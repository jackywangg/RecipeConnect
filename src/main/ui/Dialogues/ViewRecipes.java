
package ui.dialogues;

import model.Recipe;
import model.RecipeConnect;

import javax.swing.*;
import java.awt.*;

// Represents the "View Recipes" Function in GUI
public class ViewRecipes extends JDialog {
    private RecipeConnect recipeConnect;
    private JList<Recipe> recipeList;
    private DefaultListModel<Recipe> listModel;

    // EFFECTS: Sets up the ViewRecipes function
    public ViewRecipes(JFrame parent, RecipeConnect recipeConnect) {
        super(parent, "View All Recipes", true);
        this.recipeConnect = recipeConnect;
        setup();
    }

    // MODIFIES: this
    // EFFECTS: Sets up ViewRecipe Panel
    private void setup() {
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        for (Recipe recipe : recipeConnect.getRecipeList()) {
            listModel.addElement(recipe);
        }
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.setVisibleRowCount(10);
        add(new JScrollPane(recipeList), BorderLayout.CENTER);
        setupButtonPanel();
        setSize(400, 300);
        setLocationRelativeTo(getParent());
    }

    // MODIFIES: this
    // EFFECTS: Sets up ViewRecipe's bottom button fields
    private void setupButtonPanel() {
        JButton viewButton = new JButton("View Recipe");
        JButton randomRecipeButton = new JButton("Random Recipe");
        JButton deleteARecipe = new JButton("Delete a Recipe");
        viewButton.addActionListener(e -> viewRecipe());
        randomRecipeButton.addActionListener(e -> viewRandomRecipe());
        deleteARecipe.addActionListener(e -> deleteRecipe());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        buttonPanel.add(randomRecipeButton);
        buttonPanel.add(deleteARecipe);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Facilitates the "View Recipe" button.
    private void viewRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            showRecipeDialog(selectedRecipe.getRecipeDetails());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a recipe to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Produces dialog upon selecting a recipe
    private void showRecipeDialog(String message) {
        ImageIcon i = new ImageIcon("pics\\food icon.jpg");
        Image newImage = i.getImage();
        Image newImageIcon = newImage.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newImageIcon);
        JOptionPane.showMessageDialog(this, message, "Recipe Details", JOptionPane.PLAIN_MESSAGE, imageIcon);
    }

    // MODIFIES: this
    // EFFECTS: Application selects a random recipe from existing list and displays
    // its details.
    private void viewRandomRecipe() {
        Recipe randomRecipe = recipeConnect.randomRecipe();
        if (randomRecipe != null) {
            JOptionPane.showMessageDialog(this, randomRecipe.getRecipeDetails(), "Random Recipe Details",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            ImageIcon i = new ImageIcon("pics\\SadFace No Random Recipes.png");
            Image newImage = i.getImage();
            Image newImageIcon = newImage.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(newImageIcon);
            JOptionPane.showMessageDialog(this, "No recipes available.", "Error", JOptionPane.ERROR_MESSAGE, imageIcon);
        }
    }

    // MODIFIES: this
    // EFFECTS: Deletes the selected recipe from the list.
    private void deleteRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this recipe?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                int index = recipeList.getSelectedIndex();
                recipeConnect.deleteRecipe(index);
                listModel.removeElement(selectedRecipe);
                JOptionPane.showMessageDialog(this, "Recipe deleted successfully!", "Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a recipe to delete.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
