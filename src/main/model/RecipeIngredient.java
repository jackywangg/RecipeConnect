package model;

// Represents an ingredient paired with its quantity used for the recipe.
public class RecipeIngredient {
    private Ingredient ingredient;
    private String quantity;

    // MODIFIES: this
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
}
