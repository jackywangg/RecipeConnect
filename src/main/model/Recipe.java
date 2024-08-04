package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// Represents a recipe with a collection of ingredients and instructions.
public class Recipe implements Writable {
    private List<RecipeIngredient> recipe;
    private String recipeName;
    private List<String> recipeInstructions;
    private List<RecipeIngredient> listOfIngredients;
    private List<String> listOfIngredientQuantity;

    /*
     * REQUIRES: recipeName must be of non-zero length.
     * EFFECTS: Recipe name is set to recipeName; recipe is created with an empty
     * list of
     * recipeIngredients (ingredient name and its amount) and recipeInstructions
     * (instructions for the recipe).
     */
    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        recipe = new ArrayList<>();
        recipeInstructions = new ArrayList<>();
        listOfIngredients = new ArrayList<>();
        listOfIngredientQuantity = new ArrayList<>();
    }

    // REQUIRES: ingredient not already in recipe
    // MODIFIES: this
    // EFFECTS: adds given ingredient and quantity into recipe
    public void addIngredient(RecipeIngredient recipeIngredient) {
        recipe.add(recipeIngredient);
        listOfIngredients.add(recipeIngredient);
        listOfIngredientQuantity.add(recipeIngredient.getQuantity());
    }

    // REQUIRES: ingredient is in the recipe
    // MODIFIES: this
    // EFFECTS: removes given ingredient from recipe
    public void removeIngredient(RecipeIngredient recipeIngredient) {
        recipe.remove(recipeIngredient);
        listOfIngredients.remove(recipeIngredient);
        listOfIngredientQuantity.remove(recipeIngredient.getQuantity());
    }

    // MODIFIES: this
    // EFFECTS: updates current recipeName with newRecipeName
    public void editRecipeName(String newRecipeName) {
        this.recipeName = newRecipeName;
    }

    // EFFECTS: returns a message indicating the number of ingredients in the
    // recipe.
    // Indicate whether the list is empty or not; if not, then print the
    // ingredients.
    public String getIngredients() {
        if (listOfIngredients.isEmpty()) {
            return "There are no ingredients.";
        } else {
            StringBuilder sb = new StringBuilder("Ingredients:\n");
            for (RecipeIngredient ri : listOfIngredients) {
                sb.append(ri.getIngredient().getName())
                        .append(": ")
                        .append(ri.getQuantity())
                        .append("\n");
            }
            return sb.toString();
        }
    }

    // REQUIRES: instruction not already in recipe
    // MODIFIES: this
    // EFFECTS: adds given instruction into recipe
    public void addInstruction(String instruction) {
        recipeInstructions.add(instruction);
    }

    // REQUIRES: Instruction is in the recipe
    // MODIFIES: this
    // EFFECTS: removes given instruction from recipe
    public void removeInstruction(String instruction) {
        recipeInstructions.remove(instruction);
    }

    // REQUIRES: Index is valid within recipeInstructions
    // MODIFIES: this
    // EFFECTS: edits given instruction in index with newInstruction
    public void editInstruction(int index, String newInstruction) {
        recipeInstructions.set(index, newInstruction);
    }

    // EFFECTS: If the list of ingredient quantities is empty, return null;
    // otherwise,
    // print each quantity in the list and returns an empty string.
    public String getQuantity() {
        if (listOfIngredientQuantity.isEmpty()) {
            return null;
        } else {
            for (String ri : listOfIngredientQuantity) {
                System.out.println(ri);
            }
            return "";
        }
    }

    // EFFECTS: Gets recipe details, including ingredients and instructions.
    public String getRecipeDetails() {
        StringBuilder details = new StringBuilder();
        int i = 1;
        details.append("Recipe Name: ").append(recipeName).append("\n\n");
        details.append(getIngredients()).append("\n");
        details.append("Instructions:\n");
        for (String instruction : recipeInstructions) {
            details.append(i + ". ").append(instruction).append("\n");
            i++;
        }
        return details.toString();
    }

    // EFFECTS: returns ingredients in this list as a JSON array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (RecipeIngredient ingredient : listOfIngredients) {
            jsonArray.put(ingredient.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: Returns instructions in this list as a JSON array
    private JSONArray instructionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String instruction : recipeInstructions) {
            jsonArray.put(instruction);
        }
        return jsonArray;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public List<String> getRecipeInstructions() {
        return recipeInstructions;
    }

    public List<String> getIngredientQuantity() {
        return listOfIngredientQuantity;
    }

    public List<RecipeIngredient> getListOfIngredients() {
        return listOfIngredients;
    }

    @Override
    public String toString() {
        return recipeName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", recipeName);
        json.put("ingredients", ingredientsToJson());
        json.put("instructions", instructionsToJson());
        return json;
    }

}
