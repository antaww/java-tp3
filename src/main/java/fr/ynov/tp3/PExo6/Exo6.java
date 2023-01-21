package fr.ynov.tp3.PExo6;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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

        var monstersList = new ArrayList<ArrayList<String>>();

        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            var type = jsonElement1.getAsJsonObject().get("type").getAsString();
            if (type.contains("Monster")) {
                var monster = new ArrayList<String>();
                var cardName = jsonElement1.getAsJsonObject().get("name").getAsString();
                var cardLevel = jsonElement1.getAsJsonObject().has("level") ? jsonElement1.getAsJsonObject().get("level").getAsInt() : -1;
                var cardAttribute = jsonElement1.getAsJsonObject().get("attribute").getAsString();
                var cardTypes = jsonElement1.getAsJsonObject().get("race").getAsString() + " " + jsonElement1.getAsJsonObject().get("type").getAsString();
                var cardAtk = jsonElement1.getAsJsonObject().has("atk") ? jsonElement1.getAsJsonObject().get("atk").getAsInt() : -1;
                var cardDef = jsonElement1.getAsJsonObject().has("def") ? jsonElement1.getAsJsonObject().get("def").getAsInt() : -1;

                monster.add(cardName);
                monster.add(String.valueOf(cardLevel));
                monster.add(cardAttribute); //todo: attribute translation
                monster.add(cardTypes); //todo: types translation
                monster.add(String.valueOf(cardAtk));
                monster.add(String.valueOf(cardDef));
                monstersList.add(monster);
            }
        });

        var randomMonsters = new ArrayList<ArrayList<String>>();
        for (var i = 0; i < 3; i++) {
            var randomIndex = (int) (Math.random() * monstersList.size());
            randomMonsters.add(monstersList.get(randomIndex));
            monstersList.remove(randomIndex);
        }

        var field = new YuGiOhField();
        for (var i = 0; i < 3; i++) {
            field.addCardToField(randomMonsters.get(i));
        }

        field.displayFieldCards();
        field.displayLifePoints();
        field.decreasePlayerLifePoints(field.getCardAtk(promptInt()));

        field.displayLifePoints();
    }

    private static int promptInt() {
        var scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.print("Entrez le numéro de la carte avec laquelle voulez attaquer (1, 2, ou 3): ");
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
                scanner.next(); // Discard the input
            }
        }
    }

}
