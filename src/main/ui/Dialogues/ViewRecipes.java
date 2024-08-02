package ui.Dialogues;

import model.Recipe;
import ui.RecipeConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class for Viewing Recipe(s) Function
public class ViewRecipes extends JDialog {
    private RecipeConnect recipeConnect;
    private JList<Recipe> recipeList;
    private DefaultListModel<Recipe> listModel;

    // EFFECTS: Constructs ViewRecipes function
    public ViewRecipes(JFrame parent, RecipeConnect recipeConnect) {
        super(parent, "View All Recipes", true);
        this.recipeConnect = recipeConnect;
        setup();
    }

    // EFFECTS: Sets up ViewRecipe Selection
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

    // EFFECTS: Sets up the button & panel for ViewRecipe
    private void setupButtonPanel() {
        JButton viewButton = new JButton("View Recipe");
        viewButton.addActionListener(e -> viewRecipe());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: Panels involving the view recipe function when selected.
    private void viewRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            showRecipeDialog(selectedRecipe.getRecipeDetails());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a recipe to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Information upon selecting a recipe
    private void showRecipeDialog(String message) {
        ImageIcon i = new ImageIcon("src\\main\\ui\\food icon.jpg");
        Image newImage = i.getImage();
        Image newImageIcon = newImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newImageIcon);
        JOptionPane.showMessageDialog(this, message, "Recipe Details", JOptionPane.PLAIN_MESSAGE, imageIcon);
    }
}