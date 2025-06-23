package com.example.crudthymeleafselenium.config;

import com.example.crudthymeleafselenium.model.Product;
import com.example.crudthymeleafselenium.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            IntStream.rangeClosed(1, 500).forEach(i -> {
                Product p = new Product();
                p.setName("Product " + i);
                p.setPrice(Math.round(Math.random() * 10000.0) / 100.0); 
                productRepository.save(p);
            });
        };
    }
}
