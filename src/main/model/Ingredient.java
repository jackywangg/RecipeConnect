package model;

// Represents an ingredient with, optionally, its cost at time of purchase. 
public class Ingredient {
    private String name;
    private int cost;

    // REQUIRES: name is of non-zero length
    // MODIFIES: this
    // EFFECTS: constructs an ingredient with given name
    public Ingredient(String name) {
        this.name = name;
    }

    // REQUIRES: name is of non-zero length.
    // MODIFIES: this
    // EFFECTS: constructs an ingredient with given name and cost
    public Ingredient(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    // MODIFIES: this
    // EFFECTS: updates current name with newName
    public void editName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
