/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Tony Zhao
 * @version $Id: LongJsonSerializer.java, v 0.1 2022-03-14 10:17 AM Tony Zhao Exp $$
 */
public class LongJsonSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long value, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException,
                                                          JsonProcessingException {
        String text = (value == null ? null : String.valueOf(value));
        if (text != null) {
            gen.writeString(text);
        }
    }

}