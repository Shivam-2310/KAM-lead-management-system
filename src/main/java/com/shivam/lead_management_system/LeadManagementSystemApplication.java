package com.shivam.lead_management_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivam.lead_management_system.config.NdjsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LeadManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadManagementSystemApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		restTemplate.getMessageConverters().add(new NdjsonHttpMessageConverter(objectMapper));
		return restTemplate;
	}

}
