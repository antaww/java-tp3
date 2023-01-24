package fr.ynov.tp3.PExo2;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;

public class Exo2_1 {
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

        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        bodyPanel.add(buttonsPanel);

        bodyPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonsPanel.setLayout(new GridLayout(2, 1, 0, 5));

        button1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button1.getText()));
        button2.addActionListener(e -> {
            button2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/canard2.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
            JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button2.getText());
            button2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/canard.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        });
        Utils.displayFrame(frame);
    }
}
