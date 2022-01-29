/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.template;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.status.StatusPair;

/**
 * @author Tony Zhao
 * @version $Id: TemplateCache.java, v 0.1 2022-01-29 3:00 PM Tony Zhao Exp $$
 */
public class TemplateCache implements Serializable {

    /** uuid */
    private static final long                       serialVersionUID = 4699755725421697179L;

    /** type => handlers accept, reject, cancel trigger handlers */
    private Map<String, List<ActionHandler>>        triggers         = new HashMap<>();

    /** destination status => handlers, initialize status and handlers */
    private Map<Integer, List<ActionHandler>>       initializers     = new HashMap<>();

    /** process status list */
    private StatusCache[]                           statusArray;

    /** action id => source + destination */
    private Map<Integer, StatusPair>                actionStatus     = new HashMap<>();

    /** destination => source => action */
    private Map<Integer, Map<Integer, ActionCache>> reverseFlow      = new HashMap<>();

}