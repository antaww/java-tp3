package fr.ynov.tp3.PExo2;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Exo2_2 : création d'une interface de saisie de nom et mot de passe avec BoxLayout.
 * Cette classe permet de créer une interface graphique pour saisir un nom et un mot de passe.
 * Elle utilise les layout BoxLayout pour organiser les différents éléments de l'interface.
 * Lorsque l'utilisateur clique sur les boutons "OK" ou "Cancel", une boîte de dialogue s'affiche pour indiquer si les champs ont été remplis ou non et les informations saisies.
 */
public class Exo2_2 {
    /**
     * Méthode main : point d'entrée de l'application.
     * Cette méthode permet d'initialiser l'affichage de l'interface de saisie de nom et mot de passe et de gérer les actions des boutons "OK" et "Cancel".
     * Elle prend en paramètre un objet de type JFrame qui représente la fenêtre principale de l'application.
     * Elle utilise également des méthodes de la classe Utils pour nettoyer le contenu de la fenêtre et afficher les éléments de l'interface.
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
        titleLabel.setText("Exercice 2.2 - BoxLayout");

        final var secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(2, 1));

        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new GridLayout(2, 1));

        final var topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        final var bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        secondPanel.add(topPanel, BorderLayout.NORTH);
        secondPanel.add(bottomPanel, BorderLayout.SOUTH);

        final var namePanel = new JPanel();
        final var passwordPanel = new JPanel();
        final var nameLabel = new JLabel("Nom : ");
        final var passwordLabel = new JLabel("Password : ");
        final var nameInput = new JTextField();
        final var passwordInput = new JPasswordField();
        nameInput.setPreferredSize(new Dimension(100, 30));
        passwordInput.setPreferredSize(new Dimension(100, 30));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nameInput.setFont(new Font("Arial", Font.BOLD, 15));
        passwordInput.setFont(new Font("Arial", Font.BOLD, 15));
        namePanel.add(nameLabel);
        namePanel.add(nameInput);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInput);
        topPanel.add(namePanel);
        topPanel.add(passwordPanel);

        final var okButton = new JButton("OK");
        final var cancelButton = new JButton("Cancel");
        okButton.setFont(new Font("Arial", Font.BOLD, 15));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 15));

        bottomPanel.add(okButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(cancelButton);

        buttonClicked(frame, nameInput, passwordInput, okButton);
        buttonClicked(frame, nameInput, passwordInput, cancelButton);

        Utils.displayFrame(frame);
    }

    /**
     * Méthode buttonClicked : gère les actions des boutons "OK" et "Cancel".
     * Cette méthode permet de gérer les actions des boutons "OK" et "Cancel" en affichant une boîte de dialogue avec les informations saisies.
     * Elle prend en paramètre un objet de type JFrame qui représente la fenêtre principale de l'application, un objet de type JTextField qui représente le champ de saisie du nom, un objet de type JPasswordField qui représente le champ de saisie du mot de passe et un objet de type JButton qui représente le bouton cliqué.
     * Elle utilise également des méthodes de la classe JOptionPane pour afficher une boîte de dialogue.
     *
     * @param frame         objet JFrame représentant la fenêtre principale de l'application
     * @param nameInput     objet JTextField représentant le champ de saisie du nom
     * @param passwordInput objet JPasswordField représentant le champ de saisie du mot de passe
     * @param button        objet JButton représentant le bouton cliqué
     * @see fr.ynov.tp3.PUtils.Utils
     * @see javax.swing
     * @see java.awt
     */
    private static void buttonClicked(final JFrame frame, final JTextField nameInput, final JPasswordField passwordInput, final JButton button) {
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, nameInput.getText().isEmpty() || passwordInput.getPassword().length == 0 ? "Vous devez remplir les champs nom et password" : "Vous avez cliqué sur : " + button.getText() + "\nNom : " + nameInput.getText() + "\nPassword : " + new String(passwordInput.getPassword())));
    }
}
