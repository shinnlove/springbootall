/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.chain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.shinnlove.springbootall.process.ex.StatusBreakException;
import com.shinnlove.springbootall.process.ex.StatusContinueException;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler2nd;
import com.shinnlove.springbootall.process.model.context.ProcessContext;

/**
 * The action chain new model, each process with explicit template id and action id will have specific handlers.
 *
 * @author Tony Zhao
 * @version $Id: ActionChain.java, v 0.1 2021-07-06 7:48 PM Tony Zhao Exp $$
 */
public class ActionChain implements Serializable {

    private static final long   serialVersionUID = -6116252186437102890L;

    /** current action index */
    private int                 index            = 0;

    /** actions that need to do  */
    private List<ActionHandler2nd> actionHandlers;

    /**
     * Constructor for inject action's handlers.
     *
     * @param actionHandlers
     */
    public ActionChain(List<ActionHandler2nd> actionHandlers) {
        this.actionHandlers = actionHandlers;
    }

    /**
     * Special warning: this process method should be wrapper with try...catch... to do business tx.
     *
     * @param context
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void process(ProcessContext context) {
        // check handler's index won't go out of boundary
        int size = actionHandlers.size();
        if (index >= size) {
            return;
        }

        // execute handler in sequence order
        ActionHandler2nd handler = actionHandlers.get(index++);
        try {
            handler.doProcess(this, context);
        } catch (StatusContinueException e) {
            // skip current handler remaining code snippet and move to execute next
            process(context);
        } catch (StatusBreakException e) {
            // break all succeed handlers
            return;
        }
    }

    public int getIndex() {
        return index;
    }

    public List<ActionHandler2nd> getActionHandlers() {
        return actionHandlers;
    }

    public Void continue0() {
        throw new StatusContinueException("continue");
    }

    public Void break0() {
        throw new StatusBreakException("break");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}