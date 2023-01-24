package fr.ynov.tp3.PExo6;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Exo6 {
    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 6 - DU-DU-DU-DUEL !");

        final var opponentCards = new JPanel();
        final var playerCards = new JPanel();
        final var textPanel = new JPanel();
        final var hpPanel = new JPanel();
        final var playerHpLabel = new JLabel();
        final var opponentHpLabel = new JLabel();
        final var textLabel = new JLabel();
        final var attackLabel = new JLabel();
        final var controlButton = new JButton("Commencer le duel !");
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        opponentCards.setLayout(new BoxLayout(opponentCards, BoxLayout.X_AXIS));
        playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.X_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        hpPanel.setLayout(new BoxLayout(hpPanel, BoxLayout.Y_AXIS));
        opponentCards.setBorder(BorderFactory.createTitledBorder(null, "Cartes de l'adversaire", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        playerCards.setBorder(BorderFactory.createTitledBorder(null, "Vos cartes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        opponentCards.add(Box.createRigidArea(new Dimension(10, 0)));
        playerCards.add(Box.createRigidArea(new Dimension(10, 0)));
        textPanel.setPreferredSize(new Dimension(650, 100));
        textLabel.setFont(new Font("Arial", Font.BOLD, 12));
        hpPanel.add(playerHpLabel);
        hpPanel.add(opponentHpLabel);
        textPanel.add(hpPanel);
        textPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        textPanel.add(textLabel);
        textPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        textPanel.add(attackLabel);
        textPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        textPanel.add(controlButton);
        bodyPanel.add(opponentCards);
        bodyPanel.add(playerCards);
        bodyPanel.add(textPanel);

        final JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
        } catch (final FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        final var monstersList = new ArrayList<MonsterCard>();
        final var monstersImages = new ArrayList<String>();

        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            final var type = jsonElement1.getAsJsonObject().get("type").getAsString();
            if (type.contains("Monster")) {
                final var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
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
            }
        });

        final var field = new YuGiOhField();
        //todo: convert monsterImages to field.getCardImages()
        final var randomMonsters = new ArrayList<MonsterCard>();
        final var randomMonstersImages = new ArrayList<String>();
        for (var i = 0; i < 6; i++) {
            final var randomIndex = (int) (Math.random() * monstersList.size());
            randomMonsters.add(monstersList.get(randomIndex));
            randomMonstersImages.add(monstersImages.get(randomIndex));
            monstersList.remove(randomIndex);
            monstersImages.remove(randomIndex);
            field.addCardToField(randomMonsters.get(i));
        }
        final var fieldCards = field.getFieldCards();
        displayFieldCards(opponentCards, playerCards, field, randomMonstersImages, fieldCards);
        playerHpLabel.setText(field.getPlayerHp());
        opponentHpLabel.setText(field.getOpponentHp());

        controlButton.addActionListener(e -> {
            //todo: fix button once game is over
            field.changeGameStatus();
            controlButton.setText(field.getGameStatus() ? "Arrêter le duel." : "Commencer le duel !");
            if (field.getGameStatus()) {
                final var firstPlayer = field.pickRandomBeginner();
                //todo: fix labeled rule's code block is redundant
                switch (firstPlayer) {
                    case "Joueur" -> {
                        textLabel.setText("<html>Vous commencez le duel !<br>Cliquez sur une carte pour l'utiliser</html>");
                    }
                    case "Adversaire" -> {
                        textLabel.setText("L'adversaire commence le duel !");
                    }
                }
            } else {
                Exo6.main(frame);
            }

            if (field.getCurrentPlayer().equals("Adversaire") & field.getGameStatus()) {
                playOpponent(playerHpLabel, opponentHpLabel, textLabel, field, attackLabel, textPanel, controlButton);
            }
        });

        var labelIndex = 0;
        for (var i = 0; i < playerCards.getComponentCount(); i++) {
            if (playerCards.getComponent(i) instanceof JLabel) {
                final var index = labelIndex;
                playerCards.getComponent(i).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        if (field.getCurrentPlayer().equals("Adversaire") || !field.getGameStatus()) return;
                        super.mouseClicked(e);
                        attackLabel.setText(field.decreasePlayerLifePoints(field.getCardAttack(index, field.getCurrentPlayer()), field.getCardName(index, field.getCurrentPlayer())));
                        playerHpLabel.setText(field.getPlayerHp());
                        opponentHpLabel.setText(field.getOpponentHp());
                        field.changeCurrentPlayer();
                        textLabel.setText(field.displayCurrentPlayer());
                        if (isGameFinished(field, textPanel, controlButton, textLabel)) return;
                        playOpponent(playerHpLabel, opponentHpLabel, textLabel, field, attackLabel, textPanel, controlButton);
                    }
                });
                labelIndex++;
            }
        }

        //todo: if button duel stop : reset the game
