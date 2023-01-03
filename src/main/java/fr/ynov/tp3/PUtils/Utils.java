package fr.ynov.tp3.PUtils;

import fr.ynov.tp3.Card;
import fr.ynov.tp3.PExo3.*;
import fr.ynov.tp3.PExo4.SpecialCards;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Utils {
    public static void cleanBodyPanel(JPanel bodyPanel) {
        Component[] bodyPanelComponents = bodyPanel.getComponents();

        if (bodyPanelComponents.length > 1) {
            for (int i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }
    }

    public static String hasUnderscore(String str) {
        return (str.contains("_")) ? str.replace("_", " ") : str;
    }

    public static Map<String, JComponent> createCardPanel(JPanel bodyPanel, String subtitle) {
        JPanel secondPanel = new JPanel();
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPanel.add(subtitleLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        JPanel thirdPanelBody = new JPanel(new BorderLayout());
        thirdPanel.add(thirdPanelBody);

        JPanel displayButtonPanel = new JPanel();
        JButton displayButton = new JButton("Afficher la carte");
        displayButton.setPreferredSize(new Dimension(200, 30));
        displayButtonPanel.add(displayButton);
        thirdPanelBody.add(displayButtonPanel, BorderLayout.NORTH);

        JLabel resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setText("Cliquer sur le bouton pour afficher la carte...");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        JPanel resultImagePanel = new JPanel();

        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultPanel.add(resultImagePanel);
        thirdPanelBody.add(resultPanel, BorderLayout.CENTER);

        bodyPanel.add(thirdPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        return Map.of("displayButton", displayButton, "resultLabel", resultLabel, "resultImagePanel", resultImagePanel, "resultPanel", resultPanel);
    }

    public static void displayCardImage(JButton displayButton, JLabel resultLabel, JPanel resultImagePanel, JPanel resultPanel, Card card) {
        try {
            String reference = card.getReference();
            String firstPart = reference.substring(0, reference.indexOf("-"));
            URL url = new URL("https://www.ultrajeux.com/images/yugioh/scan/normal/fr/" + firstPart + "/" + reference + ".jpg");
            BufferedImage image = ImageIO.read(url);
            Image scaledImage = image.getScaledInstance(image.getWidth() / 2, image.getHeight() / 2, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
            resultImagePanel.add(picLabel);
        } catch (IOException ioException) {
            JLabel errorLabel = new JLabel("Image error 404");
            resultImagePanel.add(errorLabel);
        }
        displayButton.setText("Masquer la carte");
        resultImagePanel.setVisible(true);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultLabel.setPreferredSize(new Dimension(402, 250));
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, null, monsterType, reference, atk, def, description);
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, Attribute attribute, PrimaryType primaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, tertiaryType, monsterType, reference, atk, def, description);
    }

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
