package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an ingredient paired with its quantity used for the recipe.
public class RecipeIngredient implements Writable {
    private Ingredient ingredient;
    private String quantity;

    // EFFECTS: Initializes with the given ingredient and quantity
    public RecipeIngredient(Ingredient ingredient, String quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    // MODIFIES: this
    // EFFECT: Set current ingredient to newIngredient
    public void setIngredient(Ingredient newIngredient) {
        this.ingredient = newIngredient;
    }

    // MODIFIES: this
    // EFFECT: Set current quantity to a newQuantity
    public void setQuantity(String newQuantity) {
        this.quantity = newQuantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return ingredient.getName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", ingredient.getName());
        jsonObj.put("quantity", quantity);
        return jsonObj;
    }
}
