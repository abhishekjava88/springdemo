package com.abhi.training.demo.controller;

import com.abhi.training.demo.model.Product;
import com.abhi.training.demo.service.ProductService;
import com.fasterxml.jackson.datatype.jdk8.OptionalLongDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts()
                .map(products -> ResponseEntity.ok(products))
                .orElseGet(() ->ResponseEntity.notFound().build());
    }
}
