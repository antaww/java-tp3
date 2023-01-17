package fr.ynov.tp3.PExo1;


import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TP_Classe {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 1 - TP Classe & Étudiant");

        var etudiant1 = new Etudiant("Crews", "Apollo");
        var etudiant2 = new Etudiant("Buzz", "Léclair");

        var maClasse = new Classe("B2 - Linux/Réseaux");
        maClasse.setEtudiant(etudiant1);
        maClasse.setEtudiant(etudiant2);


        var listScrollPane = new JComboBox<String>();
        var studentList = new ArrayList<>(Arrays.asList(maClasse.getEtudiants()));
        studentList.sort(String::compareToIgnoreCase);
        for (var student : studentList) {
            listScrollPane.addItem(student);
        }

        var buttonsPanel = new JPanel();
        var button1 = new JButton(maClasse.nom);
        var labelStudent = new JLabel("Sélectionnez un étudiant pour afficher ses informations");
        var labelClass = new JLabel("Cliquez sur la classe pour afficher ses informations");

        listScrollPane.setPreferredSize(new Dimension(200, 30));
        listScrollPane.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setPreferredSize(new Dimension(200, 50));
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        labelStudent.setFont(new Font("Arial", Font.BOLD, 13));
        labelClass.setFont(new Font("Arial", Font.BOLD, 13));

        bodyPanel.add(buttonsPanel);

        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(labelStudent, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(labelClass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(listScrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(button1, gbc);

        bodyPanel.setLayout(new GridLayout(5, 1, 0, 10));


        Utils.displayFrame(frame);
    }
}
