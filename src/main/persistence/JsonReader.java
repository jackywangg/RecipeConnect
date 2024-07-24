package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// EFFECTS: Represents a reader that reads Recipe from stored JSON data
public class JsonReader {
    private String srcFile;

    // EFFECTS: Constructs reader allowing to read from srcFile.
    public JsonReader(String srcFile) {
        this.srcFile = srcFile;
    }

    // EFFECTS: Reads and returns Recipe file; otherwise, throws
    //          IOException if error occurs.
    public List<Recipe> read() throws IOException {
        String file = readFile(srcFile);
        JSONArray jsonArray = new JSONArray(file);
        return parseRecipes(jsonArray);
    }

    // EFFECTS: Reads srcFile as String and returns it.
    private String readFile(String src) throws IOException {
        StringBuilder stringBuild = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(srcFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuild.append(s));
        }

        return stringBuild.toString();
    }

    // EFFECTS: Parses Recipe from JSON object and returns it.
    private Recipe parseRecipe(JSONObject obj) {
        String name = obj.getString("name");
        Recipe recipe = new Recipe(name);
        addIngredients(recipe, obj);
        addInstructions(recipe, obj);
        return recipe;
    }

    // EFFECTS: Parses Recipes from JSON array and returns it.
    private List<Recipe> parseRecipes(JSONArray jsonArray) {
        List<Recipe> recipes = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObj = (JSONObject) o;
            recipes.add(parseRecipe(jsonObj));
        }
        return recipes;
    }

    // MODIFIES: recipe
    // EFFECTS: Parses Ingredients from JSON object and adds them.
    private void addIngredients(Recipe recipe, JSONObject obj) {
        JSONArray jsonArray = obj.getJSONArray("ingredients");
        for (Object o : jsonArray) {
            JSONObject nextIngredient = (JSONObject) o;
            addIngredient(recipe, nextIngredient);
        }
    }

    // MODIFIES: recipe
    // EFFECTS: Parses ingredient from JSON object and adds it.
    private void addIngredient(Recipe recipe, JSONObject obj) {
        String name = obj.getString("name");
        String quantity = obj.getString("quantity");
        Ingredient ingredient = new Ingredient(name);
        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, quantity);
        recipe.addIngredient(recipeIngredient);
    }

    // MODIFIES: recipe
    // EFFECTS: Parses instructions from JSON object and returns them.
    private void addInstructions(Recipe recipe, JSONObject obj) {
        JSONArray jsonArray = obj.getJSONArray("instructions");
        for (Object o : jsonArray) {
            String instruction = (String) o;
            recipe.addInstruction(instruction);
        }
    }

}
