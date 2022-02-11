/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.handler.interfaces;

import java.util.List;
import java.util.Optional;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.model.context.ProcessContext;

/**
 * The common interface for action handler.
 *
 *  @param <T> input params from process' data context
 *  @param <R> return result from this action handler
 *
 * @author Tony Zhao
 * @version $Id: ActionHandler.java, v 0.1 2021-07-07 3:15 PM Tony Zhao Exp $$
 */
@FunctionalInterface
public interface ActionHandler<T, R> {

    @SuppressWarnings("rawtypes")
    default void cache(final List<ActionHandler> handlers, final int index, final R result,
                       ProcessContext<T> x) {
        String k = handlers.get(index).getClass().getName();
        x.getResult().put(k, result);
        x.getClazz().put(k, result.getClass());
    }

    /**
     * get the original input params from data context holds by process context.
     *
     * @param x
     * @return
     */
    default T params(ProcessContext<T> x) {
        return Optional.ofNullable(x.getDataContext().getData()).orElse(null);
    }

    /**
     * fetch previous data from context.
     *
     * @param x         the context of process
     * @param clazz     the handler type in process chain
     * @return
     */
    @Deprecated
    @SuppressWarnings({ "unchecked", "rawtypes" })
    default R result0(ProcessContext x, Class<? extends ActionHandler<T, ?>> clazz) {
        return Optional.ofNullable((R) x.getResult().get(clazz.getName())).orElse(null);
    }

    /**
     * Fetch previous data from context.
     * 
     * @param x         the context of process
     * @param clazz     the handler type in process chain
     * @param <V>       generic type for result to return
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    default <V> V result(ProcessContext x, Class<? extends ActionHandler> clazz) {
        String cn = clazz.getName();
        Class<V> c = Optional.ofNullable(x.getClazz().get(cn)).map(r -> (Class<V>) r).orElse(null);
        Object o = Optional.ofNullable(x.getResult().get(cn)).orElse(null);

        return cast(c, o);
    }

    /**
     * Cast result object to specific generic type.
     * 
     * @param c 
     * @param o
     * @param <V>
     * @return
     */
    default <V> V cast(Class<V> c, Object o) {
        if (c != null && c.isInstance(o)) {
            return c.cast(o);
        }
        return null;
    }

    /**
     * default implementation of this interface for chain control reverse and result K-Class store.
     *
     * @param c     the handler's chain
     * @param x     the process context
     */
    default void doProcess(ActionChain c, ProcessContext<T> x) {
        cache(c.getActionHandlers(), c.getIndex() - 1, process(c, x), x);
        c.process(x);
    }

    /**
     * common interface for each handler to handle its own business.
     *
     * @param chain         the whole process chain that holds each handler in one action
     * @param context       the specific context in whole process, not only including template id and action id,
     *                      but also has differentiate data or parameters as well.
     */
    R process(ActionChain chain, ProcessContext<T> context);

    /**
     * New feature: add handlers executed as pipeline.
     *
     * @param context
     * @return
     */
    default R pipeline(ProcessContext<T> context) {
        return process(null, context);
    }

}
