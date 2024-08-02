package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// RecipeConnect application
public class RecipeConnectTerminal {
    private List<Recipe> recipe;
    private Scanner scanner;
    private boolean isRunning;
    private Random random;
    private static final String JSON_STORE = "./data/recipes.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs the RecipeConnect application
    public RecipeConnectTerminal() {
        initialize();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        System.out.println("Welcome to the RecipeConnect Application!");
        while (this.isRunning) {
            console();
        }
    }

    // EFFECTS: Generates and prints a random recipe from the recipe list
    public void randomRecipe() {
        if (!recipe.isEmpty()) {
            System.out.println(recipe.get(random.nextInt(recipe.size())));
        } else {
            System.out.println("Recipe list is empty.");
        }
    }

    // EFFECTS: Executes the system for user interaction.
    public void console() {
        displayMenu();
        String userInput = this.scanner.nextLine();
        executeCommand(userInput);
    }

    // EFFECTS: Processes the main menu
    public void executeCommand(String userInput) {
        switch (userInput) {
            case "a":
                addNewRecipe();
                break;
            case "d":
                deleteRecipe();
                break;
            case "v":
                viewAllRecipes();
                break;
            case "x":
                exitApplication();
                break;
            case "s":
                saveRecipes();
                break;
            case "l":
                loadRecipes();
                break;
            default:
                System.out.println("Please choose a valid option.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Quits the application
    public void exitApplication() {
        saveRecipes();
        System.out.println("See you next time!");
        this.isRunning = false;
    }

    // MODIFIES: ...
    // EFFECTS: Saves current recipe list.
    public void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipe);
            jsonWriter.close();
            System.out.println("Saved recipes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to " + JSON_STORE);
        }
    }

