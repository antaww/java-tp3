package fr.ynov.tp3.PUtils;

import fr.ynov.tp3.PExo1.TP_Classe;
import fr.ynov.tp3.PExo2.Exo2_1;
import fr.ynov.tp3.PExo2.Exo2_2;
import fr.ynov.tp3.PExo2.Exo2_3;
import fr.ynov.tp3.PExo3.Exo3;
import fr.ynov.tp3.PExo4.Exo4;
import fr.ynov.tp3.PExo5.Exo5;
import fr.ynov.tp3.PExo6.Exo6;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Menu {
    public static void main(String[] args) {
        var frame = new JFrame("JAVA - TP3");
        frame.setSize(800, 800);
        UIManager.put("PopupMenu.border", new LineBorder(new Color(33, 33, 33)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        var mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        var navbarPanel = new JPanel();
        navbarPanel.setLayout(new BoxLayout(navbarPanel, BoxLayout.X_AXIS));
        mainPanel.add(navbarPanel, BorderLayout.NORTH);

        var menuBar = new JMenuBar();
        navbarPanel.add(menuBar);

        var menuMenu = new JMenu("Menu");
        menuBar.add(menuMenu);
        var creditsMenu = new JMenu("Crédits");
        menuBar.add(creditsMenu);

        var exercise1Item = new JMenuItem("Exercice 1 - TP Classe & Étudiant");
        menuMenu.add(exercise1Item);
        var exercise2Item1 = new JMenuItem("Exercice 2.1 - JButton");
        menuMenu.add(exercise2Item1);
        var exercise2Item2 = new JMenuItem("Exercice 2.2 - BoxLayout");
        menuMenu.add(exercise2Item2);
        var exercise2Item3 = new JMenuItem("Exercice 2.3 - Afficher Cartes");
        menuMenu.add(exercise2Item3);
        var exercise3Item = new JMenuItem("Exercice 3 - Yu-Gi-Oh! Carte Monstre");
        menuMenu.add(exercise3Item);
        var exercise4Item1 = new JMenuItem("Exercice 4.1 - Yu-Gi-Oh! Carte Magie");
        menuMenu.add(exercise4Item1);
        var exercise4Item2 = new JMenuItem("Exercice 4.2 - Yu-Gi-Oh! Carte Piège");
        menuMenu.add(exercise4Item2);
        var exercise5Item = new JMenuItem("Exercice 5 - Yu-Gi-Oh! Héritage");
        menuMenu.add(exercise5Item);
        var exercise6Item = new JMenuItem("Exercice 6 - DU-DU-DU-DUEL !");
        menuMenu.add(exercise6Item);
        var leaveItem = new JMenuItem("Quitter");
        menuMenu.add(leaveItem);

        var name1Item = new JMenuItem("Olivier MISTRAL");
        creditsMenu.add(name1Item);
        var name2Item = new JMenuItem("Antoine PIZZETTA");
        creditsMenu.add(name2Item);

        var bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        var titleLabel = new JLabel("JAVA - TP3", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 33));

        var titlePanel = new JPanel();
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

        leaveItem.addActionListener(e -> System.exit(0));

        Utils.setPanelsBackgroundColor(frame);
        Utils.setFontColor(frame);
        Utils.setBackgroundColorForMenuBar(menuBar, new Color(33, 33, 33));
        Utils.removeMenuBarBorders(menuBar);

        //todo: listen to menuMenu items hover and set the background color to 28, 28, 28
        //todo: remove blue background on click (buttons & menu items)
        //todo: cursor pointer on clickable elements

        menuMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(33, 33, 33)));
        creditsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(33, 33, 33)));

        frame.setVisible(true);
    }
}