//        field.displayWinner();

        Utils.displayFrame(frame);
    }

    private static boolean isGameFinished(final YuGiOhField field, final JPanel textPanel, final JButton controlButton, final JLabel textLabel) {
        if (field.checkPlayerLost()) {
            //todo: fix for loop, so that it hides all the labels except the last one & set the text of the last one to the winner
            for (var i = 0; i < textPanel.getComponentCount() - 1; i++) {
                if (textPanel.getComponent(i) instanceof JLabel) {
                    textPanel.getComponent(i).setVisible(false);
                }
            }
            field.changeGameStatus();
            controlButton.setText(field.getGameStatus() ? "Arrêter le duel." : "Commencer le duel !");
            textLabel.setText(field.displayWinner());
            return true;
        }
        return false;
    }

    private static void playOpponent(final JLabel playerHpLabel, final JLabel opponentHpLabel, final JLabel textLabel, final YuGiOhField field, final JLabel attackLabel, final JPanel textPanel, final JButton controlButton) {
        if (field.getCurrentPlayer().equals("Adversaire")) {
            final var randomIndex = (int) (Math.random() * field.getOpponentCards().size());
            attackLabel.setText(field.decreasePlayerLifePoints(field.getCardAttack(randomIndex, field.getCurrentPlayer()), field.getCardName(randomIndex, field.getCurrentPlayer())));
            playerHpLabel.setText(field.getPlayerHp());
            opponentHpLabel.setText(field.getOpponentHp());
            if (isGameFinished(field, textPanel, controlButton, textLabel)) return;
            field.changeCurrentPlayer();
            textLabel.setText(field.displayCurrentPlayer());
        }
    }

    private static void displayFieldCards(final JPanel opponentCards, final JPanel playerCards, final YuGiOhField field, final ArrayList<String> randomMonstersImages, final ArrayList<MonsterCard> fieldCards) {
        for (var i = 0; i < 3; i++) {
            final var randomIndexPlayer = (int) (Math.random() * fieldCards.size());
            field.addCardToPlayer(fieldCards.get(randomIndexPlayer));
            field.addCardImageToPlayer(randomMonstersImages.get(randomIndexPlayer));
            addCardsToField(playerCards, randomMonstersImages, randomIndexPlayer);
            fieldCards.remove(randomIndexPlayer);
            randomMonstersImages.remove(randomIndexPlayer);
            final var randomIndexOpponent = (int) (Math.random() * fieldCards.size());
            field.addCardToOpponent(fieldCards.get(randomIndexOpponent));
            field.addCardImageToOpponent(randomMonstersImages.get(randomIndexOpponent));
            addCardsToField(opponentCards, randomMonstersImages, randomIndexOpponent);
            fieldCards.remove(randomIndexOpponent);
            randomMonstersImages.remove(randomIndexOpponent);
        }
    }

    private static void addCardsToField(final JPanel opponentCards, final ArrayList<String> randomMonstersImages, final int randomIndexOpponent) {
        try {
            final var url = new URL(randomMonstersImages.get(randomIndexOpponent));
            final var image = ImageIO.read(url);
            final var scaledImage = image.getScaledInstance(220, 280, Image.SCALE_SMOOTH); // Default size : 525 × 768
            final var picLabel = new JLabel(new ImageIcon(scaledImage));
            opponentCards.add(picLabel);
            opponentCards.add(Box.createRigidArea(new Dimension(10, 0)));
        } catch (final IOException ioException) {
            final var errorLabel = new JLabel("Image error 404");
            opponentCards.add(errorLabel);
        }
    }
}
