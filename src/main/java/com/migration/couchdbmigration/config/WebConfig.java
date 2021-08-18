package com.migration.couchdbmigration.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, CouchConfig couchConfig) {
        RestTemplate restTemplate = builder.build();
        restTemplate.getInterceptors()
                .add(new BasicAuthenticationInterceptor(couchConfig.getUsername(), couchConfig.getPassword()));
        return restTemplate;
    }

}
