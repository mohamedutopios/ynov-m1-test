package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonTest {

    @BeforeAll
    static void initAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    static void endAll(){
        System.out.println("afterAll");
    }

    @BeforeEach
    void init(){
        System.out.println("beforeEach");
    }

    @AfterEach
    void end(){
        System.out.println("afterEach");
    }

    @Test
    @DisplayName("Simple test")
    void simpleTest(){
        System.out.println("Simple Test");
        assertTrue(true);
    }

    @Test
    @DisplayName("Simple test2")
    void simpleTest2(){
        System.out.println("Simple Test2");
        assertTrue(true);
    }


}
