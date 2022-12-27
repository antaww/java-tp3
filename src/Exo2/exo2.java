package Exo2;

import javax.swing.*;
import java.awt.*;

public class exo2 {
    public static void main(JFrame frame) {
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2");

        if (((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponentCount() > 1) {
            ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).remove(1);
        }
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        JButton button1 = new JButton("Bouton simple");
        buttonsPanel.add(button1);
        JButton button2 = new JButton("Bouton fantaisie");
        button2.setIcon(new ImageIcon(new ImageIcon("src/Exo2/canard.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

        buttonsPanel.add(button2);

        ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).add(buttonsPanel);
        ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).setLayout(new GridLayout(3, 1));
        buttonsPanel.setLayout(new GridLayout(2, 1));

        button1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button1.getText()));
        button2.addActionListener(e -> {
            button2.setIcon(new ImageIcon(new ImageIcon("src/Exo2/canard2.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
            JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur : " + button2.getText());
            button2.setIcon(new ImageIcon(new ImageIcon("src/Exo2/canard.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        });

        frame.setVisible(true);
    }
}
