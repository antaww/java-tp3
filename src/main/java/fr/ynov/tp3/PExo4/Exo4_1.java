package fr.ynov.tp3.PExo4;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.tp3.PUtils.Utils.*;

public class Exo4_1 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 4.1 - Yu-Gi-Oh!");

        var cardPanel = createCardPanel(bodyPanel, "Carte Magie");
        JButton displayButton = (JButton) cardPanel.get("displayButton");
        JLabel resultLabel = (JLabel) cardPanel.get("resultLabel");
        JPanel resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        JPanel resultPanel = (JPanel) cardPanel.get("resultPanel");

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                SpecialCards spellCard1 = new SpecialCards();
                setSpecialCard(spellCard1, "Typhon d'Espace Mysthique", "Magie", SpecialIcon.Jeu_Rapide,"ys14-fr024", "Ciblez 1 Carte Magie/Piège sur le Terrain ; détruisez la cible.");
                resultLabel.setText("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + spellCard1.getName() +
                        "<br><u>Type</u> : " + spellCard1.getType() +
                        //todo: enlever l'underscore de l'icone
                        "<br><u>Icône</u> : " + spellCard1.getSpecialIcon() +
                        "<br><u>Référence</u> : " + spellCard1.getReference().toUpperCase() +
                        "<br><u>Description</u> : " + spellCard1.getDescription() +
                        "</div>" +
                        "</html>");
                displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, spellCard1.getReference(), spellCard1);
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
