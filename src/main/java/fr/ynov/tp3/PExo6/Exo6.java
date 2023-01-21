package fr.ynov.tp3.PExo6;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Exo6 {
    public static void main(String[] args) {
        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        var monstersList = new ArrayList<MonsterCard>();

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
                var monster = new MonsterCard(cardName, cardLevel, Attribute.valueOf(cardAttribute), cardTypes, cardReference, cardAtk, cardDef, cardDescription);

                monstersList.add(monster);
            }
        });

        var field = new YuGiOhField();
        var randomMonsters = new ArrayList<MonsterCard>();
        for (var i = 0; i < 6; i++) {
            var randomIndex = (int) (Math.random() * monstersList.size());
            randomMonsters.add(monstersList.get(randomIndex));
            monstersList.remove(randomIndex);
            field.addCardToField(randomMonsters.get(i));
        }

        var fieldCards = field.getFieldCards();
        for (var i = 0; i < 3; i++) {
            var randomIndexPlayer = (int) (Math.random() * fieldCards.size());
            field.addCardToPlayer(fieldCards.get(randomIndexPlayer));
            fieldCards.remove(randomIndexPlayer);
            var randomIndexOpponent = (int) (Math.random() * fieldCards.size());
            field.addCardToOpponent(fieldCards.get(randomIndexOpponent));
            fieldCards.remove(randomIndexOpponent);
        }

        //start game
        field.displayFieldCards();
        field.pickRandomBeginner();
        while (!field.checkPlayerLost()) {
            field.displayCurrentPlayer();
            field.displayCurrentTurn();
            field.displayLifePoints();
            var cardIndex = cardPrompt(field);
            field.decreasePlayerLifePoints(field.getCardAttack(cardIndex, field.getCurrentPlayer()), field.getCardName(cardIndex, field.getCurrentPlayer()));
            field.changeCurrentPlayer();
        }
        field.displayWinner();
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
