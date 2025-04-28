package org.example.productTest;

import org.example.enums.Categorie;
import org.example.model.Item;
import org.example.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest {

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemService();
    }


    @ParameterizedTest
    @CsvSource({
            "Ordinateur, 1000, ELECTRONIQUE",
            "Chaise, 50, MEUBLE",
            "Pomme,1,ALIMENTAIRE"
    })
    void testAjoutItemCsvSource(String nom, double prix, Categorie categorie){

        Item item = itemService.ajouterItem(1,nom, prix,categorie);

        assertAll("Verification des proprietes de l'Item",
                () -> assertEquals(nom, item.getName()),
                () -> assertEquals(prix, item.getPrice()),
                () -> assertEquals(categorie, item.getCategorie()),
                () -> {
                    switch (categorie) {
                        case ELECTRONIQUE -> assertTrue(item.getPrice()>100);
                        case MEUBLE -> assertTrue(item.getPrice()>20 && item.getPrice()<500);
                        case ALIMENTAIRE -> assertTrue(item.getPrice()<20);
                    }

                }
                );
    }




}
