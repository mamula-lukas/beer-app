package com.example.beer_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * @author mamula.lukas@gmail.com
 */
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractMvcTest {
    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected static MockHttpServletRequestBuilder setHeaders(MockHttpServletRequestBuilder builder) {
        return builder.contentType(MediaType.APPLICATION_JSON);
    }

    protected String serialize(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    protected <T> T deserialize(String json, Class<T> type) throws JsonProcessingException {
        return objectMapper.readValue(json, type);
    }

    protected <T> T deserialize(String json, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(json, type);
    }
}
