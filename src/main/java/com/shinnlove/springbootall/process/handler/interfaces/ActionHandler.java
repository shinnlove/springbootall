/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.handler.interfaces;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

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
public interface ActionHandler<T, R> extends BaseHandler {

    /**
     * @return 
     */
    default Class<?> paramType() {
        Type[] types = this.getClass().getGenericInterfaces();
        ParameterizedTypeImpl pType = (ParameterizedTypeImpl) types[0];
        Type[] genericTypes = pType.getActualTypeArguments();
        return (Class<?>) genericTypes[0];
    }

    /**
     * @param handlers 
     * @param index
     * @param result
     * @param x
     */
    @SuppressWarnings("rawtypes")
    default void cache(final List<ActionHandler> handlers, final int index, final R result,
                       ProcessContext<T> x) {
        x.store(handlers.get(index), result.getClass(), result, false);
    }

    /**
     * Get the original input params from data context holds by process context.
     * 
     * @param x 
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    default <V> V param(ProcessContext<T> x) {
        String name = this.getClass().getName();
        Class<V> c = (Class<V>) x.getInputClass().get(name);
        Object o = x.getInputObject().get(name);

        return cast(c, o);
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
    default <V> V results(ProcessContext x, Class<? extends ActionHandler> clazz) {
        String cn = clazz.getName();
        Class<V> c = Optional.ofNullable(x.getResultClass().get(cn)).map(r -> (Class<V>) r)
            .orElse(null);
        Object o = Optional.ofNullable(x.getResultObject().get(cn)).orElse(null);

        return cast(c, o);
    }

    /**
     * default implementation of this interface for chain control reverse and result K-Class store.
     *
     * @param c     the handler's chain
     * @param x     the process context
     */
    default void doProcess(ActionChain c, ProcessContext<T> x) {

        Object data = x.getDataContext().getData();
        Class<?> dataClass = data.getClass();
        Class<?> handlerType = paramType();

        if (!dataClass.isAssignableFrom(handlerType)) {
            // search for inner type
            for (Field f : dataClass.getDeclaredFields()) {
                Class<?> cls = f.getType();
                if (!isBasicType(cls) && cls.isAssignableFrom(handlerType)) {
                    data = fValue(data, f);
                    break;
                }
            }
        }

        x.store(this, handlerType, data, true);
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
