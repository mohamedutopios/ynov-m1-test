package org.example.productTest;

import org.example.enums.Categorie;
import org.example.model.Produit;
import org.example.service.ProduitService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test de la classe ProduitService")
public class ProduitTest2Arg {

    private ProduitService produitService;

    @BeforeEach
    void init() {
        produitService = new ProduitService();
    }

    @Test
    @DisplayName("Creation de produit et incrementation")
    void testCreateProduitAndIncr() {
        Produit produit = produitService.ajouterProduit("chaise", 23);
        Produit produit1 = produitService.ajouterProduit("Table", 89);

        Assertions.assertAll("Vérification des propriétes des produist",
                () -> assertEquals("chaise", produit.getName(), "Le nom de la chaise doit être correct"),
                () -> assertEquals(23, produit.getPrice(), "Le prix doit etre correct"),
                () -> assertEquals(1, produit.getId(), "Id est egal 1"),
                () -> assertEquals(2, produit1.getId(), "Id est egal 2"),
                () -> assertTrue(produit.getId() > 0, "le produit doit avoir un id"),
                () -> assertTrue(produit.getId() < produit1.getId()));
    }


    @ParameterizedTest
    @ValueSource(strings = {"chaise", "table", "bureau"})
    void testAjoutProduitAvecNomDifferents(String nom) {
        Produit produit = produitService.ajouterProduit(nom, 23);
        assertEquals(nom, produit.getName(), "Le nom du produit doit correspondre aux paramètres");
    }


    @ParameterizedTest
    @EnumSource(Categorie.class)
    void testeCreateProduitAvecCategorie(Categorie categorie) {
        Produit produit = switch (categorie) {
            case ELECTRONIQUE -> new Produit("TELEVISION", 45.0);
            case MEUBLE -> new Produit("CHAISE", 124.0);
            case ALIMENTAIRE -> new Produit("POMME", 2.0);

        };
        assertNotNull(produit);
        assertTrue(produit.getId() > 0, "Le produit doit avoir un id");
        assertFalse(produit.getName().isEmpty(), "Le nom du produit ne doit pas être vide");
    }


    @Test
    @DisplayName("Verification nom produit vide")
    void testNomNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> produitService.ajouterProduit("", 23), "Une exception doit être levée");
    }


    @DisplayName("Verification prix pas null ou négatif")
    @RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testPriceNullOrUnderZero() {
        assertThrows(IllegalArgumentException.class, () -> produitService.ajouterProduit("Chaise", -24), "Une exception doit être levée");
    }


    @Test
    @DisplayName("Supprime un produit")
    void testDeleteProduut() {
        Produit produit = produitService.ajouterProduit("Scooter", 2548.0);
        produitService.supprimeUnProduitParId(produit.getId());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> produitService.trouverUnProduitParId(produit.getId()));

    }

    @Test
    @DisplayName("Trouver un produit par son id")
    @Tag("Modification")
    @Disabled("Pas à tester")
    void testTrouverProduitParId() {
        Produit produit = produitService.ajouterProduit("Iphone", 1458);
        Produit produit1 = produitService.ajouterProduit("IPad", 1700);

        Assertions.assertAll("Vérification de la recupération par Id",
                () -> assertEquals(produit, produitService.trouverUnProduitParId(produit.getId()), "Je dois avoir un produit"),
                () -> assertEquals(produit1, produitService.trouverUnProduitParId(produit1.getId()), "Je dois avoir un produit"),
                () -> Assertions.assertThrows(NoSuchElementException.class, () -> produitService.trouverUnProduitParId(75L), ""));

    }


    @Test
    @Tag("Recuperation")
    @DisplayName("Récupération de tous les produits")
    void testGetAllProduit() {
        produitService.ajouterProduit("chaise", 23);
        produitService.ajouterProduit("table", 45);

        Assertions.assertAll("Verification de la recupéaration par Id",
                () -> Assertions.assertFalse(produitService.listeProduits().isEmpty(), "La liste ne devrait pas être vide"),
                () -> assertEquals(2, produitService.listeProduits().size(), "La liste devrait contenir deux produits"));
    }


    @Test
    @DisplayName("Mise à jour d'un produit")
    void testUpdateProduit() {
        Produit produit = produitService.ajouterProduit("chaise", 23);
        produitService.mettreAJourProduit(produit.getId(), "mobile", 23);
        Produit produitMisAJour = produitService.trouverUnProduitParId(produit.getId());

        Assertions.assertAll("Vérification après mise à jour du produit",
                () -> assertEquals("mobile", produitMisAJour.getName(), "Le nom du produit doit être mis à jour"),
                () -> assertEquals(23, produitMisAJour.getPrice(), "Le prix du produit doit rester inchangé")
        );
    }
}