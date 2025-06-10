package org.example.crudthymeleafselenium.service;

import org.example.crudthymeleafselenium.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product save(Product product);

    public void deleteById(Long id);

    public Product findById(Long id);

}
