package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Event;
import model.EventLog;
import model.Recipe;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the underlying functions that support RecipeConnect's GUI
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
    public Recipe randomRecipe() {
        if (!recipes.isEmpty()) {
            Recipe randomRecipe = recipes.get(random.nextInt(recipes.size()));
            EventLog.getInstance().logEvent(new Event("Selected random recipe: " + randomRecipe.getRecipeName()));
            return randomRecipe;
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves current list of recipes, and prints corresponding message
    public void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipes);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("Saved recipes to " + JSON_STORE));
            System.out.println("Saved recipes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads recipes from saved file, and prints corresponding message
    public void loadRecipes() {
        try {
            recipes = jsonReader.read();
            EventLog.getInstance().logEvent(new Event("Loaded recipes from " + JSON_STORE));
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

    // MODIFIES: this
    // EFFECTS: Deletes selected recipe, and prints message
    public void deleteRecipe(int index) {
        if (index >= 0 && index < recipes.size()) {
            Recipe recipe = recipes.remove(index);
            EventLog.getInstance().logEvent(new Event("Deleted recipe: " + recipe.getRecipeName()));
        } else {
            System.out.println("Invalid index.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added recipe: " + recipe.getRecipeName()));
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

    // MODIFIES: this
    // EFFECTS: Initializes new list of recipes and random function
    private void initialize() {
        this.recipes = new ArrayList<>();
        random = new Random();
    }

}
