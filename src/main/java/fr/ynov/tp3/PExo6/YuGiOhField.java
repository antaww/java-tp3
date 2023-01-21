package fr.ynov.tp3.PExo6;

import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.Utils;

import java.util.ArrayList;

public class YuGiOhField {
    private final ArrayList<MonsterCard> fieldCards;
    private final ArrayList<MonsterCard> playerCards;
    private final ArrayList<MonsterCard> opponentCards;
    private String currentPlayer;
    private int currentTurn;
    private int playerLifePoints;
    private int opponentLifePoints;

    public YuGiOhField() {
        fieldCards = new ArrayList<>();
        playerCards = new ArrayList<>();
        opponentCards = new ArrayList<>();
        currentPlayer = "";
        playerLifePoints = 8000;
        opponentLifePoints = 8000;
        currentTurn = 1;
    }

    public void displayLifePoints() {
        System.out.println("Joueur : " + playerLifePoints + " PV");
        System.out.println("Adversaire : " + opponentLifePoints + " PV");
    }

    public void pickRandomBeginner() {
        var random = (int) (Math.random() * 2);
        if (random == 0) {
            currentPlayer = "Joueur";
            System.out.println("Vous commencez la partie !");
        } else {
            currentPlayer = "Adversaire";
            System.out.println("L'adversaire commence la partie !");
        }
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void displayCurrentPlayer() {
        if(currentTurn != 1) {
            if (currentPlayer.equals("Joueur")) {
                System.out.println("\nC'est à votre tour !");
            } else {
                System.out.println("\nC'est au tour de l'adversaire !");
            }
        }
    }

    public void displayCurrentTurn() {
        System.out.println("Tour " + currentTurn);
    }

    public void addCardToField(MonsterCard card) {
        fieldCards.add(card);
    }

    public ArrayList<MonsterCard> getFieldCards() {
        return fieldCards;
    }

    public void addCardToPlayer(MonsterCard card) {
        playerCards.add(card);
    }

    public void addCardToOpponent(MonsterCard card) {
        opponentCards.add(card);
    }

    public void displayFieldCards() {
        System.out.println("Vos cartes :");
        getDeck(playerCards);

        System.out.println("Cartes de l'adversaire :");
        getDeck(opponentCards);
    }

    private void getDeck(ArrayList<MonsterCard> playerCards) {
        for (var i = 0; i < playerCards.size(); i++) {
            System.out.println("Carte " + (i + 1) + " : "
                    + playerCards.get(i).getName() + " - "
                    + (playerCards.get(i).getLevel() != -1 ? "Niveau " + playerCards.get(i).getLevel() : "Aucun niveau") + " - "
                    + playerCards.get(i).getAttribute() + " - ["
                    + Utils.translateString(playerCards.get(i).getTypes()) + "] - "
                    + playerCards.get(i).getStats());
            if (i == playerCards.size() - 1) {
                System.out.println();
            }
        }
    }

    public int getCardAttack(int index, String player) {
        switch (player) {
            case "Joueur" -> {
                return Integer.parseInt(playerCards.get(index).getStats().split(" ")[0].split("/")[1]);
            }
            case "Adversaire" -> {
                return Integer.parseInt(opponentCards.get(index).getStats().split(" ")[0].split("/")[1]);
            }
            default -> {
                return 0;
            }
        }
    }

    public String getCardName(int index, String player) {
        switch (player) {
            case "Joueur" -> {
                return playerCards.get(index).getName();
            }
            case "Adversaire" -> {
                return opponentCards.get(index).getName();
            }
            default -> {
                return "";
            }
        }
    }

    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Joueur") ? "Adversaire" : "Joueur";
        currentTurn++;
    }

    public void decreasePlayerLifePoints(int attackPoints, String cardName) {
        if (currentPlayer.equals("Joueur")) {
            opponentLifePoints -= attackPoints;
            System.out.println("Vous utilisez " + cardName + " ! Cela inflige " + attackPoints + " points de dégâts à l'adversaire !");
        } else {
            playerLifePoints -= attackPoints;
            System.out.println("L'adversaire utilise " + cardName + " ! Cela vous inflige " + attackPoints + " points de dégâts !");
        }
    }

    public boolean checkPlayerLost() {
        return playerLifePoints <= 0 || opponentLifePoints <= 0;
    }

    public void displayWinner() {
        if (playerLifePoints > 0) {
            System.out.println("Joueur l'emporte !");
        } else {
            System.out.println("L'adversaire l'emporte !");
        }
    }
}