/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.initialization;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Xml file process action attributes domain model.
 *
 * @author Tony Zhao
 * @version $Id: XmlProcessAction.java, v 0.1 2021-07-21 2:55 PM Tony Zhao Exp $$
 */
public class XmlProcessAction implements Serializable {
    private static final long       serialVersionUID = -1306494317871543125L;

    /** action id */
    private int                     id;

    /** action name */
    private String                  name;

    /** action description */
    private String                  desc;

    /** whether this action is an entrance, reject proceed if no process and not an entrance */
    private boolean                 entrance;

    /** action targeting destination status */
    private int                     destination;

    /** handlers that are orchestrated by its sequence order */
    private List<XmlProcessHandler> handlers;

    /**
     * Constructor for reflect.
     */
    public XmlProcessAction() {
    }

    public XmlProcessAction(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    /**
     * Constructor with all arguments.
     *
     * @param id
     * @param name
     * @param desc
     * @param entrance
     * @param destination
     * @param handlers
     */
    public XmlProcessAction(int id, String name, String desc, boolean entrance, int destination, List<XmlProcessHandler> handlers) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.entrance = entrance;
        this.destination = destination;
        this.handlers = handlers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isEntrance() {
        return entrance;
    }

    public void setEntrance(boolean entrance) {
        this.entrance = entrance;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public List<XmlProcessHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<XmlProcessHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}