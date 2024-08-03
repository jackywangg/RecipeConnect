package ui;

import javax.swing.*;
import ui.Dialogues.AddRecipe;
import ui.Dialogues.ViewRecipes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Initializes and Runs RecipeConnect's GUI 
public class RecipeConnectGUI {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    private JFrame frame;
    private JPanel panel;
    private RecipeConnect recipeConnect;

    // EFFECTS: Initializes GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecipeConnectGUI();
        });
    }

    // EFFECTS: RecipeConnect's Initial Menu
    public RecipeConnectGUI() {
        recipeConnect = new RecipeConnect();
        frame = new JFrame("Recipe Connect");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(7, 1));
        addButton("Add Recipe", e -> openAddRecipe());
        addButton("View All Recipes", e -> openViewRecipes());
        addButton("Save Recipes", e -> showDummyMessage("Save Recipes button clicked"));
        addButton("Load Recipes", e -> showDummyMessage("Load Recipes button clicked"));
        addButton("Exit", e -> {
            frame.dispose();
            System.exit(0);
        });
        frame.add(panel, BorderLayout.CENTER);
        ImageIcon image = new ImageIcon(
                "src\\main\\ui\\RecipeConnectLogo.png");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
    }

    // EFFECTS: Adds new button
    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    // EFFECTS: Current messages to test button
    private void showDummyMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    // EFFECTS: Opens adding recipe function
    private void openAddRecipe() {
        AddRecipe addRecipe = new AddRecipe(frame, recipeConnect);
        addRecipe.setVisible(true);
    }

    // EFFECTS: Opens view recipe(s) function
    private void openViewRecipes() {
        ViewRecipes viewRecipes = new ViewRecipes(frame, recipeConnect);
        viewRecipes.setVisible(true);
    }
}
