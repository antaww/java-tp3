package Utils;

import javax.swing.*;
import java.awt.*;

public class menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JAVA - TP3");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new BoxLayout(navbarPanel, BoxLayout.X_AXIS));
        mainPanel.add(navbarPanel, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        navbarPanel.add(menuBar);

        JMenu menuMenu = new JMenu("Menu");
        menuBar.add(menuMenu);
        JMenu creditsMenu = new JMenu("Crédits");
        menuBar.add(creditsMenu);

        JMenuItem exercise1Item = new JMenuItem("Exercice 1");
        menuMenu.add(exercise1Item);
        JMenuItem exercise2Item = new JMenuItem("Exercice 2");
        menuMenu.add(exercise2Item);
        JMenuItem leaveItem = new JMenuItem("Quitter");
        menuMenu.add(leaveItem);

        JMenuItem name1Item = new JMenuItem("Olivier MISTRAL");
        creditsMenu.add(name1Item);
        JMenuItem name2Item = new JMenuItem("Antoine PIZZETTA");
        creditsMenu.add(name2Item);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("JAVA - TP3", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.add(titleLabel);
        bodyPanel.add(titlePanel);

        bodyPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        exercise2Item.addActionListener(e -> Exo2.exo2.main(frame));

        frame.setVisible(true);
    }
}
