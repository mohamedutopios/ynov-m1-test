package org.example.assertAll;

import org.example.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {


    @Test
    void testAttributeUtilisateur(){
        Utilisateur utilisateur = new Utilisateur("Dorian",78);
        assertAll("verification attribut utilisateur",
                ()-> assertEquals("Dorian", utilisateur.getNom(),
                        "Le nom doit etre Dorian"),
                ()-> assertEquals(78, utilisateur.getAge(),
                        "L'age doit etre de 78"),
                ()-> assertNotEquals("", utilisateur.getNom(),
                        "L'arribut nom ne doit pas être vide"));
    }

}
