package fr.ynov.tp3.PExo2;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.tp3.PUtils.Utils.cleanBodyPanel;

public class Exo2_1 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.1 - JButton");

        var buttonsPanel = new JPanel();
        var button1 = new JButton("Bouton simple");
        var button2 = new JButton("Bouton fantaisie");
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

        frame.setVisible(true);
    }
}
