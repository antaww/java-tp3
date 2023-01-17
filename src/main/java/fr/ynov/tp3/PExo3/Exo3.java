package fr.ynov.tp3.PExo3;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Exo3 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3 - Yu-Gi-Oh!");

        var cardPanel = Utils.createCardPanel(bodyPanel, "Carte Monstre");
        var comboBox = (JComboBox<String>) cardPanel.get("comboBox");
        var resultLabel = (JLabel) cardPanel.get("resultLabel");
        var resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        var resultPanel = (JPanel) cardPanel.get("resultPanel");

        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        var jsonElement = Utils.getJsonElement(comboBox, "Monster");

        comboBox.addActionListener(e -> {
            resultImagePanel.removeAll();
            var selected = comboBox.getSelectedItem();

            jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                if (cardName.equals(selected)) {
                    var cardLevel = jsonElement1.getAsJsonObject().has("level") ? jsonElement1.getAsJsonObject().get("level").getAsInt() : -1;
                    var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                    var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                    var cardReference = jsonElement1.getAsJsonObject().has("card_sets") && jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().size() > 0
                            ? jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString()
                            : "N/A";
                    var cardAtk = jsonElement1.getAsJsonObject().has("atk") ? jsonElement1.getAsJsonObject().get("atk").getAsInt() : -1;
                    var cardDef = jsonElement1.getAsJsonObject().has("def") ? jsonElement1.getAsJsonObject().get("def").getAsInt() : -1;
                    var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                    var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                    var monsterCard1 = new MonsterCard(cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);

                    Utils.displayCardImage(resultLabel, resultImagePanel, resultPanel, cardImage);
                    resultLabel.setText(Utils.convertUnderscoresToSpaces("<html>" +
                            "<div>" +
                            "<p><u>Nom</u> : " + monsterCard1.getName() +
                            "<br><u>Niveau</u> : " + (monsterCard1.getLevel() != -1 ? monsterCard1.getLevel() : "Aucun niveau disponible pour cette carte.") +
                            "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                            "<br><u>Types</u> : " + "[" + Utils.translateString(monsterCard1.getTypes()) + "]" +
                            "<br><u>Référence</u> : " + (!Objects.equals(monsterCard1.getReference(), "N/A") ? monsterCard1.getReference().toUpperCase() : "Aucune référence disponible pour cette carte.") +
                            "<br><u>Statistiques</u> : " + monsterCard1.getStats() +
                            "<br><u>Description</u> : " + monsterCard1.getDescription() +
                            "</div>" +
                            "</html>")
                    );
                }
            });
        });
        Utils.displayFrame(frame);
    }
}