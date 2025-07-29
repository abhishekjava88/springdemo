package com.abhi.training.demo.service;

import com.abhi.training.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public Optional<List<Product>> getAllProducts() {
      ResponseEntity<List<Product>> response = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
        return Optional.ofNullable(response.getBody());
    }

    public Optional<List<Product>> getWebClientProducts() {
        List<Product> response = webClient.get()
                .uri("/products")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .map(errorbody ->new RuntimeException("Client Error :" + errorbody)))
                .bodyToFlux(Product.class).collectList() // convert Flux<Product> to Mono<List<Product>>
                .block();
        return Optional.ofNullable(response);
    }
}
