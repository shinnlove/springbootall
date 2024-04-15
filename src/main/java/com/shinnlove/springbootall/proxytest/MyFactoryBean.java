/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.proxytest;

import com.shinnlove.springbootall.proxytest.impl.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Tony Zhao
 * @version $Id: MyFactoryBean.java, v 0.1 2024-04-14 12:07 PM Tony Zhao Exp $$
 */
public class MyFactoryBean {

    private MyFactoryBean() {
    }

    public static UserService getJDKDynamicProxyInstance() {
        // target ： 目标类
        final UserService userService = new UserServiceImpl();
        // Aspect : 切面类
        final MyJDKAspect myJDKAspect = new MyJDKAspect();
        // Weaving : 织入，也就是产生代理的过程
        UserService dynamicProxy = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class}, (Object proxy, Method method, Object[] args) -> {
                    // 模拟切点 - pointcut
                    if ("speak".equals(method.getName())) {
                        myJDKAspect.before();
                    }
                    return method.invoke(userService, args);
                });
        return dynamicProxy;
    }

    public static UserService getCglibInstance() {
        // target ： 目标类
        final UserService userService = new UserServiceImpl();
        // Aspect : 切面类
        final MyCglibAspect myCglibAspect = new MyCglibAspect();
        // Weaving : 织入，也就是产生代理的过程
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userService.getClass());
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 模拟 pointcut-切点
                if ("speak".equals(method.getName())) {
                    myCglibAspect.before();
                }
                return methodProxy.invokeSuper(o, objects);
            }
        });
        return (UserService) enhancer.create();
    }

    public static void main(String[] args) {
        UserService cglibProxyInstance = MyFactoryBean.getCglibInstance();
        UserService JdkProxyInstance = MyFactoryBean.getJDKDynamicProxyInstance();

        cglibProxyInstance.speak();
        JdkProxyInstance.speak();
    }

}