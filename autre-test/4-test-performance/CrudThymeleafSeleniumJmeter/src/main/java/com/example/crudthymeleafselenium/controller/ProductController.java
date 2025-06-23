package com.example.crudthymeleafselenium.controller;

import com.example.crudthymeleafselenium.model.Product;
import com.example.crudthymeleafselenium.service.ProductService;
import com.example.crudthymeleafselenium.service.ProductServiceNoPerf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

       @Autowired
   //private ProductService productService;
   private ProductServiceNoPerf productService;


    @GetMapping
    public List<Product> getAllProducts() {
        // Requête optimisée
        return productService.findAll();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        // Requête optimisée: sauvegarde unique
        return productService.save(product);
    }

    @PutMapping("/edit")
    public Product updateProduct(@RequestBody Product product) {
        // Requête optimisée: sauvegarde unique
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // Requête optimisée: suppression directe par ID
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // Requête optimisée: utilisation de la méthode findById de JpaRepository
        return productService.findById(id);
    }
}
