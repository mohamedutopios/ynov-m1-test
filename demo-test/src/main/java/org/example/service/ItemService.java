package org.example.service;

import org.example.enums.Categorie;
import org.example.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {



    private List<Item> items = new ArrayList<>();


    public Item ajouterItem(int id, String name, double price, Categorie categorie ) {

        switch (categorie) {
            case ELECTRONIQUE -> {
                if(price<100) throw  new IllegalArgumentException("Le prix ne peut être inférieur à 100");
            }

            case MEUBLE -> {
                if(price<20 || price>500) throw  new IllegalArgumentException("Le prix ne peut être inférieur à 20 et supérieur à 500");
            }
            case ALIMENTAIRE -> {
                if(price > 20)  throw  new IllegalArgumentException("Le prix ne peut être superieur à 20");
            }


        }

        Item item = new Item(id, name, price, categorie);
        items.add(item);
        return item;

    }






}
