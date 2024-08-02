package ui;

import javax.swing.*;
import ui.Dialogues.AddRecipe;
import ui.Dialogues.ViewRecipes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeConnectGUI {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    private JFrame frame;
    private JPanel panel;
    private RecipeConnect recipeConnect;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecipeConnectGUI();
        });
    }

    public RecipeConnectGUI() {
        recipeConnect = new RecipeConnect();  
        frame = new JFrame("Recipe Connect");
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        panel.setLayout(new GridLayout(7, 1));

        addButton("Add Recipe", e -> openAddRecipe());
        addButton("Delete Recipe", e -> showDummyMessage("Delete Recipe button clicked"));
        addButton("View All Recipes", e -> openViewRecipes());
        addButton("Save Recipes", e -> showDummyMessage("Save Recipes button clicked"));
        addButton("Load Recipes", e -> showDummyMessage("Load Recipes button clicked"));
        addButton("Exit", e -> {
            frame.dispose();
            System.exit(0);
        });

        frame.add(panel, BorderLayout.CENTER);

        ImageIcon image = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\ProjectStarter\\src\\main\\ui\\RecipeConnectLogo.png");
        frame.setIconImage(image.getImage());

        frame.setVisible(true);
    }

    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void showDummyMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void openAddRecipe() {
        AddRecipe addRecipe = new AddRecipe(frame, recipeConnect);
        addRecipe.setVisible(true);
    }

    private void openViewRecipes() {
        ViewRecipes viewRecipes = new ViewRecipes(frame, recipeConnect);
        viewRecipes.setVisible(true);
    }
}
