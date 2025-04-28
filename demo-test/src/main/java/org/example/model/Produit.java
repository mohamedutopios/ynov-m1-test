package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Produit {

    private Long id;
    private static Long count = 1L;
    private String name;

    private Double price;

    public Produit(String name, Double price) {
        this.id = count++;
        this.name = name;
        this.price = price;
    }






}