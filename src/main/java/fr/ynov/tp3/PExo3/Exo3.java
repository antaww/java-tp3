package fr.ynov.tp3.PExo3;

import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Classe Exo3 : création d'une interface pour afficher des informations sur des cartes Monstres de Yu-Gi-Oh!
 * Cette classe permet de créer une interface graphique pour sélectionner une carte Monstre dans une liste déroulante,
 * puis d'afficher des informations sur la carte sélectionnée telles que le nom, le niveau, l'attribut, le type, la référence, les points d'attaque et de défense, et la description.
 * Elle utilise les layout GridLayout pour organiser les différents éléments de l'interface.
 * Elle utilise également des méthodes de la classe Utils pour nettoyer le contenu de la fenêtre, afficher les informations de la carte et afficher l'image de la carte.
 * Elle utilise également l'enum Attribute pour stocker les différents attributs des cartes.
 */
public class Exo3 {
    /**
     * Méthode main : point d'entrée de l'application.
     * Cette méthode permet d'initialiser l'affichage de l'application et de créer l'interface pour la sélection et l'affichage des cartes Yu-Gi-Oh!
     * Elle prend en paramètre un objet de type JFrame qui représente la fenêtre principale de l'application.
     * Elle utilise également des méthodes de la classe Utils pour nettoyer le contenu de la fenêtre, créer l'interface pour la sélection et l'affichage des cartes et récupérer les informations des cartes depuis un fichier json.
     *
     * @param frame objet JFrame représentant la fenêtre principale de l'application
     * @see fr.ynov.tp3.PUtils.Utils
     * @see javax.swing
     * @see java.awt
     * @see fr.ynov.tp3.PExo3.Attribute
     */
    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3 - Yu-Gi-Oh!");

        final var cardPanel = Utils.createCardPanel(bodyPanel, "Carte Monstre");
        final var comboBox = (JComboBox<String>) cardPanel.get("comboBox");
        final var resultLabel = (JLabel) cardPanel.get("resultLabel");
        final var resultImagePanel = (JPanel) cardPanel.get("resultImagePanel");
        final var resultPanel = (JPanel) cardPanel.get("resultPanel");

        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        final var jsonElement = Utils.getJsonElement(comboBox, "Monster");

        comboBox.addActionListener(e -> {
            resultImagePanel.removeAll();
            final var selected = comboBox.getSelectedItem();

            jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
                final var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                if (cardName.equals(selected)) {
                    final var cardLevel = jsonElement1.getAsJsonObject().has("level") ? jsonElement1.getAsJsonObject().get("level").getAsInt() : -1;
                    final var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                    final var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                    final var cardReference = jsonElement1.getAsJsonObject().has("card_sets") && jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().size() > 0
                            ? jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString()
                            : "N/A";
                    final var cardAtk = jsonElement1.getAsJsonObject().has("atk") ? jsonElement1.getAsJsonObject().get("atk").getAsInt() : -1;
                    final var cardDef = jsonElement1.getAsJsonObject().has("def") ? jsonElement1.getAsJsonObject().get("def").getAsInt() : -1;
                    final var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                    final var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                    final var monsterCard1 = new MonsterCard(cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);

                    Utils.displayCardImage(resultLabel, resultImagePanel, resultPanel, cardImage);
                    frame.revalidate();
                    resultLabel.setText(Utils.convertUnderscoresToSpaces("<html>" +
                            "<div>" +
                            "<p><u>Nom</u> : " + monsterCard1.getName() +
                            "<br><u>Niveau</u> : " + (monsterCard1.getLevel() != -1 ? monsterCard1.getLevel() : "Aucun niveau disponible pour cette carte.") +
                            "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                            "<br><u>Types</u> : " + "[" + Utils.translateString(monsterCard1.getTypes()) + "]" +
                            "<br><u>Référence</u> : " + (!Objects.equals(monsterCard1.getReference(), "N/A") ? monsterCard1.getReference().toUpperCase() : "Aucune référence disponible pour cette carte.") +
                            "<br><u>Statistiques</u> : " + monsterCard1.getStats() +
                            "<br><u>Description</u> : " + monsterCard1.getDescription() +
                            "</div>" +
                            "</html>")
                    );
                }
            });
        });
        Utils.displayFrame(frame);
    }
}