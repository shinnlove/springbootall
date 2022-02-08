/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.enums.TemplateTriggerType;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessStatus;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessTemplate;
import com.shinnlove.springbootall.process.model.status.StatusPair;
import com.shinnlove.springbootall.process.model.template.ActionCache;
import com.shinnlove.springbootall.process.model.template.StatusCache;
import com.shinnlove.springbootall.process.model.template.TemplateCache;
import com.shinnlove.springbootall.process.parser.XmlTemplateParser;
import com.shinnlove.springbootall.process.service.ProcessAssemble2ndService;
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
@Service
public class ProcessAssemble2ndServiceImpl implements ProcessAssemble2ndService,
                                           ApplicationContextAware, InitializingBean {

    private static final Logger         logger        = LoggerFactory
        .getLogger(ProcessAssemble2ndServiceImpl.class);

    private ApplicationContext          applicationContext;

    /** template id => template cache */
    private Map<Integer, TemplateCache> templateCache = new HashMap<>();

    @Override
    public void initializeProcess(InputStream stream) {
        // parse template from xml definition
        XmlProcessTemplate xp = XmlTemplateParser.parse(stream);

        // parse action handlers and cache status
        TemplateCache template = cacheTemplate(xp);
        templateCache.put(xp.getId(), template);
    }

    private TemplateCache cacheTemplate(XmlProcessTemplate xp) {
        TemplateCache template = new TemplateCache();

        Map<Integer, StatusPair> actionStatus = new HashMap<>();
        Map<Integer, Map<Integer, ActionCache>> reverseFlow = new HashMap<>();

        // step1: action and its reverse reflection
        for (XmlProcessAction a : xp.getActions()) {
            int id = a.getId();
            int src = a.getSource();
            int des = a.getDestination();

            List<ActionHandler> sync = new ArrayList<>();
            List<ActionHandler> async = new ArrayList<>();
            ActionCache cache = new ActionCache(id, src, des, sync, async);

            XmlProcessAction xa = xp.getActionById(id);
            for (XmlProcessHandler h : xa.getHandlers()) {
                ActionHandler ah = fetchHandler(h);
                if (h.isTrans()) {
                    sync.add(ah);
                } else {
                    async.add(ah);
                }
            }

            actionStatus.put(id, new StatusPair(src, des));
            twoKeyReflect(src, des, cache, reverseFlow);
        }

        // step2: status check array
        List<StatusCache> statusCache = new ArrayList<>();
        for (XmlProcessStatus s : xp.getStatus()) {
            statusCache.add(new StatusCache(s.getNo(), s.getSequence()));
        }

        Collections.sort(statusCache);

        if (CollectionUtils.isEmpty(statusCache)) {
            return template;
        }

        // step3: initializer
        Map<Integer, List<ActionHandler>> initializer = new HashMap<>();
        Map<Integer, List<XmlProcessHandler>> inits = xp.getInits();
        for (Map.Entry<Integer, List<XmlProcessHandler>> entry : inits.entrySet()) {
            int target = entry.getKey();
            List<ActionHandler> handlers = fetchHandlers(entry.getValue());
            initializer.put(target, handlers);
        }

        // step4: triggers
        Map<String, List<ActionHandler>> triggers = new HashMap<>();
        triggers.put(TemplateTriggerType.ACCEPT.name(), fetchHandlers(xp.getAccepts()));
        triggers.put(TemplateTriggerType.REJECT.name(), fetchHandlers(xp.getRejects()));
        triggers.put(TemplateTriggerType.CANCEL.name(), fetchHandlers(xp.getCancels()));

        // step5: array
        template.setStatusArray(statusCache.toArray(new StatusCache[statusCache.size()]));
        template.setActionStatus(actionStatus);
        template.setReverseFlow(reverseFlow);
        template.setInitializers(initializer);
        template.setTriggers(triggers);

        return template;
    }

    private List<ActionHandler> fetchHandlers(List<XmlProcessHandler> handlers) {
        return Optional.ofNullable(handlers)
            .map(hs -> hs.stream().map(h -> fetchHandler(h)).collect(Collectors.toList()))
            .orElse(new ArrayList<>());
    }

    private ActionHandler fetchHandler(XmlProcessHandler handler) {
        return fetchService(handler.getRefBeanId());
    }

    @SuppressWarnings("rawtypes")
    private ActionHandler fetchService(String serviceId) {
        ActionHandler handlerService = null;

        try {
            handlerService = (ActionHandler) applicationContext.getBean(serviceId);
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
            Resource[] resources = resolver.getResources("classpath:META-INF/process/*.xml");
            for (Resource resource : resources) {
                // initialize xml parse and metadata assemble
                initializeProcess(resource.getInputStream());
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

}