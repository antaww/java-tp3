package fr.ynov.tp3.PExo6;

import fr.ynov.tp3.PExo3.MonsterCard;

import java.util.ArrayList;

public class YuGiOhField {
    private final ArrayList<MonsterCard> fieldCards = new ArrayList<>();
    private final ArrayList<MonsterCard> playerCards = new ArrayList<>();
    private final ArrayList<String> playerCardsImages = new ArrayList<>();
    private final ArrayList<MonsterCard> opponentCards = new ArrayList<>();
    private final ArrayList<String> opponentCardsImages = new ArrayList<>();
    private String currentPlayer = "";
    private int currentTurn = 1;
    private int playerLifePoints = 8000;
    private int opponentLifePoints = 8000;
    private boolean isGameRunning = false;

    public boolean getGameStatus() {
        return isGameRunning;
    }

    public void changeGameStatus() {
        isGameRunning = !isGameRunning;
    }

    public void addCardToField(final MonsterCard card) {
        fieldCards.add(card);
    }

    public ArrayList<MonsterCard> getFieldCards() {
        return fieldCards;
    }

    public void addCardToPlayer(final MonsterCard card) {
        playerCards.add(card);
    }

    public void addCardImageToPlayer(final String cardImage) {
        playerCardsImages.add(cardImage);
    }

    public void addCardToOpponent(final MonsterCard card) {
        opponentCards.add(card);
    }

    public void addCardImageToOpponent(final String cardImage) {
        opponentCardsImages.add(cardImage);
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
        final var random = (int) (Math.random() * 2);
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
                return "<html>C'est à votre tour !<br>Cliquez sur une carte pour l'utiliser</html>";
            } else {
                return "C'est au tour de l'adversaire !";
            }
        }
        return "";
    }

    public int getCardAttack(final int index, final String player) {
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

    public String getCardName(final int index, final String player) {
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

    public String decreasePlayerLifePoints(final int attackPoints, final String cardName) {
        //todo: take defense points into account (if the card has any)
        if (currentPlayer.equals("Joueur")) {
            opponentLifePoints -= attackPoints;
            return "<html>Vous utilisez " + cardName + " !<br>Cela inflige " + attackPoints + " points de dégâts à l'adversaire !</html>";
        } else {
            playerLifePoints -= attackPoints;
            return "<html>L'adversaire utilise " + cardName + " !<br>Cela vous inflige " + attackPoints + " points de dégâts !</html>";
        }
    }

    public boolean checkPlayerLost() {
        return playerLifePoints <= 0 || opponentLifePoints <= 0;
    }

    public String displayWinner() {
        if (playerLifePoints > 0) {
            return "Vous l'emportez !";
        } else {
            return "L'adversaire l'emporte !";
        }
    }
}