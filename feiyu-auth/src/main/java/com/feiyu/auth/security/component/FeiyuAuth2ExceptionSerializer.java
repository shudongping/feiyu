package com.feiyu.auth.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Author shudongping
 * @Date 2019/3/27
 */
public class FeiyuAuth2ExceptionSerializer extends StdSerializer<FeiyuAuth2Exception> {


    public FeiyuAuth2ExceptionSerializer() {
        super(FeiyuAuth2Exception.class);
    }

    @Override
    public void serialize(FeiyuAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("code", e.getCode());
        jsonGenerator.writeStringField("message", e.getMessage());
        jsonGenerator.writeObjectField("data", null);
        jsonGenerator.writeEndObject();
    }
}
