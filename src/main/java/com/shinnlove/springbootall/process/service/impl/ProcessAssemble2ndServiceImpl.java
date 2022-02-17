/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

import static com.shinnlove.springbootall.process.consts.XmlParseConstant.TEMPLATE_PATH;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.enums.TemplateTriggerType;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler2nd;
import com.shinnlove.springbootall.process.model.cache.ActionCache;
import com.shinnlove.springbootall.process.model.cache.StatusCache;
import com.shinnlove.springbootall.process.model.cache.TemplateCache;
import com.shinnlove.springbootall.process.model.cache.TemplateMetadata;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessStatus;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessTemplate;
import com.shinnlove.springbootall.process.model.status.StatusPair;
import com.shinnlove.springbootall.process.parser.XmlTemplateParser;
import com.shinnlove.springbootall.process.service.ProcessAssemble2ndService;
import com.shinnlove.springbootall.process.service.ProcessMetadataService;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * Service which parse and cache template action into memory. 
 * 
 * @author Tony Zhao
 * @version $Id: ProcessAssemble2ndServiceImpl.java, v 0.1 2022-01-29 5:42 PM Tony Zhao Exp $$
 */
//@Service
public class ProcessAssemble2ndServiceImpl implements InitializingBean, ApplicationContextAware,
                                           ProcessAssemble2ndService, ProcessMetadataService {

    private static final Logger         logger          = LoggerFactory
        .getLogger(ProcessAssemble2ndServiceImpl.class);

    /** spring context injected */
    private ApplicationContext          applicationContext;

    /** is it necessary? consider later => template id => template cache */
    private Map<Integer, TemplateCache> templateIdCache = new HashMap<>();

    /** action id should be unique. action id => template cache */
    private Map<Integer, TemplateCache> actionIdCache   = new HashMap<>();

    @Override
    public void initialize(InputStream stream) {
        // Step1: parse template from xml definition
        XmlProcessTemplate xp = XmlTemplateParser.parse(stream);

        // parse action handlers and cache status
        TemplateCache template = cacheTemplate(xp);

        // Step3: build cache
        mappingCache(template);
    }

    /**
     * Build action id reverse search template mapping.
     * 
     * @param template 
     */
    private void mappingCache(TemplateCache template) {
        // 1. template id => template
        TemplateMetadata info = template.getMetadata();
        templateIdCache.put(info.getId(), template);

        // 2. then action id => template
        Map<Integer, ActionCache> actions = template.getActions();
        if (CollectionUtils.isEmpty(actions)) {
            return;
        }

        for (Map.Entry<Integer, ActionCache> entry : actions.entrySet()) {
            int actionId = entry.getKey();
            if (actionIdCache.containsKey(actionId)) {
                throw new SystemException(SystemResultCode.SYSTEM_ERROR,
                    "Each action id should be unique, now is duplicate!");
            }

            actionIdCache.put(actionId, template);
        }
    }

    private TemplateCache cacheTemplate(XmlProcessTemplate xp) {
        TemplateCache template = new TemplateCache(cacheMetadata(xp));

        Map<Integer, ActionCache> actions = new HashMap<>();
        Map<Integer, Map<Integer, StatusPair>> dstTable = new HashMap<>();
        Map<Integer, Map<Integer, ActionCache>> actionTable = new HashMap<>();

        // step1: action and its reverse reflection
        for (XmlProcessAction a : xp.getActions()) {
            int id = a.getId();
            int src = a.getSource();
            int des = a.getDestination();
            String name = a.getName();
            String desc = a.getDesc();

            List<ActionHandler2nd> sync = new ArrayList<>();
            List<ActionHandler2nd> async = new ArrayList<>();
            Map<String, Integer> prepare = new HashMap<>();
            ActionCache cache = new ActionCache(id, name, desc, src, des, sync, async, prepare);

            XmlProcessAction xa = xp.getActionById(id);
            for (XmlProcessHandler h : xa.getHandlers()) {
                ActionHandler2nd ah = fetchHandler(h);
                if (h.isTrans()) {
                    sync.add(ah);
                } else {
                    async.add(ah);
                }

                // prepare params for processes recursive call
                if (h.getPrepareId() > 0) {
                    String n = ah.getClass().getName();
                    prepare.put(n, h.getPrepareId());
                }
            }

            actions.put(id, cache);
            twoKeyReflect(id, src, new StatusPair(src, des), dstTable);
            // special warning: dst => src => action, reverse order.
            twoKeyReflect(des, src, cache, actionTable);
        }

        // step2: status check array
        List<StatusCache> statusCache = new ArrayList<>();
        for (XmlProcessStatus s : xp.getStatus()) {
            statusCache.add(new StatusCache(s.getNo(), s.getSequence(), s.getAc()));
        }

        if (CollectionUtils.isEmpty(statusCache)) {
            return template;
        }

        Collections.sort(statusCache);

        // step3: initializer
        Map<Integer, List<ActionHandler2nd>> initializer = new HashMap<>();
        Map<Integer, List<XmlProcessHandler>> inits = xp.getInits();
        for (Map.Entry<Integer, List<XmlProcessHandler>> entry : inits.entrySet()) {
            initializer.put(entry.getKey(), fetchHandlers(entry.getValue()));
        }

        // step4: triggers
        Map<String, List<ActionHandler2nd>> triggers = new HashMap<>();
        triggers.put(TemplateTriggerType.ACCEPT.name(), fetchHandlers(xp.getAccepts()));
        triggers.put(TemplateTriggerType.REJECT.name(), fetchHandlers(xp.getRejects()));
        triggers.put(TemplateTriggerType.CANCEL.name(), fetchHandlers(xp.getCancels()));

        // step5: assemble
        template.setStatusArray(statusCache.toArray(new StatusCache[statusCache.size()]));
        template.setInitializers(initializer);
        template.setActions(actions);
        template.setTriggers(triggers);
        template.setDstTable(dstTable);
        template.setActionTable(actionTable);

        return template;
    }

    private TemplateMetadata cacheMetadata(XmlProcessTemplate xp) {
        int id = xp.getId();
        int parentId = xp.getParent();
        String name = xp.getName();
        String desc = xp.getDesc();
        int reconcile = xp.getReconcile();
        int coordinate = xp.getCoordinate();

        return new TemplateMetadata(id, parentId, name, desc, reconcile, coordinate);
    }

    @SuppressWarnings("rawtypes")
    private List<ActionHandler2nd> fetchHandlers(List<XmlProcessHandler> handlers) {
        return Optional.ofNullable(handlers)
            .map(h -> h.stream().map(this::fetchHandler).collect(Collectors.toList()))
            .orElse(new ArrayList<>());
    }

    @SuppressWarnings("rawtypes")
    private ActionHandler2nd fetchHandler(XmlProcessHandler handler) {
        return fetchService(handler.getRefBeanId());
    }

    @SuppressWarnings("rawtypes")
    private ActionHandler2nd fetchService(String serviceId) {
        ActionHandler2nd handlerService = null;

        try {
            handlerService = (ActionHandler2nd) applicationContext.getBean(serviceId);
            AssertUtil.isNotNull(handlerService, SystemResultCode.SYSTEM_ERROR,
                "Springboot context doesn't have a bean with id=" + serviceId);

        } catch (Exception e) {
            LoggerUtil.error(logger, e,
                "Springboot context doesn't have a bean with id=" + serviceId, e.getMessage());

            // determine whether throw ex later...
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, e,
                "Springboot context doesn't have a bean with id=" + serviceId);
        }

        return handlerService;
    }

    private <T, V> Map<T, Map<T, V>> twoKeyReflect(T key1, T key2, V value, Map<T, Map<T, V>> map) {
        if (key1 == null || key2 == null || value == null) {
            return map;
        }

        if (map == null) {
            map = new HashMap<>();
        }

        if (!map.containsKey(key1)) {
            Map<T, V> reflectMap = new HashMap<>();
            map.put(key1, reflectMap);
        }

        Map<T, V> reflection = map.get(key1);
        reflection.put(key2, value);

        return map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // search process template xml file
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // search all template files under classpath
            Resource[] resources = resolver.getResources(TEMPLATE_PATH);
            for (Resource resource : resources) {
                // initialize xml parse and metadata assemble
                initialize(resource.getInputStream());
            }
        } catch (Exception e) {
            LoggerUtil.error(logger, e, e.getMessage(),
                "mission system scan classpath process template failed.");
            throw e;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public TemplateCache getTemplateById(int templateId) {
        return templateIdCache.get(templateId);
    }

    @Override
    public TemplateCache getTemplateByActionId(int actionId) {
        return actionIdCache.get(actionId);
    }

    private List<ActionHandler2nd> getInitializer(int templateId, int destination) {

        TemplateCache template = getTemplateById(templateId);
        Map<Integer, List<ActionHandler2nd>> inits = template.getInitializers();

        if (!inits.containsKey(destination)) {
            return new ArrayList<>();
        }

        return inits.get(destination);
    }

    private List<ActionHandler2nd> getTriggers(int templateId, int triggerType) {

        String type = TemplateTriggerType.getNameByCode(triggerType);

        if (type == null) {
            return new ArrayList<>();
        }

        TemplateCache template = getTemplateById(templateId);
        Map<String, List<ActionHandler2nd>> triggers = template.getTriggers();

        if (CollectionUtils.isEmpty(triggers) || !triggers.containsKey(type)) {
            // empty or no such triggers
            return new ArrayList<>();
        }

        return triggers.get(type);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<ActionHandler2nd> getExecutions(int actionId, boolean sync) {
        ActionCache action = getActionCache(actionId);
        if (sync) {
            return action.getSyncHandlers();
        } else {
            return action.getAsyncHandlers();
        }
    }

    @Override
    public boolean isFinalStatus(int templateId, int status) {
        TemplateCache cache = getTemplateById(templateId);
        if (cache == null || CollectionUtils.isEmpty(cache.getDstTable())) {
            return false;
        }

        Map<Integer, Map<Integer, StatusPair>> table = cache.getDstTable();
        for (Map.Entry<Integer, Map<Integer, StatusPair>> entry : table.entrySet()) {
            Map<Integer, StatusPair> dst = entry.getValue();
            if (dst.containsKey(status)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getACStatus(int templateId) {
        TemplateCache cache = getTemplateById(templateId);
        if (cache == null || cache.getStatusArray().length == 0) {
            return -1;
        }

        StatusCache[] statusCaches = cache.getStatusArray();
        for (int i = 0; i < statusCaches.length; i++) {
            if (statusCaches[i].getAccomplish() == 1) {
                return statusCaches[i].getNo();
            }
        }

        return -1;
    }

    private ActionCache getActionCache(int actionId) {
        TemplateCache cache = actionIdCache.get(actionId);
        Map<Integer, ActionCache> actions = cache.getActions();
        return actions.get(actionId);
    }

}