package fr.ynov.tp3.PUtils;

import fr.ynov.tp3.PExo3.*;
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

    public static void translateString(String stringToTranslate){
        Map<String, String> translations = new HashMap<>();
        translations.put("Effect", "Effet");
        translations.put("world", "monde");

        var words = stringToTranslate.split(" ");
        var output = new StringBuilder();
        for (var word : words) {
            var translatedWord = translations.get(word);
            if (translatedWord != null) {
                output.append(translatedWord).append(" ");
            } else {
                output.append(word).append(" ");
            }
        }

        System.out.println(output.toString().trim());
    }

    public static String hasUnderscore(String str) {
        return (str.contains("_")) ? str.replace("_", " ") : str;
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
        var displayButton = new JButton("Afficher la carte");
        displayButton.setPreferredSize(new Dimension(200, 30));
        displayButtonPanel.add(displayButton);
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
        return Map.of("displayButton", displayButton, "resultLabel", resultLabel, "resultImagePanel", resultImagePanel, "resultPanel", resultPanel);
    }

    public static void displayCardImage(JButton displayButton, JLabel resultLabel, JPanel resultImagePanel, JPanel resultPanel, String cardImage) {
        try {
            var url = new URL(cardImage);
            var image = ImageIO.read(url);
            var scaledImage = image.getScaledInstance(image.getWidth() / 3, image.getHeight() / 3, Image.SCALE_SMOOTH);
            var picLabel = new JLabel(new ImageIcon(scaledImage));
            resultImagePanel.add(picLabel);
        } catch (IOException ioException) {
            var errorLabel = new JLabel("Image error 404");
            resultImagePanel.add(errorLabel);
        }
        displayButton.setText("Masquer la carte");
        resultImagePanel.setVisible(true);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultLabel.setPreferredSize(new Dimension(402, 250));
    }

    //if card doesn't have secondary type & tertiary type
    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, null, monsterType, reference, atk, def, description);
    }

    //if card doesn't have secondary type
    //unused for the moment but useful if we want to add a card with tertiary type but without secondary type
    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, tertiaryType, monsterType, reference, atk, def, description);
    }

    //if card doesn't have tertiary type
    //unused for the moment but useful if we want to add a card with secondary type but without tertiary type
    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, SecondaryType secondaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, secondaryType, null, monsterType, reference, atk, def, description);
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, SecondaryType secondaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        monsterCard.setName(name);
        monsterCard.setLevel(level);
        monsterCard.setAttribute(attribute);
        monsterCard.setPrimaryType(primaryType);
        monsterCard.setSecondaryType(secondaryType);
        monsterCard.setTertiaryType(tertiaryType);
        monsterCard.setMonsterType(monsterType);
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
