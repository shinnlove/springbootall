/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

import java.io.InputStream;
import java.util.*;

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
        cacheActionStatus(xp);

    }

    private TemplateCache cacheActionStatus(XmlProcessTemplate xp) {

        ActionCache actionCache = new ActionCache();
        for (XmlProcessAction a : xp.getActions()) {
            int id = a.getId();

            List<ActionHandler> sync = new ArrayList<>();
            List<ActionHandler> async = new ArrayList<>();

            XmlProcessAction xa = xp.getActionById(id);
            for (XmlProcessHandler h : xa.getHandlers()) {
                ActionHandler ah = fetchService(h.getRefBeanId());
                if (h.isTrans()) {
                    sync.add(ah);
                } else {
                    async.add(ah);
                }
            }

            actionCache.setHandlers(id, sync, async);
        }

        TemplateCache template = new TemplateCache();
        List<StatusCache> statusCache = new ArrayList<>();

        for (XmlProcessStatus s : xp.getStatus()) {
            statusCache.add(new StatusCache(s.getNo(), s.getSequence()));
        }

        Collections.sort(statusCache);

        if (CollectionUtils.isEmpty(statusCache)) {
            return template;
        }

        Map<Integer, StatusPair> statusPairs = new HashMap<>();
        for (XmlProcessAction pa : xp.getActions()) {
            int actionId = pa.getId();
            int source = pa.getSource();
            int destination = pa.getDestination();
            List<XmlProcessHandler> handlers = pa.getHandlers();

            statusPairs.put(actionId, new StatusPair(source, destination));

            Map<Integer, Map<Integer, ActionCache>> reverseFlow = new HashMap<>();

        }

        StatusCache[] statusArray = (StatusCache[]) statusCache.toArray();

        return template;
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