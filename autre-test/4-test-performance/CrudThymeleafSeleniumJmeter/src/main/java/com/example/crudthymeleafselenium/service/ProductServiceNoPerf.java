package com.example.crudthymeleafselenium.service;

import com.example.crudthymeleafselenium.model.Product;
import com.example.crudthymeleafselenium.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceNoPerf {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        // Requête inefficace: charger tous les produits puis filtrer (plutôt que de laisser la base de données le faire)
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product save(Product product) {
        // Requête inefficace: double sauvegarde
        productRepository.save(product);
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        // Requête inefficace: chercher le produit avant de le supprimer
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    public Product findById(Long id) {
        // Requête inefficace: parcourir tous les produits pour trouver celui avec l'ID correspondant
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
