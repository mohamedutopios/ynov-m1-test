package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonPremierTest {

    @Test
    @DisplayName("test sur l'egalité")
    void testEgalite(){
        assertEquals(4,2+2, "2 + 2 est egal à 4");
    }

    @Test
    @DisplayName("test sur la non egalité")
    void testNonEgalite(){
        assertNotEquals(6,2+2, "2 + 2 n'est pas egal à 6");
    }

    @Test
    @DisplayName("test est ce vrai")
    void testVrai(){
        assertTrue(2+2==4, "2 + 2 == 4 est vrai");
    }

    @Test
    @DisplayName("test est ce faux")
    @Tag("Recuperation")
    void testFalse(){
        assertTrue(2+2==4, "2 + 2 == 6 est faux");
    }

    @Test
    void testNull(){
        Object o = null;
        assertNull(o, "l'objet doit etre null");
    }

    @Test
    void testNotNull() {
        Object o = new Object();
        assertNotNull(o, "l'objet doit n'est pas null");
    }


    @Test
    void testMultiple(){
        assertAll("Plusieurs test",
                ()-> assertEquals(8, 4+4, "2 + 2 doit etre egal à 4"),
                ()-> assertTrue(3+3==6, "3 + 3 doit être egam à 6"));
    }





}
