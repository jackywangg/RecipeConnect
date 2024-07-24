package persistence;

import model.Recipe;
import model.RecipeIngredient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Recipe> recipes = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeList.json");
        try {
            List<Recipe> recipes = reader.read();
            assertEquals(0, recipes.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
    @Test
    void testReaderSingleRecipe() {
        JsonReader reader = new JsonReader("./data/testReaderSingleRecipe.json");
        try {
            List<Recipe> recipes = reader.read();
            assertEquals(1, recipes.size());

            Recipe recipe = recipes.get(0);
            checkRecipe("Recipe1", recipe);
            List<RecipeIngredient> ingredients = recipe.getListOfIngredients();
            assertEquals(2, ingredients.size());
            checkRecipeIngredient("Ingredient1", "1 cup", ingredients.get(0));
            checkRecipeIngredient("Ingredient2", "2 tsp", ingredients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderMultipleRecipes() {
        JsonReader reader = new JsonReader("./data/testReaderMultipleRecipes.json");
        try {
            List<Recipe> recipes = reader.read();
            assertEquals(2, recipes.size());

            Recipe recipe1 = recipes.get(0);
            checkRecipe("Recipe1", recipe1);
            List<RecipeIngredient> ingredients1 = recipe1.getListOfIngredients();
            assertEquals(2, ingredients1.size());
            checkRecipeIngredient("Ingredient1", "1 cup", ingredients1.get(0));
            checkRecipeIngredient("Ingredient2", "2 tsp", ingredients1.get(1));

            Recipe recipe2 = recipes.get(1);
            checkRecipe("Recipe2", recipe2);
            List<RecipeIngredient> ingredients2 = recipe2.getListOfIngredients();
            assertEquals(1, ingredients2.size());
            checkRecipeIngredient("Ingredient3", "500 ml", ingredients2.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
