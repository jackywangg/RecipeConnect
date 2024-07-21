package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRecipeIngredient {
    RecipeIngredient rsSalt;
    Ingredient salt;

    @BeforeEach
    void runBefore() {
        salt = new Ingredient("Salt");
        rsSalt = new RecipeIngredient(salt, "10");
    }

    @Test
    void testGetIngredient() {
        assertEquals(rsSalt.getIngredient().getName(), "Salt");
    }

    @Test
    void testGetQuantity() {
        assertEquals(rsSalt.getQuantity(), "10");
    }

    @Test
    void testSetIngredient() {
        assertEquals(rsSalt.getIngredient().getName(), "Salt");
        Ingredient seaSalt = new Ingredient("Sea Salt");
        rsSalt.setIngredient(seaSalt);
        assertEquals(rsSalt.getIngredient().getName(), "Sea Salt");
    }

    @Test
    void testSetQuantity() {
        assertEquals(rsSalt.getQuantity(), "10");
        rsSalt.setQuantity("20");
        assertEquals(rsSalt.getQuantity(), "20");
    }
}