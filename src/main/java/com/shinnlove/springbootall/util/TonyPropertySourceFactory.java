/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.io.IOException;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import com.shinnlove.springbootall.conf.RemoteConfHolder;

/**
 * @author Tony Zhao
 * @version $Id: TonyPropertySourceFactory.java, v 0.1 2022-05-27 2:49 PM Tony Zhao Exp $$
 */
public class TonyPropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name,
                                                  EncodedResource resource) throws IOException {

        String sName = name != null ? name : resource.getResource().getFilename();

        return new TonyPropertySource(sName, RemoteConfHolder.get());
    }

}