    // MODIFIES: ...
    // EFFECTS: Loads recipe list from previously saved file.
    public void loadRecipes() {
        try {
            recipe = jsonReader.read();
            System.out.println("Loaded recipes from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Prints the list of recipes
    public void printAllRecipes() {
        int index = 1;
        System.out.println("List of recipes:");
        for (Recipe r : recipe) {
            System.out.println(index + ": " + r);
            index++;
        }
    }

    // MODIFIES: this
    // EFFECTS: Deletes a recipe
    public void deleteRecipe() {
        if (recipe.size() > 0) {
            System.out.println("Enter the number associated with the recipe you wish to be deleted");
            printAllRecipes();
            int deleteRecipeInput = Integer.valueOf(scanner.nextLine());
            if (deleteRecipeInput <= recipe.size()) {
                recipe.remove(deleteRecipeInput - 1);
            }
            System.out.println("Recipe has been successfully deleted!");
        } else {
            System.out.println("Recipe list is empty");
        }
    }

    // EFFECTS: Displays options for adding ingredients or instructions to a recipe
    public void addNewRecipeSelection() {
        System.out.println("Choose one of the following:");
        System.out.println("i: Add ingredients?");
        System.out.println("n: Add Instructions?");
        System.out.println("b: Return to menu");
    }

    // EFFECTS: Checks whether recipe name already exists in the recipe list
    public boolean doesRecipeExist(String recipeName) {
        for (Recipe r : recipe) {
            if (r.getRecipeName().equals(recipeName)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Adds a new recipe to the recipe list
    public void addNewRecipe() {
        System.out.println("Enter a name for the recipe:");
        String recipeName = scanner.nextLine();
        if (doesRecipeExist(recipeName)) {
            System.out.println("Recipe already exists... Returning to main menu.");
            return;
        }
        Recipe newRecipe = new Recipe(recipeName);
        recipe.add(newRecipe);
        addNewRecipeSelection();
        while (true) {
            String choice = scanner.nextLine();
            if (choice.equals("i")) {
                addIngredients(newRecipe);
            } else if (choice.equals("n")) {
                addInstructions(newRecipe);
            } else if (choice.equals("b")) {
                System.out.println("Returning to menu...");
                return;
            } else {
                System.out.println("Please choose a valid option.");
            }
            addNewRecipeSelection();
        }
    }

    // EFFECTS: Displays a selection menu to view a recipe
    public void viewAllRecipesSelection() {
        System.out.println("Would you like to view or generate a random recipe?");
        System.out.println("v: View specific recipe");
        System.out.println("r: Generate a random recipe");
        System.out.println("n: No");
    }

    // MODIFIES: this
    // EFFECTS: Prints all recipes; otherwise, indicate that the recipe list is
    // empty
    public void viewAllRecipes() {
        if (recipe.size() > 0) {
            printAllRecipes();
            viewAllRecipesSelection();
            String yesNo = scanner.nextLine();
            switch (yesNo) {
                case "v":
                    System.out.println("Which recipe?");
                    int number = Integer.valueOf(scanner.nextLine()) - 1;
                    printRecipe(recipe.get(number));
                    break;
                case "r":
                    randomRecipe();
                    break;
                case "n":
                    System.out.println("Returning to menu");
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        } else {
            System.out.println("Recipe list is empty.");
        }
    }

    // EFFECTS: Prints the ingredients and instructions of a recipe
    public void printRecipe(Recipe newRecipe) {
        System.out.println("Recipe: " + newRecipe.getRecipeName());
        System.out.println("Ingredients:");
        List<RecipeIngredient> ingredients = newRecipe.getListOfIngredients();
        List<String> quantities = newRecipe.getIngredientQuantity();
        if (ingredients.isEmpty()) {
            System.out.println("No ingredients added.");
        } else {
            for (int i = 0; i < ingredients.size(); i++) {
                System.out.println(ingredients.get(i) + ", " + quantities.get(i));
            }
        }
        System.out.println("Instructions:");
        List<String> instructions = newRecipe.getRecipeInstructions();
        if (instructions.isEmpty()) {
            System.out.println("No instructions added.");
        } else {
            for (String instruction : instructions) {
                System.out.println(instruction);
            }
        }
    }

    // REQUIRES: newRecipe is not null
    // MODIFIES: newRecipe
    // EFFECTS: Add ingredient (and its quantity (optional)) into a recipe
    public void addIngredients(Recipe newRecipe) {
        System.out.println("Add the name of the ingredient:");
        String ingredName = scanner.nextLine();
        Ingredient ingredientName = new Ingredient(ingredName);
        System.out.println("Add the quantity of the ingredient:");
        String ingredientQuantity = scanner.nextLine();
        RecipeIngredient newIngredient = new RecipeIngredient(ingredientName, ingredientQuantity);
        newRecipe.addIngredient(newIngredient);
        System.out.println(newIngredient + " successfully added!");
    }

    // REQUIRES: newRecipe is not null
    // MODIFIES: newRecipe
    // EFFECTS: Adds instructions to the given recipe
    public void addInstructions(Recipe newRecipe) {
        while (true) {
            System.out.println("Enter an instruction:");
            String instruction = scanner.nextLine();
            newRecipe.addInstruction(instruction);
            System.out.println("Instruction successfully added!");
            System.out.println("a: Add more instructions?");
            System.out.println("b: Return to menu");
            String cont = scanner.nextLine();
            if (cont.equals("b")) {
                System.out.println("Returning to menu...");
                break;
            } else if (cont.equals("a")) {
                continue;
            } else {
                System.out.println("Please choose a valid option.");
            }
        }
    }

    // EFFECTS: Displays a menu with possible actions.
    public void displayMenu() {
        System.out.println("Select one of the following options:\n");
        System.out.println("a: Add a recipe");
        System.out.println("d: Delete a recipe");
        System.out.println("v: View all recipes");
        System.out.println("x: Exit application");
        System.out.println("");
        System.out.println("s: Save recipe(s)");
        System.out.println("l: Load existing file");
    }

    public List<Recipe> getRecipeList() {
        return recipe;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the RecipeConnect application.
    public void initialize() {
        this.recipe = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        random = new Random();
    }

}