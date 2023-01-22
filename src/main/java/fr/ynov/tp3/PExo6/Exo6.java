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
import java.util.InputMismatchException;

public class Exo6 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 6 - DU-DU-DU-DUEL !");

        var opponentCards = new JPanel();
        var playerCards = new JPanel();
        var textPanel = new JPanel();
        var hpPanel = new JPanel();
        var playerHpLabel = new JLabel();
        var opponentHpLabel = new JLabel();
        var textLabel = new JLabel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        opponentCards.setLayout(new BoxLayout(opponentCards, BoxLayout.X_AXIS));
        playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.X_AXIS));
        textPanel.setLayout(new BorderLayout()); //todo: change borderLayout to add rigidArea
        hpPanel.setLayout(new BoxLayout(hpPanel, BoxLayout.Y_AXIS));
        opponentCards.setBorder(BorderFactory.createTitledBorder(null, "Cartes de l'adversaire", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        playerCards.setBorder(BorderFactory.createTitledBorder(null, "Vos cartes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        opponentCards.add(Box.createRigidArea(new Dimension(10, 0)));
        playerCards.add(Box.createRigidArea(new Dimension(10, 0)));
        textPanel.setPreferredSize(new Dimension(650, 100));
        textLabel.setFont(new Font("Arial", Font.BOLD, 12));
        hpPanel.add(playerHpLabel);
        hpPanel.add(opponentHpLabel);
        hpPanel.setPreferredSize(new Dimension(130, 0));
        hpPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        textPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        textPanel.add(hpPanel, BorderLayout.WEST);
        textPanel.add(textLabel, BorderLayout.CENTER);
        bodyPanel.add(opponentCards);
        bodyPanel.add(playerCards);
        bodyPanel.add(textPanel);

        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        var monstersList = new ArrayList<MonsterCard>();
        var monstersImages = new ArrayList<String>();

        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            var type = jsonElement1.getAsJsonObject().get("type").getAsString();
            if (type.contains("Monster")) {
                var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                var cardLevel = jsonElement1.getAsJsonObject().has("level") ? jsonElement1.getAsJsonObject().get("level").getAsInt() : -1;
                var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                var cardReference = jsonElement1.getAsJsonObject().has("card_sets") && jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().size() > 0
                        ? jsonElement1.getAsJsonObject().get("card_sets").getAsJsonArray().get(0).getAsJsonObject().get("set_code").getAsString()
                        : "N/A";
                var cardAtk = jsonElement1.getAsJsonObject().has("atk") ? jsonElement1.getAsJsonObject().get("atk").getAsInt() : -1;
                var cardDef = jsonElement1.getAsJsonObject().has("def") ? jsonElement1.getAsJsonObject().get("def").getAsInt() : -1;
                var cardDescription = jsonElement1.getAsJsonObject().get("desc").getAsString();
                var cardImage = jsonElement1.getAsJsonObject().get("card_images").getAsJsonArray().get(0).getAsJsonObject().get("image_url").getAsString();
                var monster = new MonsterCard(cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);

                monstersList.add(monster);
                monstersImages.add(cardImage);
            }
        });

        var field = new YuGiOhField();
        var randomMonsters = new ArrayList<MonsterCard>();
        var randomMonstersImages = new ArrayList<String>();
        for (var i = 0; i < 6; i++) {
            var randomIndex = (int) (Math.random() * monstersList.size());
            randomMonsters.add(monstersList.get(randomIndex));
            randomMonstersImages.add(monstersImages.get(randomIndex));
            monstersList.remove(randomIndex);
            monstersImages.remove(randomIndex);
            field.addCardToField(randomMonsters.get(i));
        }

        var fieldCards = field.getFieldCards();
        displayFieldCards(opponentCards, playerCards, field, randomMonstersImages, fieldCards);
        playerHpLabel.setText(field.getPlayerHp());
        opponentHpLabel.setText(field.getOpponentHp());

        //start game
        var firstPlayer = field.pickRandomBeginner();
        switch (firstPlayer) {
            case "Joueur" -> {
                textLabel.setText("Vous commencez la partie !");
            }
            case "Adversaire" -> {
                textLabel.setText("L'adversaire commence la partie !");
            }
        }

        field.getPlayerCards().forEach(card -> {
            var cardImage = playerCards.getComponent(field.getPlayerCards().indexOf(card) + 1);
            cardImage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
//                    if (field.getCurrentPlayer().equals("Joueur")) {}
                    field.decreasePlayerLifePoints(field.getCardAttack(field.getPlayerCards().indexOf(card), field.getCurrentPlayer()), field.getCardName(field.getPlayerCards().indexOf(card), field.getCurrentPlayer()));
                    playerHpLabel.setText(field.getPlayerHp());
                    opponentHpLabel.setText(field.getOpponentHp());
                    field.changeCurrentPlayer();
                    textLabel.setText("C'est au tour de l'adversaire !");
                }
            });
        });



        //todo: replace comments with GUI adaptation
