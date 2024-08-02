package ui.Dialogues;

import model.Recipe;
import ui.RecipeConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRecipes extends JDialog {
    private RecipeConnect recipeConnect;
    private JList<Recipe> recipeList;
    private DefaultListModel<Recipe> listModel;

    public ViewRecipes(JFrame parent, RecipeConnect recipeConnect) {
        super(parent, "View All Recipes", true);
        this.recipeConnect = recipeConnect;
        setupForm();
    }

    private void setupForm() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Recipe recipe : recipeConnect.getRecipeList()) {
            listModel.addElement(recipe);
        }

        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.setVisibleRowCount(10);
        recipeList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Recipe) {
                    ((JLabel) renderer).setText(((Recipe) value).getRecipeName());
                }
                return renderer;
            }
        });

        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        JButton viewButton = new JButton("View Recipe");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRecipe();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(getParent());
    }

    private void viewRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            JOptionPane.showMessageDialog(this, selectedRecipe.getRecipeDetails(), "Recipe Details",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a recipe to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}