/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.initialization;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Xml file process template attributes domain model.
 *
 * @author Tony Zhao
 * @version $Id: XmlProcessTemplate.java, v 0.1 2021-07-21 2:55 PM Tony Zhao Exp $$
 */
public class XmlProcessTemplate implements Serializable {

    /** uuid */
    private static final long                     serialVersionUID = 5560785027283706190L;

    /** template id */
    private int                                   id;

    /** template name */
    private String                                name;

    /** template description */
    private String                                desc;

    /** parent template id, default -1 represents no parent */
    private int                                   parent           = -1;

    /** whether need to reconcile parent when child process completed. */
    private int                                   reconcile        = 0;

    /** reconcile parent mode when child process completed. */
    private int                                   coordinate       = 1;

    /** metadata for status list */
    private List<XmlProcessStatus>                status;

    /** initialize process */
    private Map<Integer, List<XmlProcessHandler>> inits;

    /** handlers called when status accepted */
    private List<XmlProcessHandler>               accepts;

    /** handlers called when status rejected */
    private List<XmlProcessHandler>               rejects;

    /** handlers called when status canceled */
    private List<XmlProcessHandler>               cancels;

    /** a couple of actions hold by the template */
    private List<XmlProcessAction>                actions;

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
     * Constructor with basic arguments and status list.
     * 
     * @param id 
     * @param name
     * @param desc
     * @param parent
     * @param status
     */
    public XmlProcessTemplate(int id, String name, String desc, int parent,
                              List<XmlProcessStatus> status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.parent = parent;
        this.status = status;
    }

    /**
     * Constructor with all arguments.
     * 
     * @param id 
     * @param name
     * @param desc
     * @param parent
     * @param reconcile
     * @param coordinate
     * @param status
     * @param inits
     * @param accepts
     * @param rejects
     * @param cancels
     * @param actions
     */
    public XmlProcessTemplate(int id, String name, String desc, int parent, int reconcile,
                              int coordinate, List<XmlProcessStatus> status,
                              Map<Integer, List<XmlProcessHandler>> inits,
                              List<XmlProcessHandler> accepts, List<XmlProcessHandler> rejects,
                              List<XmlProcessHandler> cancels, List<XmlProcessAction> actions) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.parent = parent;
        this.reconcile = reconcile;
        this.coordinate = coordinate;
        this.status = status;
        this.inits = inits;
        this.accepts = accepts;
        this.rejects = rejects;
        this.cancels = cancels;
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

    public int getReconcile() {
        return reconcile;
    }

    public void setReconcile(int reconcile) {
        this.reconcile = reconcile;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public List<XmlProcessStatus> getStatus() {
        return status;
    }

    public void setStatus(List<XmlProcessStatus> status) {
        this.status = status;
    }

    public Map<Integer, List<XmlProcessHandler>> getInits() {
        return inits;
    }

    public void setInits(Map<Integer, List<XmlProcessHandler>> inits) {
        this.inits = inits;
    }

    public List<XmlProcessHandler> getAccepts() {
        return accepts;
    }

    public void setAccepts(List<XmlProcessHandler> accepts) {
        this.accepts = accepts;
    }

    public List<XmlProcessHandler> getRejects() {
        return rejects;
    }

    public void setRejects(List<XmlProcessHandler> rejects) {
        this.rejects = rejects;
    }

    public List<XmlProcessHandler> getCancels() {
        return cancels;
    }

    public void setCancels(List<XmlProcessHandler> cancels) {
        this.cancels = cancels;
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