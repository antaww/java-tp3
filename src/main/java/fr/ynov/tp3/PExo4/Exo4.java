package fr.ynov.tp3.PExo4;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static fr.ynov.tp3.PUtils.Utils.*;

public class Exo4 {
    public static void main(JFrame frame, String specialType) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        switch (specialType) {
            case "Carte Magie" -> titleLabel.setText("Exercice 4.1 - Yu-Gi-Oh!");
            case "Carte Piège" -> titleLabel.setText("Exercice 4.2 - Yu-Gi-Oh!");
        }

        var cardPanel = createCardPanel(bodyPanel, specialType);
        var displayButton = (JButton) cardPanel.get("displayButton");
        var resultLabel = (JLabel) cardPanel.get("resultLabel");
        var resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        var resultPanel = (JPanel) cardPanel.get("resultPanel");

        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                JsonElement jsonElement;
                try {
                    jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                switch (specialType) {
                    case "Carte Magie" -> {
                        var spellCard1 = new SpecialCards();
                        createAndDisplaySpecialCard(spellCard1, "Typhon d'Espace Mystique", displayButton, resultLabel, resultImagePanel, resultPanel, jsonElement);
                    }
                    case "Carte Piège" -> {
                        var trapCard1 = new SpecialCards();
                        createAndDisplaySpecialCard(trapCard1, "Sortilège de l'ombre", displayButton, resultLabel, resultImagePanel, resultPanel, jsonElement);
                    }
                }
            } else {
                isDisplayButtonClicked[0] = false;
                displayButton.setText("Afficher la carte");
                resultLabel.setText("Cliquer sur le bouton pour afficher la carte...");
                resultPanel.setLayout(new FlowLayout());
                resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
                resultImagePanel.removeAll();
                resultImagePanel.setVisible(false);
            }
        });
        frame.setVisible(true);
    }
}
