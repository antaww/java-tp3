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
                        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                            var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                            if (cardName.equals("Typhon d'Espace Mystique")) {
                                var cardType = replaceByUnderscore(jsonElement1.getAsJsonObject().get("type").getAsString());
                                var cardRace = replaceByUnderscore(jsonElement1.getAsJsonObject().get("race").getAsString());
                                var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                                var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                                var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();

                                setSpecialCard(spellCard1, cardName, SpecialType.valueOf(cardType), SpecialIcon.valueOf(cardRace), cardReference, cardDescription);
                                displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, cardImage);
                            }
                        });
                        resultLabel.setText(hasUnderscore("<html>" +
                                "<div>" +
                                "<p><u>Nom</u> : " + spellCard1.getName() +
                                "<br><u>Type</u> : " + spellCard1.getType() +
                                "<br><u>Icône</u> : " + spellCard1.getSpecialIcon() +
                                "<br><u>Référence</u> : " + spellCard1.getReference().toUpperCase() +
                                "<br><u>Description</u> : " + spellCard1.getDescription() +
                                "</div>" +
                                "</html>"));
                    }
                    case "Carte Piège" -> {
//                        var trapCard1 = new SpecialCards();
//                        setSpecialCard(trapCard1, "Sortilège De l'Ombre", SpecialType.Piege, SpecialIcon.Continu, "ys14-fr034", "Activez cette carte en ciblant 1 monstre face recto contrôlé par votre adversaire ; il perd 700 ATK, et aussi, il ne peut ni attaquer ni changer sa position de combat. Lorsqu’il quitte le Terrain, détruisez cette carte.");
////                        displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, trapCard1);
//                        resultLabel.setText(hasUnderscore("<html>" +
//                                "<div>" +
//                                "<p><u>Nom</u> : " + trapCard1.getName() +
//                                "<br><u>Type</u> : " + trapCard1.getType() +
//                                "<br><u>Icône</u> : " + trapCard1.getSpecialIcon() +
//                                "<br><u>Référence</u> : " + trapCard1.getReference().toUpperCase() +
//                                "<br><u>Description</u> : " + trapCard1.getDescription() +
//                                "</div>" +
//                                "</html>"));
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
