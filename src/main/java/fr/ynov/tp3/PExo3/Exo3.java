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
                    if(jsonElement1.getAsJsonObject().get("name").getAsString().equals("Invocateur Dragon Bleu")) {
                        System.out.println(jsonElement1);
                        System.out.println("Nom : " + jsonElement1.getAsJsonObject().get("name").getAsString());
                        System.out.println("Niveau : " + jsonElement1.getAsJsonObject().get("level").getAsString());
                        System.out.println("Attribut : " + jsonElement1.getAsJsonObject().get("attribute").getAsString());
                        //todo: regrouper type & race pour types (supprimer primaryType, secondaryType etc.)
                        System.out.println("Type : " + jsonElement1.getAsJsonObject().get("type").getAsString());
                        System.out.println("Race : " + jsonElement1.getAsJsonObject().get("race").getAsString());
                        System.out.println("Référence : " + jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString());
                        System.out.println("ATK : " + jsonElement1.getAsJsonObject().get("atk").getAsString());
                        System.out.println("DEF : " + jsonElement1.getAsJsonObject().get("def").getAsString());
                        System.out.println("Description : " + jsonElement1.getAsJsonObject().get("desc").getAsString());
                        System.out.println("Image : " + jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString());
//                        setMonsterCard(monsterCard1, "Invocateur Dragon Bleu", 4, Attribute.valueOf(jsonElement1.getAsJsonObject().get("attribute").getAsString()), PrimaryType.Magicien, MonsterType.Effet, "ys14-fr017", 1500, 600, "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck a votre main.");

                    }
                });

                setMonsterCard(monsterCard1, "Invocateur Dragon Bleu", 4, Attribute.WIND, PrimaryType.Magicien, MonsterType.Effet, "ys14-fr017", 1500, 600, "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck a votre main.");
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
                displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, monsterCard1);
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