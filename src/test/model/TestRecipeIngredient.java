package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRecipeIngredient {
    RecipeIngredient rSalt;
    Ingredient salt;

    @BeforeEach
    void runBefore() {
        salt = new Ingredient("Salt");
        rSalt = new RecipeIngredient(salt, "10");
    }

    @Test
    void testGetIngredient() {
        assertEquals(rSalt.getIngredient().getName(), "Salt");
    }

    @Test
    void testGetQuantity() {
        assertEquals(rSalt.getQuantity(), "10");
    }

    @Test
    void testSetIngredient() {
        assertEquals(rSalt.getIngredient().getName(), "Salt");
        Ingredient seaSalt = new Ingredient("Sea Salt");
        rSalt.setIngredient(seaSalt);
        assertEquals(rSalt.getIngredient().getName(), "Sea Salt");
    }

    @Test
    void testSetQuantity() {
        assertEquals(rSalt.getQuantity(), "10");
        rSalt.setQuantity("20");
        assertEquals(rSalt.getQuantity(), "20");
    }
}