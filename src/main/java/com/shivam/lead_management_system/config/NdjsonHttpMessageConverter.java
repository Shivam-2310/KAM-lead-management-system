package com.shivam.lead_management_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class NdjsonHttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public NdjsonHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, MediaType.parseMediaType("application/x-ndjson"));
    }
}
