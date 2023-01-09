package fr.ynov.tp3.PExo3;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static fr.ynov.tp3.PUtils.Utils.*;

public class Exo3 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3 - Yu-Gi-Oh!");

        var cardPanel = createCardPanel(bodyPanel, "Carte Monstre");
        var displayButton = (JButton) cardPanel.get("displayButton");
        var resultLabel = (JLabel) cardPanel.get("resultLabel");
        var resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        var resultPanel = (JPanel) cardPanel.get("resultPanel");

        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                var monsterCard1 = new MonsterCard();
                JsonElement jsonElement;
                try {
                    jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                    var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                    if (cardName.equals("Invocateur Dragon Bleu")) {
                        var cardLevel = jsonElement1.getAsJsonObject().get("level").getAsInt();
                        var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                        var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                        var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                        var cardAtk = jsonElement1.getAsJsonObject().get("atk").getAsInt();
                        var cardDef = jsonElement1.getAsJsonObject().get("def").getAsInt();
                        var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                        var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();

                        setMonsterCard(monsterCard1, cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);
                        displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, cardImage);
                    }
                });

                resultLabel.setText(hasUnderscore("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + monsterCard1.getName() +
                        "<br><u>Niveau</u> : " + monsterCard1.getLevel() +
                        "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                        "<br><u>Types</u> : " + "[" + translateString(monsterCard1.getTypes()) + "]" +
                        "<br><u>Référence</u> : " + monsterCard1.getReference().toUpperCase() +
                        "<br><u>Statistiques</u> : " + monsterCard1.getStats() +
                        "<br><u>Description</u> : " + monsterCard1.getDescription() +
                        "</div>" +
                        "</html>"));
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