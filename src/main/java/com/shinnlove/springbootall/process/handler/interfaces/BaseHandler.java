/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.handler.interfaces;

import static com.shinnlove.springbootall.process.consts.MetadataConstant.*;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.shinnlove.springbootall.process.wrap.ReflectWrapWithResult;

/**
 * @author Tony Zhao
 * @version $Id: BaseHandler.java, v 0.1 2022-02-12 10:23 PM Tony Zhao Exp $$
 */
public interface BaseHandler {

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

    default Object fValue(final Object o, final Field f) {
        if (o == null || f == null) {
            return null;
        }
        ReflectionUtils.makeAccessible(f);
        return reflect(() -> f.get(o));
    }

    default boolean isBasicType(Class<?> clazz) {
        return isBasicType(clazz.getName());
    }

    default boolean isBasicType(String fieldTypeName) {
        if (fieldTypeName != null) {
            if (LONG_CLASS.equals(fieldTypeName) || INTEGER_CLASS.equals(fieldTypeName)
                || BOOLEAN_CLASS.equals(fieldTypeName) || FLOAT_CLASS.equals(fieldTypeName)
                || DOUBLE_CLASS.equals(fieldTypeName) || SMALL_INT_CLASS.equals(fieldTypeName)
                || SMALL_LONG_CLASS.equals(fieldTypeName) || SMALL_FLOAT_CLASS.equals(fieldTypeName)
                || SMALL_DOUBLE_CLASS.equals(fieldTypeName)
                || SMALL_BOOLEAN_CLASS.equals(fieldTypeName)
                || STRING_CLASS.equals(fieldTypeName)) {
                return true;
            }
        }
        return false;
    }

    default Object reflect(ReflectWrapWithResult wrap) {
        try {
            return wrap.call();
        } catch (IllegalAccessException e) {

        } catch (NoSuchFieldException e) {

        } catch (Exception e) {

        }
        return null;
    }

    default Object reflect(ReflectWrapWithResult wrap, boolean suppress) {
        try {
            return wrap.call();
        } catch (IllegalAccessException e) {

        } catch (NoSuchFieldException e) {

        } catch (Exception e) {

        }
        return null;
    }

}
