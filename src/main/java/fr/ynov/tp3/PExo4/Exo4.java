package fr.ynov.tp3.PExo4;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Exo4 {
    public static void main(final JFrame frame, final String title) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);

        final var titles = new ArrayList<String>();
        titles.add(title);
        switch (title) {
            case "Carte Magie" -> {
                titleLabel.setText("Exercice 4.1 - Yu-Gi-Oh!");
                titles.add("Spell");
            }
            case "Carte Piège" -> {
                titleLabel.setText("Exercice 4.2 - Yu-Gi-Oh!");
                titles.add("Trap");
            }
        }

        final var cardPanel = Utils.createCardPanel(bodyPanel, titles.get(0));
        final var comboBox = (JComboBox<String>) cardPanel.get("comboBox");
        final var resultLabel = (JLabel) cardPanel.get("resultLabel");
        final var resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        final var resultPanel = (JPanel) cardPanel.get("resultPanel");

        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        final var jsonElement = Utils.getJsonElement(comboBox, titles.get(1));

        comboBox.addActionListener(e -> {
            resultImagePanel.removeAll();
            final var selected = comboBox.getSelectedItem();

            jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                final var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                if (cardName.equals(selected)) {
                    final var cardType = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("type").getAsString());
                    final var cardRace = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("race").getAsString());
                    final var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                    final var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                    final var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                    final var specialCards1 = new SpecialCards(cardName, SpecialType.valueOf(cardType), SpecialIcon.valueOf(cardRace), cardReference, cardDescription);

                    Utils.displayCardImage(resultLabel, resultImagePanel, resultPanel, cardImage);
                    frame.revalidate();
                    resultLabel.setText(Utils.convertUnderscoresToSpaces("<html>" + "<div>" + "<p><u>Nom</u> : " + specialCards1.getName() + "<br><u>Type</u> : " + specialCards1.getType() + "<br><u>Icône</u> : " + specialCards1.getSpecialIcon() + "<br><u>Référence</u> : " + specialCards1.getReference().toUpperCase() + "<br><u>Description</u> : " + specialCards1.getDescription() + "</div>" + "</html>"));
                }
            });
        });
        Utils.displayFrame(frame);
    }
}
