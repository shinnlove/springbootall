/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.parser;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * File and stream parse util class.
 * 
 * @author Tony Zhao
 * @version $Id: StreamParser.java, v 0.1 2022-01-28 6:36 PM Tony Zhao Exp $$
 */
public class StreamParser {

    public static Node parse(InputStream stream) throws ParserConfigurationException, IOException,
                                                 SAXException {
        InputStream inputStream = stream;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);

        // pay attention: root node (the template node) has no text, elements starts from index 0!
        NodeList root = doc.getChildNodes();
        Node template = root.item(0);

        return template;
    }

}