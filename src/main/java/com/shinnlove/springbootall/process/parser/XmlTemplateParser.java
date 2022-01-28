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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.shinnlove.springbootall.process.model.initialization.XmlProcessAction;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessHandler;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessStatus;
import com.shinnlove.springbootall.process.model.initialization.XmlProcessTemplate;
import com.shinnlove.springbootall.util.log.LoggerUtil;

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
            Node root = StreamParser.parse(stream);

            parseAttrs(xp, parseAttrs(root));

            parseMetadata(root, xp);

            parseInit(root, xp);

            parseTriggers(root, xp);

            parseActions(root, xp);

        } catch (Exception e) {
            LoggerUtil.error(logger, e,
                "Mission system could not initialize process templates, ex=", e.getMessage());
        }

        return xp;
    }

    private static void parseMetadata(Node template, XmlProcessTemplate xp) {
        List<Node> metadata = getNodes(template, SECTION_ROOT_METADATA);
        if (CollectionUtils.isEmpty(metadata)) {
            xp.setStatus(new ArrayList<>());
        } else {
            xp.setStatus(parseStatusList(metadata.get(0)));
        }
    }

    private static List<XmlProcessStatus> parseStatusList(Node statusParentNode) {
        List<XmlProcessStatus> statusList = new ArrayList<>();

        for (Node s : getNodes(statusParentNode, SECTION_INNER_STATUS)) {
            statusList.add(parseStatus(parseAttrs(s)));
        }

        // don't forget to sort status after read from xml file
        Collections.sort(statusList);

        return statusList;
    }

    private static XmlProcessStatus parseStatus(Map<String, String> attr) {
        // a single status node created
        XmlProcessStatus xs = new XmlProcessStatus();

        xs.setNo(Integer.parseInt(attr.get(ATTR_NO)));
        xs.setName(attr.get(ATTR_NAME));
        xs.setDesc(attr.get(ATTR_DESC));
        xs.setPs(Integer.parseInt(attr.get(ATTR_PARENT_STATUS)));

        return xs;
    }

    private static void parseInit(Node template, XmlProcessTemplate xp) {
        List<Node> initSection = getNodes(template, SECTION_ROOT_INIT);
        if (CollectionUtils.isEmpty(initSection)) {
            xp.setInits(new HashMap<>());
        } else {
            xp.setInits(scanInit(initSection.get(0)));
        }
    }

    private static Map<Integer, List<XmlProcessHandler>> scanInit(Node sectionNode) {
        List<XmlProcessHandler> preHandlers = innerHandlers(sectionNode, SECTION_INNER_PRE);
        List<XmlProcessHandler> postHandlers = innerHandlers(sectionNode, SECTION_INNER_POST);

        Map<Integer, List<XmlProcessHandler>> destinations = new HashMap<>();
        List<Node> dispatch = getNodes(sectionNode, SECTION_INNER_DISPATCH);
        if (!CollectionUtils.isEmpty(dispatch)) {
            for (Node des : getNodes(dispatch.get(0), SECTION_THIRD_DESTINATION)) {

                Map<String, String> attrs = parseAttrs(des);
                Integer destination = Integer.parseInt(attrs.get(ATTR_NO));

                destinations.put(destination, parseHandlers(des));
            }
        }

        // rearrange
        Map<Integer, List<XmlProcessHandler>> finalDispatch = new HashMap<>();
        for (Map.Entry<Integer, List<XmlProcessHandler>> entry : destinations.entrySet()) {
            List<XmlProcessHandler> ha = new ArrayList<>(preHandlers);
            ha.addAll(entry.getValue());
            ha.addAll(postHandlers);
            finalDispatch.put(entry.getKey(), ha);
        }

        return finalDispatch;
    }

    private static void parseTriggers(Node template, XmlProcessTemplate xp) {
        xp.setAccepts(innerHandlers(template, SECTION_ROOT_ACCEPT));
        xp.setRejects(innerHandlers(template, SECTION_ROOT_REJECT));
        xp.setCancels(innerHandlers(template, SECTION_ROOT_CANCEL));
    }

    private static void parseActions(Node template, XmlProcessTemplate xp) {
        List<XmlProcessAction> actions = new ArrayList<>();

        List<Node> actionSection = getNodes(template, SECTION_ROOT_ACTION);
        if (!CollectionUtils.isEmpty(actionSection)) {
            for (Node ac : actionSection) {
                List<XmlProcessHandler> handlers = new ArrayList<>(parseHandlers(ac));
                actions.add(parseAction(parseAttrs(ac), handlers));
            }
        }

        // status trans actions
        xp.setActions(actions);
    }

    private static XmlProcessAction parseAction(Map<String, String> attr,
                                                List<XmlProcessHandler> handlers) {
        XmlProcessAction xa = new XmlProcessAction();

        xa.setId(Integer.parseInt(attr.get(ATTR_ID)));
        xa.setName(attr.get(ATTR_NAME));
        xa.setDesc(attr.get(ATTR_DESC));
        xa.setEntrance(Boolean.parseBoolean(attr.get(ATTR_ENTRANCE)));

        int source = attr.get(ATTR_SOURCE) == null ? -1 : Integer.parseInt(attr.get(ATTR_SOURCE));
        xa.setSource(source);

        xa.setDestination(Integer.parseInt(attr.get(ATTR_DESTINATION)));
        xa.setHandlers(handlers);

        return xa;
    }

    private static List<XmlProcessHandler> innerHandlers(Node current, String handlerParentTag) {
        List<XmlProcessHandler> handlers = new ArrayList<>();

        List<Node> nodes = getNodes(current, handlerParentTag);
        if (!CollectionUtils.isEmpty(nodes)) {
            handlers.addAll(parseHandlers(nodes.get(0)));
        }

        return handlers;
    }

    private static List<XmlProcessHandler> parseHandlers(Node handlerParentNode) {
        List<XmlProcessHandler> handlers = new ArrayList<>();
        for (Node ha : getNodes(handlerParentNode, SECTION_INNER_HANDLER)) {
            handlers.add(parseHandler(parseAttrs(ha)));
        }

        // don't forget to sort handlers after read from xml file
        Collections.sort(handlers);

        return handlers;
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

    private static List<Node> getNodes(Node root, String childName) {
        return getNodes(root, childName, 0);
    }

    @Deprecated
    private static List<Node> getNodes(Node root, String childName, int depth) {

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
            int j = 0;
            while (j < len) {
                Node node = children.item(j++);
                List<Node> onceResult = getNodes(node, childName, depth + 1);
                if (!CollectionUtils.isEmpty(onceResult)) {
                    result.addAll(onceResult);
                }
            }
        }

        return result;
    }

    private static Map<String, String> parseAttrs(Node node) {
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

    private static void parseAttrs(XmlProcessTemplate xp, Map<String, String> attr) {
        xp.setId(Integer.parseInt(attr.get(ATTR_ID)));
        xp.setName(attr.get(ATTR_NAME));
        xp.setDesc(attr.get(ATTR_DESC));
        xp.setParent(Integer.parseInt(attr.get(ATTR_PARENT)));
    }

}