//        while (!field.checkPlayerLost()) {
//            field.displayCurrentPlayer();
//            field.displayCurrentTurn();
//            field.displayLifePoints();
//            var cardIndex = cardPrompt(field);
//            field.decreasePlayerLifePoints(field.getCardAttack(cardIndex, field.getCurrentPlayer()), field.getCardName(cardIndex, field.getCurrentPlayer()));
//            field.changeCurrentPlayer();
//        }
//        field.displayWinner();

        Utils.displayFrame(frame);
    }

    private static void displayFieldCards(JPanel opponentCards, JPanel playerCards, YuGiOhField field, ArrayList<String> randomMonstersImages, ArrayList<MonsterCard> fieldCards) {
        for (var i = 0; i < 3; i++) {
            var randomIndexPlayer = (int) (Math.random() * fieldCards.size());
            field.addCardToPlayer(fieldCards.get(randomIndexPlayer));
            field.addCardImageToPlayer(randomMonstersImages.get(randomIndexPlayer));
            addCardsToField(playerCards, randomMonstersImages, randomIndexPlayer);
            fieldCards.remove(randomIndexPlayer);
            randomMonstersImages.remove(randomIndexPlayer);
            var randomIndexOpponent = (int) (Math.random() * fieldCards.size());
            field.addCardToOpponent(fieldCards.get(randomIndexOpponent));
            field.addCardImageToOpponent(randomMonstersImages.get(randomIndexOpponent));
            addCardsToField(opponentCards, randomMonstersImages, randomIndexOpponent);
            fieldCards.remove(randomIndexOpponent);
            randomMonstersImages.remove(randomIndexOpponent);
        }
    }

    private static void addCardsToField(JPanel opponentCards, ArrayList<String> randomMonstersImages, int randomIndexOpponent) {
        try {
            var url = new URL(randomMonstersImages.get(randomIndexOpponent));
            var image = ImageIO.read(url);
            var scaledImage = image.getScaledInstance(220, 280, Image.SCALE_SMOOTH); // Default size : 525 × 768
            var picLabel = new JLabel(new ImageIcon(scaledImage));
            opponentCards.add(picLabel);
            opponentCards.add(Box.createRigidArea(new Dimension(10, 0)));
        } catch (IOException ioException) {
            var errorLabel = new JLabel("Image error 404");
            opponentCards.add(errorLabel);
        }
    }

    private static int cardPrompt(YuGiOhField field) {
        var scanner = new java.util.Scanner(System.in);
        switch (field.getCurrentPlayer()) {
            case "Joueur" -> {
                while (true) {
                    System.out.print("Entrez le numéro de la carte avec laquelle vous voulez attaquer (1, 2, ou 3): ");
                    try {
                        var input = scanner.nextInt();
                        switch (input) {
                            case 1, 2, 3 -> {
                                return input - 1;
                            }
                            default -> System.out.println("Entrée invalide. Veuillez entrer une valeur de 1, 2 ou 3.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                        scanner.next();
                    }
                }
            }
            case "Adversaire" -> {
                return (int) (Math.random() * 3);
            }
        }
        return 0;
    }
}
