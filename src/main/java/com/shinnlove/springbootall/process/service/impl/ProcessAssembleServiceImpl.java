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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.core.ProcessActionMetadataCoreService;
import com.shinnlove.springbootall.process.core.ProcessStatusMetadataCoreService;
import com.shinnlove.springbootall.process.core.ProcessTemplateMetadataCoreService;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.action.ProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessTemplate;
import com.shinnlove.springbootall.process.model.status.ProcessStatus;
import com.shinnlove.springbootall.process.model.status.StatusPair;
import com.shinnlove.springbootall.process.model.template.ProcessTemplate;
import com.shinnlove.springbootall.process.parser.XmlTemplateParser;
import com.shinnlove.springbootall.process.service.ProcessAssembleService;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * Assemble process service, initialize status when spring context starts up.
 *
 * @author Tony Zhao
 * @version $Id: ProcessAssembleServiceImpl.java, v 0.1 2021-07-06 6:36 PM Tony Zhao Exp $$
 */
@Deprecated
@Service
public class ProcessAssembleServiceImpl implements ProcessAssembleService, ApplicationContextAware,
                                        InitializingBean {

    private static final Logger                               logger              = LoggerFactory
        .getLogger(ProcessAssembleServiceImpl.class);

    /** the springboot application context for get service bean */
    private ApplicationContext                                applicationContext;

    /** the reverse action id to template mapping initialized by springboot context */
    private Map<Integer, ProcessTemplate>                     actionTemplateMap   = new HashMap<>();

    /** template id => AC status, A reflect mapping for each template hold the normal accomplished status number! */
    private Map<Integer, Integer>                             templateACStatus    = new HashMap<>();

    /** the action id to action info mapping initialized by springboot context */
    @Deprecated
    private Map<Integer, ProcessAction>                       actionMap           = new HashMap<>();

    /** template_id => action_id => current status => destination status */
    private Map<Integer, Map<Integer, Map<Integer, Integer>>> actionStatus        = new HashMap<>();

    /** template_id => action_id => sync execute in tx's handlers */
    @Deprecated
    private Map<Integer, Map<Integer, List<ActionHandler>>>   actionSyncHandlers  = new HashMap<>();

    /** template_id => action_id => async execute in tx's handlers */
    @Deprecated
    private Map<Integer, Map<Integer, List<ActionHandler>>>   actionAsyncHandlers = new HashMap<>();

    @Autowired
    private ProcessTemplateMetadataCoreService                processTemplateMetadataCoreService;

    @Autowired
    private ProcessActionMetadataCoreService                  processActionMetadataCoreService;

    @Autowired
    private ProcessStatusMetadataCoreService                  processStatusMetadataCoreService;

    @Override
    public void initializeProcessTemplateStatus(InputStream stream) {
        // Step1: parse the template xml file defined in classpath
        XmlProcessTemplate xp = XmlTemplateParser.parse(stream);
        int templateId = xp.getId();

        // Step2: initialize each template's normal completed status
        List<ProcessStatus> processStatusList = processStatusMetadataCoreService
            .getProcessStatus(templateId);
        for (ProcessStatus ps : processStatusList) {
            if (1 == ps.getNormalCompleted()) {
                templateACStatus.put(templateId, ps.getStatusNumber());
            }
        }

        // Step3: initialize the metadata of process template
        // please pay special attention here: metadata service could only be inject and called by this class!!!
        // the application should restart if there is any changes to template xml file or database status records.
        ProcessTemplate processTemplate = processTemplateMetadataCoreService
            .getTemplateMetadata(templateId);

        // Step4: initialize the metadata of process status
        List<ProcessAction> actionList = processActionMetadataCoreService
            .getProcessActions(templateId);
        for (ProcessAction pa : actionList) {
            // use database status's action id to reflect each action parsed.
            int actionId = pa.getActionId();
            int source = pa.getSourceStatus();
            int destination = pa.getDestinationStatus();
            // 7/26: build reverse mapping
            actionTemplateMap.put(actionId, processTemplate);
            actionMap.put(actionId, pa);

            // initialize status flow and handler mapping
            buildStatusReflection(templateId, actionId, source, destination);
            initActionHandlerMapping(templateId, actionId, actionSyncHandlers);
            initActionHandlerMapping(templateId, actionId, actionAsyncHandlers);
            List<ActionHandler> syncHandlers = actionSyncHandlers.get(templateId).get(actionId);
            List<ActionHandler> asyncHandlers = actionAsyncHandlers.get(templateId).get(actionId);

            // do mapping
            XmlProcessAction xa = xp.getActionById(actionId);
            for (XmlProcessHandler xh : xa.getHandlers()) {
                String beanId = xh.getRefBeanId();
                ActionHandler handlerBean = null;
                try {
                    handlerBean = (ActionHandler) applicationContext.getBean(beanId);
                    AssertUtil.isNotNull(handlerBean, SystemResultCode.SYSTEM_ERROR,
                        "Springboot context doesn't have a bean with id=" + beanId);

                    // special warn: put bean service into handler list
                    if (xh.isTrans()) {
                        syncHandlers.add(handlerBean);
                    } else {
                        asyncHandlers.add(handlerBean);
                    }
                } catch (Exception e) {
                    LoggerUtil.error(logger, e,
                        "Springboot context doesn't have a bean with id=" + beanId, e.getMessage());
                    throw new SystemException(SystemResultCode.SYSTEM_ERROR, e,
                        "Springboot context doesn't have a bean with id=" + beanId);
                }
            } // for search each bean service in springboot context

        } // for template's status

        LoggerUtil.debug(logger, "Template and status's metadata has been successfully loaded.");
        LoggerUtil.debug(logger, "Springboot context handler has been successfully reflected.");
    }

    @Override
    public ProcessTemplate getTemplateInfoByActionId(int actionId) {
        return actionTemplateMap.get(actionId);
    }

    @Override
    public ProcessAction getActionInfoById(int actionId) {
        return actionMap.get(actionId);
    }

    @Override
    public StatusPair getActionStatus(int actionId) {
        // reverse get
        ProcessTemplate template = actionTemplateMap.get(actionId);
        int templateId = template.getTemplateId();

        // get status pair
        Map<Integer, Integer> statusPair = actionStatus.get(templateId).get(actionId);

        // get map key source status
        int sourceStatus = -1;
        int destinationStatus = -1;
        for (Map.Entry<Integer, Integer> entry : statusPair.entrySet()) {
            sourceStatus = entry.getKey();
            destinationStatus = entry.getValue();
            break;
        }

        return new StatusPair(sourceStatus, destinationStatus);
    }

    @Override
    public List<ActionHandler> getActionExecutions(int actionId, boolean sync) {
        // reverse get
        ProcessTemplate template = actionTemplateMap.get(actionId);
        int templateId = template.getTemplateId();

        // get action handlers
        Map<Integer, List<ActionHandler>> actionHandler;
        if (sync) {
            // synchronous execute handlers
            actionHandler = actionSyncHandlers.get(templateId);
        } else {
            actionHandler = actionAsyncHandlers.get(templateId);
        }

        if (!CollectionUtils.isEmpty(actionHandler)) {
            List<ActionHandler> handlers = actionHandler.get(actionId);
            if (!CollectionUtils.isEmpty(handlers)) {
                return handlers;
            }
        }

        return Collections.emptyList();
    }

    @Override
    public boolean isFinalStatus(int templateId, int sourceStatus) {
        Map<Integer, Map<Integer, Integer>> actionMappings = actionStatus.get(templateId);
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : actionMappings.entrySet()) {
            Map<Integer, Integer> statusPair = entry.getValue();
            if (statusPair.containsKey(sourceStatus)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getACFinalStatus(int templateId) {
        if (templateACStatus.containsKey(templateId)) {
            return templateACStatus.get(templateId);
        }
        return -1;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
//                initializeProcessTemplateStatus(resource.getInputStream());
            }
        } catch (Exception e) {
            LoggerUtil.error(logger, e, e.getMessage(),
                "mission system scan classpath process template failed.");
            throw e;
        }
    }

    private void buildStatusReflection(int templateId, int actionId, int sourceStatus,
                                       int destinationStatus) {
        if (!actionStatus.containsKey(templateId)) {
            Map<Integer, Map<Integer, Integer>> actionStatusPair = new HashMap<>();
            actionStatus.put(templateId, actionStatusPair);
        }
        Map<Integer, Map<Integer, Integer>> actionStatusPair = actionStatus.get(templateId);
        if (!actionStatusPair.containsKey(actionId)) {
            Map<Integer, Integer> statusPair = new HashMap<>();
            statusPair.put(sourceStatus, destinationStatus);
            actionStatusPair.put(actionId, statusPair);
        }
    }

    private void initActionHandlerMapping(int templateId, int actionId,
                                          final Map<Integer, Map<Integer, List<ActionHandler>>> handlerMapping) {
        if (!handlerMapping.containsKey(templateId)) {
            Map<Integer, List<ActionHandler>> aHandlers = new HashMap<>();
            handlerMapping.put(templateId, aHandlers);
        }
        Map<Integer, List<ActionHandler>> actionHandlers = handlerMapping.get(templateId);
        if (!actionHandlers.containsKey(actionId)) {
            List<ActionHandler> handlers = new ArrayList<>();
            actionHandlers.put(actionId, handlers);
        }
    }

}