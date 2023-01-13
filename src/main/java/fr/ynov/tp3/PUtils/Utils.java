package fr.ynov.tp3.PUtils;

import com.google.gson.JsonElement;
import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PExo4.SpecialCards;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static void cleanBodyPanel(JPanel bodyPanel) {
        var bodyPanelComponents = bodyPanel.getComponents();

        if (bodyPanelComponents.length > 1) {
            for (var i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }
    }

    public static String translateString(String stringToTranslate) {
        //todo: fix cards which dont have level like "Arc-En-Ciel, Peintre Météorologique" (check if every card has every attribute)
        Map<String, String> translations = new HashMap<>();
        //PrimaryType
        translations.put("Aqua", "Aqua");
        translations.put("Beast", "Bête /");
        translations.put("Beast-Warrior", "Bête-guerrier /");
        translations.put("Creator-God", "Créateur /");
        translations.put("Cyberse", "Cyberse");
        translations.put("Dinosaur", "Dinosaure /");
        translations.put("Divine-Beast", "Bête-divine /");
        translations.put("Dragon", "Dragon");
        translations.put("Fairy", "Elfe /");
        translations.put("Fiend", "Démon /");
        translations.put("Fish", "Poisson /");
        translations.put("Insect", "Insecte /");
        translations.put("Machine", "Machine");
        translations.put("Plant", "Plante /");
        translations.put("Psychic", "Psychique /");
        translations.put("Pyro", "Pyro");
        translations.put("Reptile", "Reptile");
        translations.put("Rock", "Rocher /");
        translations.put("Sea Serpent", "Serpent De Mer /");
        translations.put("Spellcaster", "Magicien /");
        translations.put("Thunder", "Tonnerre /");
        translations.put("Warrior", "Guerrier /");
        translations.put("Winged Beast", "Bête-ailée /");
        translations.put("Wyrm", "Wyrm");
        translations.put("Zombie", "Zombie");

        //SecondaryType
        translations.put("Fusion", "Fusion");
        translations.put("Link", "Lien /");
        translations.put("Pendulum", "Pendule /");
        translations.put("Ritual", "Rituel /");
        translations.put("XYZ", "Xyz /");

        //TertiaryType
        translations.put("Flip", "Flip");
        translations.put("Gemini", "Gémeau /");
        translations.put("Spirit", "Esprit /");
        translations.put("Synchro", "Synchro");
        translations.put("Toon", "Toon");
        translations.put("Tuner", "Synthoniseur /");
        translations.put("Union", "Union");

        //MonsterType
        translations.put("Effect", "Effet");
        translations.put("Normal", ""); // Do not display "Normal" in the card
        translations.put("Monster", ""); // Do not display "Monster" in the card

        StringBuilder output;
        System.out.println(stringToTranslate);
        if (stringToTranslate.contains(" ")) { //If the string to translate is a combination of words
            for (var entry : translations.entrySet()) {
                if (stringToTranslate.contains(entry.getKey())) {
                    stringToTranslate = stringToTranslate.replace(entry.getKey(), entry.getValue());
                }
            }
        }

        var words = stringToTranslate.split(" ");
        output = new StringBuilder();
        for (var word : words) {
            var translatedWord = translations.get(word);
            if (translatedWord != null) {
                output.append(translatedWord).append(" / ");
            } else {
                output.append(word).append(" ");
            }
        }

        var result = output.toString().trim();
        while (result.endsWith(" /")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public static String hasUnderscore(String str) {
        return (str.contains("_")) ? str.replace("_", " ") : str;
    }

    public static String replaceByUnderscore(String str) {
        return (str.contains("-") || str.contains(" ")) ? str.replace("-", "_").replace(" ", "_") : str;
    }

    public static void createAndDisplaySpecialCard(SpecialCards spellCard1, String specialCardName, JButton displayButton, JLabel resultLabel, JPanel resultImagePanel, JPanel resultPanel, JsonElement jsonElement) {
        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
            if (cardName.equals(specialCardName)) {
                var cardType = replaceByUnderscore(jsonElement1.getAsJsonObject().get("type").getAsString());
                var cardRace = replaceByUnderscore(jsonElement1.getAsJsonObject().get("race").getAsString());
                var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();

                setSpecialCard(spellCard1, cardName, SpecialType.valueOf(cardType), SpecialIcon.valueOf(cardRace), cardReference, cardDescription);
                displayCardImage(resultLabel, resultImagePanel, resultPanel, cardImage);
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
                "</html>")
        );
    }

    public static Map<String, JComponent> createCardPanel(JPanel bodyPanel, String subtitle) {
        var secondPanel = new JPanel();
        var subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPanel.add(subtitleLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        var thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        var thirdPanelBody = new JPanel(new BorderLayout());
        thirdPanel.add(thirdPanelBody);

        var displayButtonPanel = new JPanel();
//        var displayButton = new JButton("Afficher la carte");
//        displayButton.setPreferredSize(new Dimension(200, 30));
        var comboBox = new JComboBox<String>();
        displayButtonPanel.add(comboBox);
        thirdPanelBody.add(displayButtonPanel, BorderLayout.NORTH);

        var resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setText("Cliquer sur le bouton pour afficher la carte...");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        var resultImagePanel = new JPanel();

        var resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultPanel.add(resultImagePanel);
        thirdPanelBody.add(resultPanel, BorderLayout.CENTER);

        bodyPanel.add(thirdPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        return Map.of("comboBox", comboBox, "resultLabel", resultLabel, "resultImagePanel", resultImagePanel, "resultPanel", resultPanel);
    }

    public static void displayCardImage(JLabel resultLabel, JPanel resultImagePanel, JPanel resultPanel, String cardImage) {
        try {
            var url = new URL(cardImage);
            var image = ImageIO.read(url);
            var scaledImage = image.getScaledInstance(280, 435, Image.SCALE_SMOOTH); // Default size : 525 × 768
            var picLabel = new JLabel(new ImageIcon(scaledImage));
            resultImagePanel.add(picLabel);
        } catch (IOException ioException) {
            var errorLabel = new JLabel("Image error 404");
            resultImagePanel.add(errorLabel);
        }
//        displayButton.setText("Masquer la carte");
        resultImagePanel.setVisible(true);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultLabel.setPreferredSize(new Dimension(402, 250));
    }

//    //if card doesn't have secondary type & tertiary type
//    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
//        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, null, monsterType, reference, atk, def, description);
//    }
//
//    //if card doesn't have secondary type
//    //unused for the moment but useful if we want to add a card with tertiary type but without secondary type
//    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
//        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, tertiaryType, monsterType, reference, atk, def, description);
//    }
//
//    //if card doesn't have tertiary type
//    //unused for the moment but useful if we want to add a card with secondary type but without tertiary type
//    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, SecondaryType secondaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
//        setMonsterCard(monsterCard, name, level, attribute, primaryType, secondaryType, null, monsterType, reference, atk, def, description);
//    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, String types, String reference, int atk, int def, String description) {
        monsterCard.setName(name);
        monsterCard.setLevel(level);
        monsterCard.setAttribute(attribute);
        monsterCard.setTypes(types);
        monsterCard.setReference(reference);
        monsterCard.setStats(new String[]{String.valueOf(atk), String.valueOf(def)});
        monsterCard.setDescription(description);
    }

    public static void setSpecialCard(SpecialCards specialCard, String name, SpecialType type, SpecialIcon specialIcon, String reference, String description) {
        specialCard.setName(name);
        specialCard.setType(type);
        specialCard.setSpecialIcon(specialIcon);
        specialCard.setReference(reference);
        specialCard.setDescription(description);
    }
}
