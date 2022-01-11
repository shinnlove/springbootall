/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.shinnlove.springbootall.process.model.initialization.XmlProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessTemplate;
import com.shinnlove.springbootall.util.log.LoggerUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * A util class to parse template xml file for initialization.
 *
 * @author Tony Zhao
 * @version $Id: XmlTemplateParser.java, v 0.1 2021-07-21 3:37 PM Tony Zhao Exp $$
 */
public class XmlTemplateParser {

    private static final Logger logger           = LoggerFactory.getLogger(XmlTemplateParser.class);

    private static final String ATTR_ACTION      = "action";

    private static final String ATTR_HANDLER     = "handler";

    private static final String ATTR_ID          = "id";

    private static final String ATTR_NAME        = "name";

    private static final String ATTR_DESC        = "desc";

    private static final String ATTR_ENTRANCE    = "entrance";

    private static final String ATTR_DESTINATION = "destination";

    private static final String ATTR_REFERENCE   = "ref";

    private static final String ATTR_SEQUENCE    = "seq";

    private static final String ATTR_TRANS       = "trans";

    public static XmlProcessTemplate parse(InputStream stream) {
        XmlProcessTemplate xp = new XmlProcessTemplate();

        try {
            InputStream inputStream = stream;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            // get the template node
            NodeList root = doc.getChildNodes();
            // pay attention: root node has no text, elements starts from index 0!
            Node template = root.item(0);

            // handler template attributes
            NamedNodeMap templateNodeAttributes = template.getAttributes();
            for (int h = 0; h < templateNodeAttributes.getLength(); h++) {
                String templateAttrName = templateNodeAttributes.item(h).getNodeName();
                String templateAttrValue = templateNodeAttributes.item(h).getNodeValue();

                if (ATTR_ID.equals(templateAttrName)) {
                    // handle template id
                    xp.setId(Integer.parseInt(templateAttrValue));
                }
                if (ATTR_NAME.equals(templateAttrName)) {
                    // use template name to reflect key
                    xp.setName(templateAttrValue);
                }
                if (ATTR_DESC.equals(templateAttrName)) {
                    // initialized template's desc into bean definition for logging
                    xp.setName(templateAttrValue);
                }
            } // for template's attributes

            // for each template's actions
            NodeList actionList = template.getChildNodes();

            // a collection to collect all actions that has been successfully parsed
            List<XmlProcessAction> parseActions = new ArrayList<>();

            for (int i = 0; i < actionList.getLength(); i++) {
                Node actionNode = actionList.item(i);
                if (ATTR_ACTION.equals(actionNode.getNodeName())) {

                    // a new action node created...
                    XmlProcessAction xa = new XmlProcessAction();

                    // extract action's attributes
                    NamedNodeMap actionNodeAttributes = actionNode.getAttributes();
                    for (int j = 0; j < actionNodeAttributes.getLength(); j++) {
                        String actionAttrName = actionNodeAttributes.item(j).getNodeName();
                        String actionAttrValue = actionNodeAttributes.item(j).getNodeValue();
                        //                        System.out.println("action: " + actionAttrName + "=" + actionAttrValue + ",");

                        if (ATTR_ID.equals(actionAttrName)) {
                            // handle action id
                            xa.setId(Integer.parseInt(actionAttrValue));
                        }
                        if (ATTR_NAME.equals(actionAttrName)) {
                            // use action name to reflect key
                            xa.setName(actionAttrValue);
                        }
                        if (ATTR_DESC.equals(actionAttrName)) {
                            // initialized action's desc into bean definition for logging
                            xa.setDesc(actionAttrValue);
                        }
                        if (ATTR_ENTRANCE.equals(actionAttrName)) {
                            // initialize actions's entrance for process init
                            xa.setEntrance(Boolean.parseBoolean(actionAttrValue));
                        }
                        if (ATTR_DESTINATION.equals(actionAttrName)) {
                            // initialize action's target status for check destination
                            xa.setDestination(Integer.parseInt(actionAttrValue));
                        }
                    } // for actions' attributes

                    // for each action's child node
                    NodeList action = actionNode.getChildNodes();

                    // a collection to collect all handlers that are parsed successfully..
                    List<XmlProcessHandler> parseHandlers = new ArrayList<>();

                    for (int k = 0; k < action.getLength(); k++) {
                        Node handlerNode = action.item(k);
                        if (ATTR_HANDLER.equals(handlerNode.getNodeName())) {

                            // a new handler created...
                            XmlProcessHandler xh = new XmlProcessHandler();

                            // extract handler's attributes
                            NamedNodeMap handlerNodeAttributes = handlerNode.getAttributes();
                            for (int l = 0; l < handlerNodeAttributes.getLength(); l++) {
                                String handlerAttrName = handlerNodeAttributes.item(l)
                                    .getNodeName();
                                String handlerAttrValue = handlerNodeAttributes.item(l)
                                    .getNodeValue();

                                LoggerUtil.info(logger, "handler: ", handlerAttrName, "=",
                                    handlerAttrValue, ",");

                                if (ATTR_SEQUENCE.equals(handlerAttrName)) {
                                    // handle sequence
                                    xh.setSequence(Integer.parseInt(handlerAttrValue));
                                }
                                if (ATTR_REFERENCE.equals(handlerAttrName)) {
                                    // get bean reference from spring context
                                    xh.setRefBeanId(handlerAttrValue);
                                }
                                if (ATTR_DESC.equals(handlerAttrName)) {
                                    // initialized to bean definition for logging
                                    xh.setDesc(handlerAttrValue);
                                }
                                if (ATTR_TRANS.equals(handlerAttrName)) {
                                    // trans sync execute
                                    xh.setTrans(Boolean.parseBoolean(handlerAttrValue));
                                }

                            } // for handler's attributes

                            // special waring: don't forget to put handler back to handler list
                            parseHandlers.add(xh);

                        } // if handler
                    } // for handlers

                    // don't forget to sort handlers after read from xml file
                    Collections.sort(parseHandlers);
                    // put handlers back to an action
                    xa.setHandlers(parseHandlers);
                    // special warning: don't forget to put action back to action list
                    parseActions.add(xa);

                } // if action
            } // for actions

            // special warning: don't forget to put action list back to template
            xp.setActions(parseActions);

        } catch (Exception e) {
            LoggerUtil.error(logger, e,
                "Mission system could not initialize process templates, ex=", e.getMessage());
        }

        return xp;
    }

}