package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeIngredient;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\0illegal:fileName.json");
            writer.open();
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecipeList() {
        try {
            List<Recipe> recipes = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeList.json");
            writer.open();
            writer.write(recipes);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeList.json");
            recipes = reader.read();
            assertEquals(0, recipes.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSingleRecipe() {
        try {
            List<Recipe> recipes = new ArrayList<>();
            Recipe recipe = new Recipe("Recipe1");
            recipe.addIngredient(new RecipeIngredient(new Ingredient("Ingredient1"), "1 cup"));
            recipe.addIngredient(new RecipeIngredient(new Ingredient("Ingredient2"), "2 tsp"));
            recipes.add(recipe);
            JsonWriter writer = new JsonWriter("./data/testWriterSingleRecipe.json");
            writer.open();
            writer.write(recipes);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterSingleRecipe.json");
            recipes = reader.read();
            assertEquals(1, recipes.size());
            Recipe readRecipe = recipes.get(0);
            checkRecipe("Recipe1", readRecipe);
            List<RecipeIngredient> ingredients = readRecipe.getListOfIngredients();
            assertEquals(2, ingredients.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultipleRecipes() {
        try {
            List<Recipe> recipes = new ArrayList<>();
            Recipe recipe1 = new Recipe("Recipe1");
            recipe1.addIngredient(new RecipeIngredient(new Ingredient("Ingredient1"), "1 cup"));
            recipe1.addIngredient(new RecipeIngredient(new Ingredient("Ingredient2"), "2 tsp"));
            recipes.add(recipe1);
            Recipe recipe2 = new Recipe("Recipe2");
            recipe2.addIngredient(new RecipeIngredient(new Ingredient("Ingredient3"), "500 ml"));
            recipes.add(recipe2);
            JsonWriter writer = new JsonWriter("./data/testWriterMultipleRecipes.json");
            writer.open();
            writer.write(recipes);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterMultipleRecipes.json");
            recipes = reader.read();
            assertEquals(2, recipes.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
