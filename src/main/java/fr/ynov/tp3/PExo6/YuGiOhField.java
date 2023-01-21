package fr.ynov.tp3.PExo6;

import java.util.ArrayList;

public class YuGiOhField {
    private final ArrayList<ArrayList<String>> fieldCards;
    private String currentPlayer;
    private int playerLifePoints;
    private int opponentLifePoints;

    public YuGiOhField() {
        fieldCards = new ArrayList<>();
        currentPlayer = "";
        playerLifePoints = 8000;
        opponentLifePoints = 8000;
        var matchWinsPlayer = 0;
        var matchWinsOpponent = 0;
        var currentTurn = 1;
    }

    public void displayLifePoints() {
        System.out.println("Jouer : " + playerLifePoints);
        System.out.println("Adversaire : " + opponentLifePoints);
    }

    public void addCardToField(ArrayList<String> card) {
        fieldCards.add(card);
    }

    public void displayFieldCards() {
        for (var i = 0; i < fieldCards.size(); i++) {
            System.out.println("Carte " + (i+1) + " : " + getCardName(i) + " - Niveau " + getCardLevel(i) + " - " + getCardAttribute(i) + " - " + getCardTypes(i) + " - [ATK/" + getCardAtk(i) + " DEF/" + getCardDef(i) + "]");
        }
    }

    public ArrayList<String> getCard(int index) {
        return fieldCards.get(index);
    }

    public String getCardName(int index) {
        return getCard(index).get(0);
    }

    public int getCardLevel(int index) {
        return Integer.parseInt(getCard(index).get(1));
    }

    public String getCardAttribute(int index) {
        return getCard(index).get(2);
    }

    public String getCardTypes(int index) {
        return getCard(index).get(3);
    }

    public int getCardAtk(int index) {
        return Integer.parseInt(getCard(index).get(4));
    }

    public int getCardDef(int index) {
        return Integer.parseInt(getCard(index).get(5));
    }

    public void changeCurrentPlayer() {
        currentPlayer = (currentPlayer.equals("")) ? "Joueur" : "Adversaire";
    }

    public void decreasePlayerLifePoints(int points) {
        if (currentPlayer.equals("Joueur")) {
            playerLifePoints -= points;
        } else {
            opponentLifePoints -= points;
        }
    }

    public boolean checkPlayerLost() {
        return playerLifePoints <= 0 || opponentLifePoints <= 0;
    }

    public void displayWinner() {
        if (playerLifePoints <= 0) {
            System.out.println("Joueur l'emporte !");
        } else {
            System.out.println("Adversaire l'emporte !");
        }
    }
}