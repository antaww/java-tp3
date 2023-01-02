package fr.ynov.tp3.PUtils;


import fr.ynov.tp3.PExo2.Exo2_1;
import fr.ynov.tp3.PExo2.Exo2_2;
import fr.ynov.tp3.PExo2.Exo2_3;
import fr.ynov.tp3.PExo3.Exo3;
import fr.ynov.tp3.PExo4.Exo4_1;

import javax.swing.*;
import java.awt.*;

public class Menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JAVA - TP3");
        frame.setSize(500, 500);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
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
        JMenuItem exercise2Item1 = new JMenuItem("Exercice 2.1 - JButton");
        menuMenu.add(exercise2Item1);
        JMenuItem exercise2Item2 = new JMenuItem("Exercice 2.2 - BoxLayout");
        menuMenu.add(exercise2Item2);
        JMenuItem exercise2Item3 = new JMenuItem("Exercice 2.3 - Afficher Cartes");
        menuMenu.add(exercise2Item3);
        JMenuItem exercise3Item = new JMenuItem("Exercice 3 - Yu-Gi-Oh! Carte Monstre");
        menuMenu.add(exercise3Item);
        JMenuItem exercise4Item1 = new JMenuItem("Exercice 4.1 - Yu-Gi-Oh! Carte Magie");
        menuMenu.add(exercise4Item1);
        JMenuItem exercise4Item2 = new JMenuItem("Exercice 4.2 - Yu-Gi-Oh! Carte Piège");
        menuMenu.add(exercise4Item2);
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
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        bodyPanel.add(titlePanel);

        bodyPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        exercise2Item1.addActionListener(e -> Exo2_1.main(frame));
        exercise2Item2.addActionListener(e -> Exo2_2.main(frame));
        exercise2Item3.addActionListener(e -> Exo2_3.main(frame));
        exercise3Item.addActionListener(e -> Exo3.main(frame));
        exercise4Item1.addActionListener(e -> Exo4_1.main(frame));
        leaveItem.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

}
