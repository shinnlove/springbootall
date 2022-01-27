/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.parser;

import static com.shinnlove.springbootall.process.consts.XmlParseConstant.*;

import java.io.InputStream;
import java.util.*;

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
                    continue;
                }
                if (ATTR_NAME.equals(templateAttrName)) {
                    // use template name to reflect key
                    xp.setName(templateAttrValue);
                    continue;
                }
                if (ATTR_DESC.equals(templateAttrName)) {
                    // initialized template's desc into bean definition for logging
                    xp.setName(templateAttrValue);
                    continue;
                }
                if (ATTR_PARENT.equals(templateAttrName)) {
                    // initialize parent
                    xp.setParent(Integer.parseInt(templateAttrValue));
                    continue;
                }

            } // for template's attributes

            // for each template's actions
            NodeList actionList = template.getChildNodes();

            // a collection to collect all actions that has been successfully parsed
            List<XmlProcessAction> parseActions = new ArrayList<>();

            for (int i = 0; i < actionList.getLength(); i++) {
                Node actionNode = actionList.item(i);

                dispatchSectionNode(actionNode);

            } // for actions

            // special warning: don't forget to put action list back to template
            xp.setActions(parseActions);

        } catch (Exception e) {
            LoggerUtil.error(logger, e,
                "Mission system could not initialize process templates, ex=", e.getMessage());
        }

        return xp;
    }

    private static void dispatchSectionNode(Node sectionNode) {
        switch (sectionNode.getNodeName()) {
            case SECTION_ROOT_METADATA:
                List<XmlProcessAction> metadata = scanMetadata(sectionNode);
                System.out.println(metadata);
                break;
            case SECTION_ROOT_INIT:
                Map<Integer, List<XmlProcessHandler>> inits = scanInit(sectionNode);
                System.out.println(inits);
                break;
            case SECTION_ROOT_ACCEPT:
                List<XmlProcessAction> accepts = scanAccept(sectionNode);
                System.out.println(accepts);
                break;
            case SECTION_ROOT_REJECT:
                List<XmlProcessAction> rejects = scanReject(sectionNode);
                System.out.println(rejects);
                break;
            case SECTION_ROOT_CANCEL:
                List<XmlProcessAction> cancels = scanCancel(sectionNode);
                System.out.println(cancels);
            case SECTION_ROOT_ACTION:
                List<XmlProcessAction> actions = scanActions(sectionNode);
                System.out.println(actions);
                break;
            default:
                // skip..
                break;
        }
    }

    private static List<XmlProcessAction> scanMetadata(Node actionNode) {

        NamedNodeMap actionNodeAttributes = actionNode.getAttributes();
        for (int j = 0; j < actionNodeAttributes.getLength(); j++) {
            String actionAttrName = actionNodeAttributes.item(j).getNodeName();
            String actionAttrValue = actionNodeAttributes.item(j).getNodeValue();
            System.out.println("action: " + actionAttrName + "=" + actionAttrValue + ",");

        } // for actions' attributes

        // for each action's child node
        NodeList action = actionNode.getChildNodes();

        // a collection to collect all handlers that are parsed successfully..

        return null;
    }

    private static Node getChildNodeByName(Node root, String childNodeName) {
        return getChildNodeByName(root, childNodeName, 0);
    }

    @Deprecated
    private static Node getChildNodeByName(Node root, String childNodeName, int depth) {

        if (childNodeName.equals(root.getNodeName())) {
            return root;
        }

        NodeList children = root.getChildNodes();
        int len = children.getLength();
        if (len == 0 || depth > 5) {
            // skip recursive exceed five cascades
            return null;
        }

        for (int i = 0; i < len; i++) {
            Node node = children.item(i);
            if (matchNode(node, childNodeName)) {
                return node;
            }
        }

        for (int j = 0; j < len; j++) {
            Node node = children.item(j);
            if (matchNode(node, childNodeName)) {
                return getChildNodeByName(node, childNodeName, depth + 1);
            }
        }

        return null;
    }

    private static boolean matchNode(Node node, String search) {
        return node != null && search.equals(node.getNodeName());
    }

    private static Map<Integer, List<XmlProcessHandler>> scanInit(Node sectionNode) {

        NamedNodeMap actionNodeAttributes = sectionNode.getAttributes();
        for (int j = 0; j < actionNodeAttributes.getLength(); j++) {
            String actionAttrName = actionNodeAttributes.item(j).getNodeName();
            String actionAttrValue = actionNodeAttributes.item(j).getNodeValue();
            System.out.println("action: " + actionAttrName + "=" + actionAttrValue + ",");

        } // for actions' attributes

        // for each action's child node
        NodeList secondSectionNode = sectionNode.getChildNodes();

        // a collection to collect all handlers that are parsed successfully..

        List<XmlProcessHandler> preHandlers = new ArrayList<>();
        List<XmlProcessHandler> postHandlers = new ArrayList<>();
        Map<Integer, List<XmlProcessHandler>> destinationMap = new HashMap<>();

        for (int k = 0; k < secondSectionNode.getLength(); k++) {
            Node initStepNode = secondSectionNode.item(k);

            String stepName = initStepNode.getNodeName();
            System.out.println(stepName);

            if (SECTION_INNER_PRE.equals(stepName)) {

                // parse actions, pre list add

            } else if (SECTION_INNER_DISPATCH.equals(stepName)) {

                Node destinationNode = getChildNodeByName(initStepNode, SECTION_THIRD_DESTINATION);

                List<XmlProcessHandler> dispatchHandlers = new ArrayList<>();
                parseHandler(destinationNode, dispatchHandlers);

                Integer destination = -1;

                NamedNodeMap handlerAttrs = initStepNode.getAttributes();
                for (int j = 0; j < handlerAttrs.getLength(); j++) {
                    String handlerAttrName = handlerAttrs.item(j).getNodeName();
                    String handlerAttrValue = handlerAttrs.item(j).getNodeValue();
                    System.out
                        .println("handlers: " + handlerAttrName + "=" + handlerAttrValue + ",");

                    // destination = Integer.parseInt(handlerAttrValue);

                } // for actions' attributes

                destinationMap.put(destination, dispatchHandlers);

            } else if (SECTION_INNER_POST.equals(stepName)) {

                // parse actions, post list add

            }

        } // for handlers

        Map<Integer, List<XmlProcessHandler>> finalDispatch = new HashMap<>();
        for (Map.Entry<Integer, List<XmlProcessHandler>> entry : destinationMap.entrySet()) {
            List<XmlProcessHandler> ha = new ArrayList<>(preHandlers);
            ha.addAll(entry.getValue());
            ha.addAll(postHandlers);
            finalDispatch.put(entry.getKey(), ha);
        }

        return finalDispatch;
    }

    private static List<XmlProcessAction> scanAccept(Node actionNode) {
        return null;
    }

    private static List<XmlProcessAction> scanReject(Node actionNode) {
        return null;
    }

    private static List<XmlProcessAction> scanCancel(Node actionNode) {
        return null;
    }

    private static List<XmlProcessAction> scanActions(Node actionNode) {

        List<XmlProcessAction> parseActions = new ArrayList<>();

        if (SECTION_ROOT_ACTION.equals(actionNode.getNodeName())) {

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

                if (SECTION_INNER_HANDLER.equals(handlerNode.getNodeName())) {
                    parseHandler(handlerNode, parseHandlers);
                } // if handler

            } // for handlers

            // don't forget to sort handlers after read from xml file
            Collections.sort(parseHandlers);
            // put handlers back to an action
            xa.setHandlers(parseHandlers);
            // special warning: don't forget to put action back to action list
            parseActions.add(xa);

        } // if action

        return parseActions;
    }

    private static void parseHandler(Node handlerNode, List<XmlProcessHandler> parseHandlers) {
        // a new handler created...
        XmlProcessHandler xh = new XmlProcessHandler();

        // extract handler's attributes
        NamedNodeMap handlerNodeAttributes = handlerNode.getAttributes();
        for (int l = 0; l < handlerNodeAttributes.getLength(); l++) {
            String handlerAttrName = handlerNodeAttributes.item(l).getNodeName();
            String handlerAttrValue = handlerNodeAttributes.item(l).getNodeValue();

            LoggerUtil.info(logger, "handler: ", handlerAttrName, "=", handlerAttrValue, ",");

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
    }

}