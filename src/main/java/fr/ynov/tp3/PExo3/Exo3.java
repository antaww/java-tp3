package fr.ynov.tp3.PExo3;

import javax.swing.*;
import java.awt.*;

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
                setMonsterCard(monsterCard1, "Invocateur Dragon Bleu", 4, "Vent", PrimaryType.Magicien, MonsterType.Effet, "ys14-fr017", 1500, 600, "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck a votre main.");
                resultLabel.setText("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + monsterCard1.getName() +
                        "<br><u>Niveau</u> : " + monsterCard1.getLevel() +
                        "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                        "<br><u>Types</u> : " + monsterCard1.getAllTypes() +
                        "<br><u>Référence</u> : " + monsterCard1.getReference().toUpperCase() +
                        "<br><u>Statistiques</u> : " + monsterCard1.getStats() +
                        "<br><u>Description</u> : " + monsterCard1.getDescription() +
                        "</div>" +
                        "</html>");
                displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, monsterCard1.getReference(), monsterCard1);
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