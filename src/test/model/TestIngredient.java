package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestIngredient {
    Ingredient pepper;

    @BeforeEach
    void runBefore() {
        pepper = new Ingredient("Pepper");
    }

    @Test
    void testConstructorWithNoCost() {
        Ingredient salt = new Ingredient("Salt");
        assertEquals(salt.getName(), "Salt"); 
    }

    @Test
    void testConstructorWithCost() {
        Ingredient salt = new Ingredient("Salt", 10);
        assertEquals(salt.getCost(), 10);
        assertEquals(salt.getName(), "Salt");
    }

    @Test
    void testEditName() {
        pepper.editName("Peppercorn");
        assertEquals(pepper.getName(), "Peppercorn");
    }
}