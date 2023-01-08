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
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3 - Yu-Gi-Oh!");

        var cardPanel = createCardPanel(bodyPanel, "Carte Monstre");
        JButton displayButton = (JButton) cardPanel.get("displayButton");
        JLabel resultLabel = (JLabel) cardPanel.get("resultLabel");
        JPanel resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        JPanel resultPanel = (JPanel) cardPanel.get("resultPanel");

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                MonsterCard monsterCard1 = new MonsterCard();
                JsonElement jsonElement;
                try {
                    jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                    String cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                    if (cardName.equals("Invocateur Dragon Bleu")) {
                        int cardLevel = jsonElement1.getAsJsonObject().get("level").getAsInt();
                        String cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                        String cardRace = jsonElement1.getAsJsonObject().get("race").getAsString();
                        String cardType = jsonElement1.getAsJsonObject().get("type").getAsString();
                        String cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                        int cardAtk = jsonElement1.getAsJsonObject().get("atk").getAsInt();
                        int cardDef = jsonElement1.getAsJsonObject().get("def").getAsInt();
                        String cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                        String cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();

                        System.out.println("Nom : " + cardName);
                        System.out.println("Niveau : " + cardLevel);
                        System.out.println("Attribut : " + cardAttribute);
                        //todo: regrouper race & type puis traduire en map
                        System.out.println("Race : " + cardRace);
                        System.out.println("Type : " + cardType);
                        System.out.println("Référence : " + cardReference);
                        System.out.println("ATK : " + cardAtk);
                        System.out.println("DEF : " + cardDef);
                        System.out.println("Description : " + cardDescription);
                        System.out.println("Image : " + cardImage);
                        setMonsterCard(monsterCard1, cardName, cardLevel, Attribute.valueOf(cardAttribute), PrimaryType.valueOf(cardRace), MonsterType.Effect, cardReference, cardAtk, cardDef, cardDescription);
                        displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, cardImage);
                        translateString("test de l'effect Effect");
                    }
                });

//                setMonsterCard(monsterCard1, "Invocateur Dragon Bleu", 4, Attribute.WIND, PrimaryType.Magicien, MonsterType.Effet, "ys14-fr017", 1500, 600, "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck a votre main.");
                resultLabel.setText(hasUnderscore("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + monsterCard1.getName() +
                        "<br><u>Niveau</u> : " + monsterCard1.getLevel() +
                        "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                        "<br><u>Types</u> : " + monsterCard1.getAllTypes() +
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