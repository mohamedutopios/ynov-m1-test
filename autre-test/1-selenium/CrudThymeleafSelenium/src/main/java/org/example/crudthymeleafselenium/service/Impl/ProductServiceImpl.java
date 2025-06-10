package org.example.crudthymeleafselenium.service.Impl;

import org.example.crudthymeleafselenium.model.Product;
import org.example.crudthymeleafselenium.repository.ProductRepository;
import org.example.crudthymeleafselenium.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
