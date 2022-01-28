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
import org.springframework.util.CollectionUtils;
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

    private static final Logger logger = LoggerFactory.getLogger(XmlTemplateParser.class);

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

            wrapTemplateAttr(xp, parseNodeAttrs(template));

            List<Node> metadata = getChildNodesByName(template, SECTION_ROOT_METADATA);

            List<Node> initSection = getChildNodesByName(template, SECTION_ROOT_INIT);
            if (!CollectionUtils.isEmpty(initSection)) {
                Map<Integer, List<XmlProcessHandler>> inits = scanInit(initSection.get(0));
            }

            List<Node> accepts = getChildNodesByName(template, SECTION_ROOT_ACCEPT);
            List<Node> rejects = getChildNodesByName(template, SECTION_ROOT_REJECT);
            List<Node> cancels = getChildNodesByName(template, SECTION_ROOT_CANCEL);

            // a collection to collect all actions that has been successfully parsed
            List<XmlProcessAction> parseActions = new ArrayList<>();
            List<Node> actionSection = getChildNodesByName(template, SECTION_ROOT_ACTION);
            if (!CollectionUtils.isEmpty(actionSection)) {
                for (Node ac : actionSection) {
                    List<XmlProcessHandler> handlers = new ArrayList<>();

                    parseActions.add(parseAction(parseNodeAttrs(ac), handlers));

                    for (Node ha : getChildNodesByName(ac, SECTION_INNER_HANDLER)) {
                        handlers.add(parseHandler(parseNodeAttrs(ha)));
                    }

                    // don't forget to sort handlers after read from xml file
                    Collections.sort(handlers);
                }
            }
            // special warning: don't forget to put action list back to template
            xp.setActions(parseActions);

        } catch (Exception e) {
            LoggerUtil.error(logger, e,
                "Mission system could not initialize process templates, ex=", e.getMessage());
        }

        return xp;
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

                for (Node des : getChildNodesByName(initStepNode, SECTION_THIRD_DESTINATION)) {
                    Map<String, String> desAttrs = parseNodeAttrs(des);
                    System.out.println(desAttrs);

                    Integer destination = Integer.parseInt(desAttrs.get(ATTR_NO));

                    List<XmlProcessHandler> dispatchHandlers = new ArrayList<>();
                    for (Node ha : getChildNodesByName(des, SECTION_INNER_HANDLER)) {
                        Map<String, String> haAttrs = parseNodeAttrs(ha);
                        XmlProcessHandler h = parseHandler(haAttrs);
                        dispatchHandlers.add(h);
                    }

                    destinationMap.put(destination, dispatchHandlers);

                }

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

    private static void wrapTemplateAttr(XmlProcessTemplate xp, Map<String, String> attr) {
        xp.setId(Integer.parseInt(attr.get(ATTR_ID)));
        xp.setName(attr.get(ATTR_NAME));
        xp.setDesc(attr.get(ATTR_DESC));
        xp.setParent(Integer.parseInt(attr.get(ATTR_PARENT)));
    }

    private static XmlProcessAction parseAction(Map<String, String> attr,
                                                List<XmlProcessHandler> handlers) {
        XmlProcessAction xa = new XmlProcessAction();

        xa.setId(Integer.parseInt(attr.get(ATTR_ID)));
        xa.setName(attr.get(ATTR_NAME));
        xa.setDesc(attr.get(ATTR_DESC));
        xa.setEntrance(Boolean.parseBoolean(attr.get(ATTR_ENTRANCE)));
        xa.setSource(Integer.parseInt(attr.get(ATTR_SOURCE)));
        xa.setDestination(Integer.parseInt(attr.get(ATTR_DESTINATION)));
        xa.setHandlers(handlers);

        return xa;
    }

    private static XmlProcessHandler parseHandler(Map<String, String> attr) {
        // a new handler created...
        XmlProcessHandler xh = new XmlProcessHandler();

        xh.setSequence(Integer.parseInt(attr.get(ATTR_SEQUENCE)));
        xh.setRefBeanId(attr.get(ATTR_REFERENCE));
        xh.setDesc(attr.get(ATTR_DESC));
        xh.setTrans(Boolean.parseBoolean(attr.get(ATTR_TRANS)));

        return xh;
    }

    private static boolean matchNode(Node node, String search) {
        return node != null && search.equals(node.getNodeName());
    }

    private static List<Node> getChildNodesByName(Node root, String childName) {
        return getChildNodesByName(root, childName, 0);
    }

    @Deprecated
    private static List<Node> getChildNodesByName(Node root, String childName, int depth) {

        List<Node> result = new ArrayList<>();

        if (childName.equals(root.getNodeName())) {
            result.add(root);
            return result;
        }

        NodeList children = root.getChildNodes();
        int len = children.getLength();

        if (len == 0 || depth > 5) {
            // skip recursive exceed five cascades
            return result;
        }

        for (int i = 0; i < len; i++) {
            Node node = children.item(i);
            if (matchNode(node, childName)) {
                result.add(node);
            }
        }

        if (CollectionUtils.isEmpty(result)) {
            // not found in current level
            // deep dive into second level
            boolean found = false;
            int j = 0;
            while (j < len) {
                Node node = children.item(j++);
                List<Node> onceResult = getChildNodesByName(node, childName, depth + 1);
                if (!CollectionUtils.isEmpty(onceResult)) {
                    result.addAll(onceResult);
                }
            }
        }

        return result;
    }

    private static Map<String, String> parseNodeAttrs(Node node) {
        Map<String, String> attrMap = new HashMap<>();

        NamedNodeMap nodeAttrs = node.getAttributes();
        int len = nodeAttrs.getLength();

        if (len <= 0) {
            return attrMap;
        }

        for (int i = 0; i < len; i++) {
            String attrName = nodeAttrs.item(i).getNodeName();
            String attrValue = nodeAttrs.item(i).getNodeValue();
            attrMap.put(attrName, attrValue);
        }

        return attrMap;
    }

}