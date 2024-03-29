package fr.ynov.tp3.PUtils;

import fr.ynov.tp3.PExo1.TP_Classe;
import fr.ynov.tp3.PExo2.Exo2_1;
import fr.ynov.tp3.PExo2.Exo2_2;
import fr.ynov.tp3.PExo2.Exo2_3;
import fr.ynov.tp3.PExo3.Exo3;
import fr.ynov.tp3.PExo4.Exo4;
import fr.ynov.tp3.PExo5.Exo5;
import fr.ynov.tp3.PExo6.Exo6;
import fr.ynov.tp3.PExo7.Exo7;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Classe Menu : création d'une classe pour définir le menu de l'application.
 * Cette classe permet de lancer les différents exercices.
 */
public class Menu {
    /**
     * Méthode main : méthode pour lancer l'application.
     *
     * @param args arguments de la méthode main
     */
    public static void main(final String[] args) {
        final var frame = new JFrame("JAVA - TP3");
        frame.setSize(800, 800);
        UIManager.put("PopupMenu.border", new LineBorder(new Color(33, 33, 33)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        final var mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        final var navbarPanel = new JPanel();
        navbarPanel.setLayout(new BoxLayout(navbarPanel, BoxLayout.X_AXIS));
        mainPanel.add(navbarPanel, BorderLayout.NORTH);

        final var menuBar = new JMenuBar();
        navbarPanel.add(menuBar);

        final var menuMenu = new JMenu("Menu");
        menuBar.add(menuMenu);
        final var creditsMenu = new JMenu("Crédits");
        menuBar.add(creditsMenu);

        final var exercise1Item = new JMenuItem("Exercice 1 - TP Classe & Étudiant");
        menuMenu.add(exercise1Item);
        final var exercise2Item1 = new JMenuItem("Exercice 2.1 - JButton");
        menuMenu.add(exercise2Item1);
        final var exercise2Item2 = new JMenuItem("Exercice 2.2 - BoxLayout");
        menuMenu.add(exercise2Item2);
        final var exercise2Item3 = new JMenuItem("Exercice 2.3 - Afficher Cartes");
        menuMenu.add(exercise2Item3);
        final var exercise3Item = new JMenuItem("Exercice 3 - Yu-Gi-Oh! Carte Monstre");
        menuMenu.add(exercise3Item);
        final var exercise4Item1 = new JMenuItem("Exercice 4.1 - Yu-Gi-Oh! Carte Magie");
        menuMenu.add(exercise4Item1);
        final var exercise4Item2 = new JMenuItem("Exercice 4.2 - Yu-Gi-Oh! Carte Piège");
        menuMenu.add(exercise4Item2);
        final var exercise5Item = new JMenuItem("Exercice 5 - Yu-Gi-Oh! Héritage");
        menuMenu.add(exercise5Item);
        final var exercise6Item = new JMenuItem("Exercice 6 - DU-DU-DU-DUEL !");
        menuMenu.add(exercise6Item);
        final var exercise7Item = new JMenuItem("Exercice 7 - Deck");
        menuMenu.add(exercise7Item);
        final var leaveItem = new JMenuItem("Quitter");
        menuMenu.add(leaveItem);

        final var name1Item = new JMenuItem("Olivier MISTRAL");
        creditsMenu.add(name1Item);
        final var name2Item = new JMenuItem("Antoine PIZZETTA");
        creditsMenu.add(name2Item);

        final var bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        final var titleLabel = new JLabel("JAVA - TP3", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 33));

        final var titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        bodyPanel.add(titlePanel);

        bodyPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        exercise1Item.addActionListener(e -> TP_Classe.main(frame));
        exercise2Item1.addActionListener(e -> Exo2_1.main(frame));
        exercise2Item2.addActionListener(e -> Exo2_2.main(frame));
        exercise2Item3.addActionListener(e -> Exo2_3.main(frame));
        exercise3Item.addActionListener(e -> Exo3.main(frame));
        exercise4Item1.addActionListener(e -> Exo4.main(frame, "Carte Magie"));
        exercise4Item2.addActionListener(e -> Exo4.main(frame, "Carte Piège"));
        exercise5Item.addActionListener(e -> Exo5.main(frame));
        exercise6Item.addActionListener(e -> Exo6.main(frame));
        exercise7Item.addActionListener(e -> Exo7.main(frame));

        leaveItem.addActionListener(e -> System.exit(0));

        Utils.setPanelsBackgroundColor(frame);
        Utils.setFontColor(frame);
        Utils.setBackgroundColorForMenuBar(menuBar, new Color(33, 33, 33));
        Utils.removeMenuBarBorders(menuBar);

        menuMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(33, 33, 33)));
        creditsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(33, 33, 33)));

        frame.setVisible(true);
    }
}

