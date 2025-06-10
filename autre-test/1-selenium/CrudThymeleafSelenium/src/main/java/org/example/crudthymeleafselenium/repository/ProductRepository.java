package org.example.crudthymeleafselenium.repository;

import org.example.crudthymeleafselenium.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

