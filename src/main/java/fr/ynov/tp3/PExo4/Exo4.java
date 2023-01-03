package fr.ynov.tp3.PExo4;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.tp3.PUtils.Utils.*;

public class Exo4 {
    public static void main(JFrame frame, String specialType) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        switch (specialType) {
            case "Carte Magie" -> titleLabel.setText("Exercice 4.1 - Yu-Gi-Oh!");
            case "Carte Piège" -> titleLabel.setText("Exercice 4.2 - Yu-Gi-Oh!");
        }

        var cardPanel = createCardPanel(bodyPanel, specialType);
        JButton displayButton = (JButton) cardPanel.get("displayButton");
        JLabel resultLabel = (JLabel) cardPanel.get("resultLabel");
        JPanel resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        JPanel resultPanel = (JPanel) cardPanel.get("resultPanel");

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                switch (specialType) {
                    case "Carte Magie" -> {
                        SpecialCards spellCard1 = new SpecialCards();
                        setSpecialCard(spellCard1, "Typhon d'Espace Mysthique", SpecialType.Magie, SpecialIcon.Jeu_Rapide, "ys14-fr024", "Ciblez 1 Carte Magie/Piège sur le Terrain ; détruisez la cible.");
                        displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, spellCard1);
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
                        SpecialCards trapCard1 = new SpecialCards();
                        setSpecialCard(trapCard1, "Sortilège De l'Ombre", SpecialType.Piege, SpecialIcon.Continu, "ys14-fr034", "Activez cette carte en ciblant 1 monstre face recto contrôlé par votre adversaire ; il perd 700 ATK, et aussi, il ne peut ni attaquer ni changer sa position de combat. Lorsqu’il quitte le Terrain, détruisez cette carte.");
                        displayCardImage(displayButton, resultLabel, resultImagePanel, resultPanel, trapCard1);
                        resultLabel.setText(hasUnderscore("<html>" +
                                "<div>" +
                                "<p><u>Nom</u> : " + trapCard1.getName() +
                                "<br><u>Type</u> : " + trapCard1.getType() +
                                "<br><u>Icône</u> : " + trapCard1.getSpecialIcon() +
                                "<br><u>Référence</u> : " + trapCard1.getReference().toUpperCase() +
                                "<br><u>Description</u> : " + trapCard1.getDescription() +
                                "</div>" +
                                "</html>"));
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
