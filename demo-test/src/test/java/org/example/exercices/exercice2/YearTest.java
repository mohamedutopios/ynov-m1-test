package org.example.exercices.exercice2;

import org.example.model.Year;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearTest {

    private Year year;

    @BeforeEach
    void init(){
        year = new Year();
    }

    @Test
    void testAnneeDivisiblePar4MaisPasPar100EstBissextile() {
        assertTrue(year.isLeap(2024));
    }

    @Test
    void testAnneeDivisiblePar400EstBissextile() {
        assertTrue(year.isLeap(2000));
    }

    @Test
    void testAnneeDivisiblePar100MaisPasPar400NestPasBissextile() {
        assertFalse(year.isLeap(1900));
    }

    @Test
    void testAnneeNonDivisiblePar4NestPasBissextile() {
        assertFalse(year.isLeap(2019));
    }

    @Test
    void testAnneeDivisiblePar4000EstBissextile() {
        assertTrue(year.isLeap(4000));
    }
}
