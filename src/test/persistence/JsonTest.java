package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipeIngredient(String name, String quantity, RecipeIngredient recipeIngredient) {
        assertEquals(name, recipeIngredient.getIngredient().getName());
        assertEquals(quantity, recipeIngredient.getQuantity());
    }

    protected void checkRecipe(String name, Recipe recipe) {
        assertEquals(name, recipe.getRecipeName());
    }
}


