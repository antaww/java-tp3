package fr.ynov.tp3.PExo2;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.tp3.PUtils.Utils.cleanBodyPanel;

public class Exo2_2 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.2 - BoxLayout");

        var secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(2, 1));

        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new GridLayout(2, 1));

        var topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        var bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        secondPanel.add(topPanel, BorderLayout.NORTH);
        secondPanel.add(bottomPanel, BorderLayout.SOUTH);

        var namePanel = new JPanel();
        var passwordPanel = new JPanel();
        var nameLabel = new JLabel("Nom : ");
        var passwordLabel = new JLabel("Password : ");
        var nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(100, 30));
        var passwordInput = new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(100, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameInput);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInput);
        topPanel.add(namePanel);
        topPanel.add(passwordPanel);

        var okButton = new JButton("OK");
        var cancelButton = new JButton("Cancel");

        bottomPanel.add(okButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(cancelButton);

        buttonClicked(frame, nameInput, passwordInput, okButton);
        buttonClicked(frame, nameInput, passwordInput, cancelButton);

        frame.setVisible(true);
    }

    private static void buttonClicked(JFrame frame, JTextField nameInput, JPasswordField passwordInput, JButton button) {
        button.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, nameInput.getText().isEmpty() || passwordInput.getPassword().length == 0
                        ? "Vous devez remplir les champs nom et password"
                        : "Vous avez cliqué sur : " + button.getText() + "\nNom : " + nameInput.getText() + "\nPassword : " + new String(passwordInput.getPassword()))
        );
    }
}