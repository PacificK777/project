package com.productservice.productservice09april.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    /*
    the first object that you want to be injectable is rest template
     */
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
