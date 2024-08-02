package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Recipe;
import persistence.JsonReader;
import persistence.JsonWriter;

// Class to support RecipeConnect's GUI
public class RecipeConnect {
    private List<Recipe> recipes;
    private Random random;
    private static final String JSON_STORE = "./data/recipes.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Initializes RecipeConnect
    public RecipeConnect() {
        initialize();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
    }

    // EFFECTS: Chooses random recipe within recipe list.
    public void randomRecipe() {
        if (!recipes.isEmpty()) {
            System.out.println(recipes.get(random.nextInt(recipes.size())));
        } else {
            System.out.println("Recipe list is empty.");
        }
    }

    // EFFECTS: Saves current list of recipes
    public void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipes);
            jsonWriter.close();
            System.out.println("Saved recipes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to " + JSON_STORE);
        }
    }

    // EFFECTS: Loads recipes from saved file
    public void loadRecipes() {
        try {
            recipes = jsonReader.read();
            System.out.println("Loaded recipes from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Prints all recipes in recipe list
    public void printAllRecipes() {
        int index = 1;
        System.out.println("List of recipes:");
        for (Recipe r : recipes) {
            System.out.println(index + ": " + r);
            index++;
        }
    }

    // EFFECTS: Deletes selected recipe
    public void deleteRecipe(int index) {
        if (index >= 0 && index < recipes.size()) {
            recipes.remove(index);
            System.out.println("Recipe has been successfully deleted!");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // EFFECTS: Checks if recipe name already exists
    public boolean doesRecipeExist(String recipeName) {
        for (Recipe r : recipes) {
            if (r.getRecipeName().equalsIgnoreCase(recipeName)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns list of recipes
    public List<Recipe> getRecipeList() {
        return recipes;
    }

    // EFFECTS: Initializes new list of recipes and random function
    private void initialize() {
        this.recipes = new ArrayList<>();
        random = new Random();
    }
}
