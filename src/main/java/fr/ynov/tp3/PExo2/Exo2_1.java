package fr.ynov.tp3.PExo2;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Exo2_1 : création de boutons simples et fantaisies.
 * Cette classe permet de créer deux types de boutons : un bouton simple avec un texte et un bouton fantaisie avec un texte et une image.
 * Lorsque l'utilisateur clique sur chacun des boutons, une boîte de dialogue s'affiche pour indiquer le bouton sur lequel il a cliqué.
 * Pour le bouton fantaisie, l'image changera également lorsque l'utilisateur clique dessus.
 */
public class Exo2_1 {
    /**
     * Méthode main : point d'entrée de l'application.
     * Cette méthode permet d'initialiser l'affichage de l'application et de créer les boutons simples et fantaisies.
     * Elle prend en paramètre un objet de type JFrame qui représente la fenêtre principale de l'application.
     * Elle utilise également des méthodes de la classe Utils pour nettoyer le contenu de la fenêtre et afficher les boutons créés.
     *
     * @param frame objet JFrame représentant la fenêtre principale de l'application
     * @see fr.ynov.tp3.PUtils.Utils
     * @see javax.swing
     * @see java.awt
     */
    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.1 - JButton");

        final var buttonsPanel = new JPanel();
        final var button1 = new JButton("Bouton simple");
        final var button2 = new JButton("Bouton fantaisie");
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        button2.setFont(new Font("Arial", Font.BOLD, 15));
        button2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/canard.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

        bodyPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonsPanel.setLayout(new GridLayout(2, 1, 0, 5));
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        bodyPanel.add(buttonsPanel);

        button1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button1.getText()));
        button2.addActionListener(e -> {
            button2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/canard2.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
            JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button2.getText());
            button2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/canard.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        });

        Utils.displayFrame(frame);
    }
}
