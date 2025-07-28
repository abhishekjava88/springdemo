package com.abhi.training.demo.config;

import com.abhi.training.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class demoConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public ProductService getProductService(){
        return new ProductService(getRestTemplate());
    }
}
