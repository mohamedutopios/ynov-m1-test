package org.example.calculatrice;

import org.example.model.Calculatrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatriceTest {

    private Calculatrice calculatrice;

    @BeforeEach
    void init(){
        System.out.println("init");
        // Arrange
        calculatrice = new Calculatrice();
    }

    @Test
    void testAddition(){
        // Act
        double res = calculatrice.addition(10, 15);
        // Assert
        assertEquals(25, res);
    }

    @Test
    void testDivision(){
        double res = calculatrice.division(30,2);
        assertEquals(15, res);
    }

    @Test
    void testDivisionParZero(){
        assertThrows(RuntimeException.class,()->{
            calculatrice.division(10,0);
        });
    }


}
