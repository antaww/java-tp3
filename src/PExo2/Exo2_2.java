package PExo2;

import javax.swing.*;
import java.awt.*;

import static PUtils.Utils.cleanBodyPanel;

public class Exo2_2 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.2 - BoxLayout");

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(2, 1));

        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        secondPanel.add(topPanel, BorderLayout.NORTH);
        secondPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel namePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JLabel nameLabel = new JLabel("Nom : ");
        JLabel passwordLabel = new JLabel("Password : ");
        JTextField nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(100, 30));
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(100, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameInput);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInput);
        topPanel.add(namePanel);
        topPanel.add(passwordPanel);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

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
