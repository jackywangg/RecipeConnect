package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRecipe {
    RecipeIngredient recipeSalt;
    RecipeIngredient recipePepper;
    Ingredient salt;
    Ingredient pepper;
    Recipe recipe;

    @BeforeEach
    void runBefore() {
        recipe = new Recipe("curry");
        salt = new Ingredient("Salt");
        pepper = new Ingredient("Black Pepper");
        recipeSalt = new RecipeIngredient(salt, "10");
        recipePepper = new RecipeIngredient(pepper, "20");
    }

    @Test
    void testConstructor() {
        assertEquals(recipe.getRecipeName(), "curry");
    }

    @Test
    void testViewIngredientsEmpty() {
        assertTrue(recipe.getListOfIngredients().isEmpty());
    }

    @Test
    void testAddIngredient() {
        recipe.addIngredient(recipeSalt);
        assertEquals(recipe.getListOfIngredients().size(), 1);
    }

    @Test
    void testAddMultipleIngredients() {
        recipe.addIngredient(recipeSalt);
        recipe.addIngredient(recipePepper);
        assertEquals(recipe.getListOfIngredients().size(), 2);
    }

    @Test
    void testRemoveIngredient() {
        recipe.addIngredient(recipeSalt);
        assertEquals(recipe.getListOfIngredients().size(), 1);
        recipe.removeIngredient(recipeSalt);
        assertEquals(recipe.getListOfIngredients().size(), 0);
    }

    @Test
    void testRemoveMultipleIngredients() {
        recipe.addIngredient(recipeSalt);
        assertEquals(recipe.getListOfIngredients().size(), 1);
        recipe.addIngredient(recipePepper);
        assertEquals(recipe.getListOfIngredients().size(), 2);
        recipe.removeIngredient(recipeSalt);
        recipe.removeIngredient(recipePepper);
        assertEquals(recipe.getListOfIngredients().size(), 0);
    }

    @Test
    void testGetRecipeName() {
        assertEquals(recipe.getRecipeName(), "curry");
    }

    @Test
    void testEditRecipeName() {
        recipe.editRecipeName("Soup");
        assertEquals(recipe.getRecipeName(), "Soup");
    }

    @Test
    void testGetIngredientsEmpty() {
        assertEquals(recipe.getListOfIngredients().size(), 0);
        assertEquals(recipe.getIngredients(), "There are no ingredients.");
    }

    @Test
    void testGetIngredientsMultiple() {
        recipe.addIngredient(recipeSalt);
        recipe.addIngredient(recipePepper);
        assertEquals(recipe.getIngredients(), "That is all the ingredients in this recipe.");
    }

    @Test
    void testToString() {
        String name = recipe.toString();
        assertEquals(name, "curry");
    }

    @Test
    void testAddInstruction() {
        recipe.addInstruction("Boil water");
        List<String> instructions = recipe.getRecipeInstructions();
        assertEquals(instructions.size(), 1);
        assertEquals(instructions.get(0), "Boil water");
    }

    @Test
    void testRemoveInstruction() {
        recipe.addInstruction("Boil water");
        assertEquals(recipe.getRecipeInstructions().size(), 1);
        recipe.removeInstruction("Boil water");
        assertEquals(recipe.getRecipeInstructions().size(), 0);
    }

    @Test
    void testEditInstruction() {
        recipe.addInstruction("Boil water");
        recipe.editInstruction(0, "Boil water for 10 minutes");
        List<String> instructions = recipe.getRecipeInstructions();
        assertEquals(instructions.size(), 1);
        assertEquals(instructions.get(0), "Boil water for 10 minutes");
    }

    @Test
    void testGetRecipeInstructions() {
        recipe.addInstruction("Boil water");
        recipe.addInstruction("Add salt");
        List<String> instructions = recipe.getRecipeInstructions();
        assertEquals(instructions.size(), 2);
        assertEquals(instructions.get(0), "Boil water");
        assertEquals(instructions.get(1), "Add salt");
    }

    @Test
    void testGetIngredientQuantity() {
        recipe.addIngredient(recipeSalt);
        recipe.addIngredient(recipePepper);
        List<String> quantities = recipe.getIngredientQuantity();
        assertEquals(quantities.size(), 2);
        assertEquals(quantities.get(0), "10");
        assertEquals(quantities.get(1), "20");
    }

    @Test
    void testRemoveInstructionNotInRecipe() {
        recipe.addInstruction("Boil water");
        recipe.removeInstruction("Add salt");
        List<String> instructions = recipe.getRecipeInstructions();
        assertEquals(instructions.size(), 1);
        assertEquals(instructions.get(0), "Boil water");
    }

    @Test
    void testGetQuantity() {
        recipe.addIngredient(recipeSalt);
        recipe.addIngredient(recipePepper);
        String quantities = recipe.getQuantity();
        assertNotNull(quantities);
    }

    @Test
    void testGetQuantityEmpty() {
        String quantities = recipe.getQuantity();
        assertNull(quantities);
    }
    
}
