package fr.ynov.tp3.PExo7;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PExo4.SpecialCards;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;
import fr.ynov.tp3.PUtils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Exo7 {
    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 7 - Deck");

        final var deckPanel = new JPanel();
        deckPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        deckPanel.setPreferredSize(new Dimension(900, 550));
        bodyPanel.add(deckPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        final JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
        } catch (final FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        final var monstersList = new ArrayList<MonsterCard>();
        final var monstersImages = new ArrayList<String>();
        final var spellsList = new ArrayList<SpecialCards>();
        final var spellsImages = new ArrayList<String>();
        final var trapsList = new ArrayList<SpecialCards>();
        final var trapsImages = new ArrayList<String>();

        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            final var type = jsonElement1.getAsJsonObject().get("type").getAsString();
            final var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
            if (type.contains("Monster")) {
                final var cardLevel = jsonElement1.getAsJsonObject().has("level") ? jsonElement1.getAsJsonObject().get("level").getAsInt() : -1;
                final var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                final var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                final var cardReference = jsonElement1.getAsJsonObject().has("card_sets") && jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().size() > 0 ? jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString() : "N/A";
                final var cardAtk = jsonElement1.getAsJsonObject().has("atk") ? jsonElement1.getAsJsonObject().get("atk").getAsInt() : -1;
                final var cardDef = jsonElement1.getAsJsonObject().has("def") ? jsonElement1.getAsJsonObject().get("def").getAsInt() : -1;
                final var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                final var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                final var monster = new MonsterCard(cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);

                monstersList.add(monster);
                monstersImages.add(cardImage);
            } else if (type.contains("Spell")) {
                final var cardType = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("type").getAsString());
                final var cardRace = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("race").getAsString());
                final var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                final var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                final var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                final var spell = new SpecialCards(cardName, SpecialType.valueOf(cardType), SpecialIcon.valueOf(cardRace), cardReference, cardDescription);

                spellsList.add(spell);
                spellsImages.add(cardImage);
            } else if (type.contains("Trap")) {
                final var cardType = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("type").getAsString());
                final var cardRace = Utils.replaceByUnderscore(jsonElement1.getAsJsonObject().get("race").getAsString());
                final var cardReference = jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString();
                final var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                final var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                final var trap = new SpecialCards(cardName, SpecialType.valueOf(cardType), SpecialIcon.valueOf(cardRace), cardReference, cardDescription);

                trapsList.add(trap);
                trapsImages.add(cardImage);
            }
        });

        final var deck = new FabriqueDeck_YUGIOH();
        final var randomSize = deck.getRandomSize();

        while (deck.getDeckList().size() < randomSize) {
            for (var i = 0; i < randomSize / 2; i++) {
                final var randomIndex = (int) (Math.random() * monstersList.size());
                deck.addMonsterCard(monstersList.get(randomIndex), monstersImages.get(randomIndex));
            }

            final var randomIndex = new Random();
            for (var i = 0; i < randomSize / 2; i++) {
                switch (randomIndex.nextInt(0, 2)) {
                    case 0 -> {
                        final var randomIndexSpell = (int) (Math.random() * spellsList.size());
                        deck.addSpellCard(spellsList.get(randomIndexSpell), spellsImages.get(randomIndexSpell));
                    }
                    case 1 -> {
                        final var randomIndexTrap = (int) (Math.random() * trapsList.size());
                        deck.addTrapCard(trapsList.get(randomIndexTrap), trapsImages.get(randomIndexTrap));
                    }
                }
            }
        }

        final var deckList = deck.getDeckList();
        final var deckImages = deckList.values();
        deckPanel.setBorder(BorderFactory.createTitledBorder(null, "Vos cartes : " + deckList.size() + " dont " + deck.getMonstersCount() + " cartes monstres et " + deck.getSpecialsCount() + " cartes spéciales :", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));

        for (final var s : deckImages) {
            try {
                final var url = new URL(s);
                final var image = ImageIO.read(url);
                final var scaledImage = image.getScaledInstance(55, 70, Image.SCALE_SMOOTH); // Default size : 525 × 768
                final var picLabel = new JLabel(new ImageIcon(scaledImage));
                deckPanel.add(picLabel);
                deckPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            } catch (final IOException ioException) {
                final var errorLabel = new JLabel("Image error 404");
                deckPanel.add(errorLabel);
            }
        }
        Utils.displayFrame(frame);
    }
}
