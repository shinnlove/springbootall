/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.initialization;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Xml file process template attributes domain model.
 *
 * @author Tony Zhao
 * @version $Id: XmlProcessTemplate.java, v 0.1 2021-07-21 2:55 PM Tony Zhao Exp $$
 */
public class XmlProcessTemplate implements Serializable {

    private static final long      serialVersionUID = 2554229365631674354L;

    /** template id */
    private int                    id;

    /** template name */
    private String                 name;

    /** template description */
    private String                 desc;

    /** parent template id, default -1 represents no parent */
    private int                    parent           = -1;

    /** a couple of actions hold by the template */
    private List<XmlProcessAction> actions;

    /**
     * Constructor for reflect.
     */
    public XmlProcessTemplate() {
    }

    /**
     * Constructor with basic arguments. 
     * 
     * @param id 
     * @param name
     * @param desc
     * @param parent
     */
    public XmlProcessTemplate(int id, String name, String desc, int parent) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.parent = parent;
    }

    /**
     * Constructor with all arguments.
     * 
     * @param id 
     * @param name
     * @param desc
     * @param parent
     * @param actions
     */
    public XmlProcessTemplate(int id, String name, String desc, int parent,
                              List<XmlProcessAction> actions) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.parent = parent;
        this.actions = actions;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<XmlProcessAction> getActions() {
        return actions;
    }

    public void setActions(List<XmlProcessAction> actions) {
        this.actions = actions;
    }

    public XmlProcessAction getActionById(int actionId) {
        for (XmlProcessAction xa : actions) {
            if (actionId == xa.getId()) {
                return xa;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}