package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for Recipe Connect
public class RecipeConnectGUI {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    private JFrame frame;
    private JPanel panel;

    public static void main(String[] args) {
        new RecipeConnectGUI();
    }

    public RecipeConnectGUI() {
        frame = new JFrame("Recipe Connect");
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        panel.setLayout(new GridLayout(7, 1));

        addButton("Add Recipe", e -> showDummyMessage("Add Recipe button clicked"));
        addButton("Delete Recipe", e -> showDummyMessage("Delete Recipe button clicked"));
        addButton("View All Recipes", e -> showDummyMessage("View All Recipes button clicked"));
        addButton("Save Recipes", e -> showDummyMessage("Save Recipes button clicked"));
        addButton("Load Recipes", e -> showDummyMessage("Load Recipes button clicked"));
        addButton("Exit", e -> showDummyMessage("Exit button clicked"));

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        ImageIcon image = new ImageIcon(
                "C:\\Users\\Lenovo\\Desktop\\ProjectStarter\\src\\main\\ui\\RecipeConnectLogo.png");
        frame.setIconImage(image.getImage());
    }

    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void showDummyMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
