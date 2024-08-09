package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TestRecipeConnect {

    private static final String TEST_RECIPE = "./data/testRecipes.json";
    private static final String INVALID_RECIPE_PATH = "./invalid_directory/recipes.json";
    private RecipeConnect recipeConnect;
    private RecipeConnect recipeConnect2;
    private RecipeConnect recipeConnect3;
    private Recipe recipe1;
    private Recipe recipe2;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    @BeforeEach
    void setUp() {
        recipeConnect = new RecipeConnect(TEST_RECIPE);
        recipe1 = new Recipe("Pasta");
        recipe2 = new Recipe("Salad");
        recipeConnect2 = new RecipeConnect(INVALID_RECIPE_PATH);
        recipeConnect3 = new RecipeConnect();
        recipeConnect.addRecipe(recipe1);
        recipeConnect.addRecipe(recipe2);
    }

    @Test
    void testAddRecipe() {
        Recipe recipe3 = new Recipe("Soup");
        recipeConnect.addRecipe(recipe3);
        List<Recipe> recipes = recipeConnect.getRecipeList();
        assertEquals(3, recipes.size());
        assertTrue(recipes.contains(recipe3));
    }

    @Test
    void testDeleteRecipe() {
        recipeConnect.deleteRecipe(0);
        List<Recipe> recipes = recipeConnect.getRecipeList();
        assertEquals(1, recipes.size());
        assertFalse(recipes.contains(recipe1));
        assertTrue(recipes.contains(recipe2));
    }

    @Test
    void testDeleteRecipeInvalidIndex() {
        int initialSize = recipeConnect.getRecipeList().size();
        recipeConnect.deleteRecipe(5);
        List<Recipe> recipes = recipeConnect.getRecipeList();
        assertEquals(initialSize, recipes.size());
    }

    @Test
    void testRandomRecipe() {
        Recipe randomRecipe = recipeConnect.randomRecipe();
        assertNotNull(randomRecipe);
        assertTrue(recipeConnect.getRecipeList().contains(randomRecipe));
    }

    @Test
    void testRandomRecipeEmptyList() {
        recipeConnect = new RecipeConnect();
        assertNull(recipeConnect.randomRecipe());
    }

    @Test
    void testDoesRecipeExist() {
        assertTrue(recipeConnect.doesRecipeExist("Pasta"));
        assertFalse(recipeConnect.doesRecipeExist("Pizza"));
    }

    @Test
    void testSaveRecipes() throws Exception {
        recipeConnect.saveRecipes();
        File file = new File(TEST_RECIPE);
        assertTrue(file.exists());
        String content = new String(Files.readAllBytes(Paths.get(TEST_RECIPE)));
        assertFalse(content.isEmpty());
    }

    @Test
    void testSaveRecipesFileNotFoundException() {
        Recipe recipe = new Recipe("Test Recipe");
        recipeConnect2.addRecipe(recipe);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        recipeConnect2.saveRecipes();
        String expectedOutput = "Unable to write to " + INVALID_RECIPE_PATH + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testLoadRecipes() throws Exception {
        recipeConnect.saveRecipes();
        RecipeConnect newRecipeConnect = new RecipeConnect(TEST_RECIPE);
        newRecipeConnect.loadRecipes();
        List<Recipe> recipes = newRecipeConnect.getRecipeList();
        assertEquals(2, recipes.size());
    }

    @Test
    void testLoadRecipesIOException() throws Exception {
        File file = new File(TEST_RECIPE);
        RecipeConnect newRecipeConnect = new RecipeConnect(TEST_RECIPE);
        newRecipeConnect.loadRecipes();
        assertFalse(newRecipeConnect.getRecipeList().isEmpty());
    }

    @Test
    void testPrintAllRecipes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        recipeConnect.printAllRecipes();
        String expectedOutput = "List of recipes:" + System.lineSeparator()
                + "1: " + recipe1 + System.lineSeparator()
                + "2: " + recipe2 + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testPrintAllRecipesEmptyList() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        recipeConnect3.printAllRecipes();
        String expectedOutput = "List of recipes:" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testDeleteRecipeWithNegativeIndex() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        recipeConnect.deleteRecipe(-1);
        String expectedOutput = "Invalid index." + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        List<Recipe> recipes = recipeConnect.getRecipeList();
        assertEquals(2, recipes.size());
        assertTrue(recipes.contains(recipe1));
        assertTrue(recipes.contains(recipe2));
    }

    @Test
    void testIndexEqual() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        int indexEqualToSize = recipeConnect.getRecipeList().size();
        recipeConnect.deleteRecipe(indexEqualToSize);
        String expectedOutput = "Invalid index." + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        List<Recipe> recipes = recipeConnect.getRecipeList();
        assertEquals(2, recipes.size());
        assertTrue(recipes.contains(recipe1));
        assertTrue(recipes.contains(recipe2));
    }
}
