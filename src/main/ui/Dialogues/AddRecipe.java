package ui.dialogues;

import model.Recipe;
import model.RecipeIngredient;
import model.Ingredient;
import ui.RecipeConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the AddRecipe function for RecipeConnect's GUI
public class AddRecipe extends JDialog {
    private RecipeConnect recipeConnect;
    private JTextField recipeNameField;
    private JTextField ingredientNameField;
    private JTextField ingredientQuantityField;
    private DefaultListModel<String> ingredientsModel;
    private JList<String> ingredientsList;
    private DefaultListModel<String> instructionsModel;
    private JList<String> instructionsList;
    private JTextField instructionField;

    // EFFECTS: Initializes JFrame and RecipeConnect for AddingRecipe function
    public AddRecipe(JFrame parent, RecipeConnect recipeConnect) {
        super(parent, "Add Recipe", true);
        this.recipeConnect = recipeConnect;
        setup();
    }

    // MODIFIES: this
    // EFFECTS: Constructor sets up main menu (title and button panel)
    private void setup() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        addLabelField(formPanel, gbc, "Recipe Name:", 0, recipeNameField = new JTextField(20));
        addLabelField(formPanel, gbc, "Ingredient Name:", 1, ingredientNameField = new JTextField(20));
        addLabelField(formPanel, gbc, "Ingredient Quantity (incl. measurement):", 2,
                ingredientQuantityField = new JTextField(20));
        addAddButton(formPanel, gbc, 3, "Add Ingredient", e -> addIngredient());
        ingredientsModel = new DefaultListModel<>();
        ingredientsList = new JList<>(ingredientsModel);
        addScrollPane(formPanel, gbc, 4, ingredientsList);
        addLabelField(formPanel, gbc, "Instructions:", 5, instructionField = new JTextField(20));
        addAddButton(formPanel, gbc, 6, "Add Instruction", e -> addInstruction());
        instructionsModel = new DefaultListModel<>();
        instructionsList = new JList<>(instructionsModel);
        addScrollPane(formPanel, gbc, 7, instructionsList);
        add(formPanel, BorderLayout.CENTER);
        addButtonAddRecipePanel();
        pack();
        setLocationRelativeTo(getParent());
    }


    // MODIFIES: panel
    // EFFECTS: Constructs the labels and fields for RecipeConnect
    private void addLabelField(JPanel panel, GridBagConstraints gbc, String labelText, int y,
            JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(textField, gbc);
    }

    // MODIFIES: panel
    // EFFECTS: Adds a button to a specific panel
    private void addAddButton(JPanel panel, GridBagConstraints gbc, int y, String buttonText, ActionListener listener) {
        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton(buttonText);
        button.addActionListener(listener);
        panel.add(button, gbc);
    }

    // MODIFIES: panel
    // EFFECTS: Adds the scroll pane for the GUI, enabling ability to scroll.
    private void addScrollPane(JPanel panel, GridBagConstraints gbc, int y, JList<String> list) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(list), gbc);
    }

    // MODIFIES: this
    // EFFECTS: Adds the bottom two buttons in the AddRecipe panel.
    private void addButtonAddRecipePanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton(buttonPanel, "Add Recipe", new AddRecipeActionListener());
        addButton(buttonPanel, "Cancel", e -> dispose());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: panel
    // EFFECTS: Adds button to panel
    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    // MODIFIES: ingredientsModel
    // EFFECTS: Adds ingredient (name and quantity) to a list of ingredients
    private void addIngredient() {
        String ingredientName = ingredientNameField.getText();
        String ingredientQuantity = ingredientQuantityField.getText();
        if (!ingredientName.isEmpty() && !ingredientQuantity.isEmpty()) {
            ingredientsModel.addElement(ingredientName + ": " + ingredientQuantity);
            ingredientNameField.setText("");
            ingredientQuantityField.setText("");
        }
    }

    // MODIFIES: instructionsModel
    // EFFECTS: Adds instruction to a list of instruction
    private void addInstruction() {
        String instruction = instructionField.getText();
        if (!instruction.isEmpty()) {
            instructionsModel.addElement(instruction);
            instructionField.setText("");
        }
    }

    // MODIFIES: recipeConnect
    // EFFECTS: Adds non-duplicate recipes to recipe list.
    private void addRecipe() {
        String recipeName = recipeNameField.getText();
        if (!recipeName.isEmpty()) {
            if (recipeConnect.doesRecipeExist(recipeName)) {
                JOptionPane.showMessageDialog(this, "Recipe already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Recipe newRecipe = new Recipe(recipeName);
            for (int i = 0; i < ingredientsModel.size(); i++) {
                String[] parts = ingredientsModel.get(i).split(": ");
                Ingredient ingredient = new Ingredient(parts[0]);
                RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, parts[1]);
                newRecipe.addIngredient(recipeIngredient);
            }
            for (int i = 0; i < instructionsModel.size(); i++) {
                newRecipe.addInstruction(instructionsModel.get(i));
            }
            recipeConnect.getRecipeList().add(newRecipe);
            JOptionPane.showMessageDialog(this, "Recipe added successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Private inner class that implmenets ActionListener
    // Handles the action of addRecipe when button is presed.
    private class AddRecipeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addRecipe();
        }
    }
}
