package com.abhi.training.demo.config;

import com.abhi.training.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class demoConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl("https://fakestoreapi.com/").build();
    }

    @Bean
    public ProductService productService(){
        return new ProductService(getRestTemplate(),webClient());
    }
}
