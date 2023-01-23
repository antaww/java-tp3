package fr.ynov.tp3.PExo6;

import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.Utils;

import java.util.ArrayList;

public class YuGiOhField {
    private final ArrayList<MonsterCard> fieldCards;
    private final ArrayList<MonsterCard> playerCards;
    private final ArrayList<String> playerCardsImages;
    private final ArrayList<MonsterCard> opponentCards;
    private final ArrayList<String> opponentCardsImages;
    private String currentPlayer;
    private int currentTurn;
    private int playerLifePoints;
    private int opponentLifePoints;
    private boolean isGameRunning;

    public YuGiOhField() {
        fieldCards = new ArrayList<>();
        playerCards = new ArrayList<>();
        playerCardsImages = new ArrayList<>();
        opponentCards = new ArrayList<>();
        opponentCardsImages = new ArrayList<>();
        currentPlayer = "";
        playerLifePoints = 8000;
        opponentLifePoints = 8000;
        currentTurn = 1;
        isGameRunning = false;
    }

    public boolean getGameStatus() {
        return isGameRunning;
    }

    public void changeGameStatus() {
        isGameRunning = !isGameRunning;
        System.out.println("Game status changed to " + isGameRunning);
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

    public void addCardImageToPlayer(String cardImage) {
        playerCardsImages.add(cardImage);
    }

    public void addCardToOpponent(MonsterCard card) {
        opponentCards.add(card);
    }

    public void addCardImageToOpponent(String cardImage) {
        opponentCardsImages.add(cardImage);
    }

    public ArrayList<MonsterCard> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<MonsterCard> getOpponentCards() {
        return opponentCards;
    }

    public String getPlayerHp() {
        return "Vous : " + playerLifePoints + " PV";
    }

    public String getOpponentHp() {
        return "Adversaire : " + opponentLifePoints + " PV";
    }

    public String pickRandomBeginner() {
        var random = (int) (Math.random() * 2);
        if (random == 0) {
            currentPlayer = "Joueur";
        } else {
            currentPlayer = "Adversaire";
        }
        return currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String displayCurrentPlayer() {
        if (currentTurn != 1) {
            if (currentPlayer.equals("Joueur")) {
                return "C'est à votre tour !";
            } else {
                return "C'est au tour de l'adversaire !";
            }
        }
        return "";
    }

    public void displayCurrentTurn() {
        System.out.println("Tour " + currentTurn);
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