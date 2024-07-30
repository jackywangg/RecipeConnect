package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for Recipe Connect
public class RecipeConnectGUI {
    private JFrame frame;
    private JPanel panel;

    public RecipeConnectGUI() {
        frame = new JFrame("Recipe Connect");
        panel = new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
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
    }

    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void showDummyMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecipeConnectGUI());
    }
}
