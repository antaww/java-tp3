package fr.ynov.tp3.PExo6;

import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.Utils;

import java.util.ArrayList;

public class YuGiOhField {
    private final ArrayList<MonsterCard> fieldCards;
    private final ArrayList<MonsterCard> playerCards;
    private final ArrayList<MonsterCard> opponentCards;
    private String currentPlayer;
    private int playerLifePoints;
    private int opponentLifePoints;

    public YuGiOhField() {
        fieldCards = new ArrayList<>();
        playerCards = new ArrayList<>();
        opponentCards = new ArrayList<>();
        currentPlayer = "";
        playerLifePoints = 8000;
        opponentLifePoints = 8000;
        var matchWinsPlayer = 0;
        var matchWinsOpponent = 0;
        var currentTurn = 1;
    }

    public void displayLifePoints() {
        System.out.println("Joueur : " + playerLifePoints + " PV");
        System.out.println("Adversaire : " + opponentLifePoints + " PV");
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

        System.out.println("\nCartes de l'adversaire :");
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
        }
    }

    public int getCardAttack(int index) {
        var stats = fieldCards.get(index).getStats();
        return Integer.parseInt(stats.split(" ")[0].split("/")[1]);
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