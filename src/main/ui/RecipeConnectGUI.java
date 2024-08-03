package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.Dialogues.AddRecipe;
import ui.Dialogues.ViewRecipes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Initializes and Runs RecipeConnect's GUI 
public class RecipeConnectGUI {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private JFrame frame;
    private JPanel panel;
    private RecipeConnect recipeConnect;

    // EFFECTS: Initializes GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecipeConnectGUI();
        });
    }

    // EFFECTS: RecipeConnect's initial display menu with buttons
    public RecipeConnectGUI() {
        recipeConnect = new RecipeConnect();
        frame = new JFrame("Recipe Connect");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        addButton("Add Recipe", e -> openAddRecipe());
        addButton("View All Recipes", e -> openViewRecipes());
        addButton("Save Recipes", e -> saveRecipes());
        addButton("Load Recipes", e -> loadRecipes());
        addButton("Exit", e -> {
            frame.dispose();
            System.exit(0);
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.add(recipeConnectLabel(), BorderLayout.NORTH);
        ImageIcon image = new ImageIcon(
                "pics\\RecipeConnectLogo.png");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
    }

    // EFFECTS: Creates "RecipeConnect" title for GUI
    private JLabel recipeConnectLabel() {
        JLabel title = new JLabel("RecipeConnect", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setBorder(new EmptyBorder(20, 10, 20, 10));
        return title;
    }

    // EFFECTS: Adds new button
    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
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

    // EFFECTS: Allows user to save current recipe
    private void saveRecipes() {
        recipeConnect.saveRecipes();
        JOptionPane.showMessageDialog(frame, "Recipes have been saved", "Save Recipes",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Allows user to load previously saved recipe
    private void loadRecipes() {
        recipeConnect.loadRecipes();
        JOptionPane.showMessageDialog(frame, "Recipe file loaded", "Load Recipes",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
