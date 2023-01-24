package fr.ynov.tp3.PExo6;

import fr.ynov.tp3.PExo3.MonsterCard;

import java.util.ArrayList;

/**
 * Classe YuGiOhField : gère les informations relatives à un champ de jeu de Yu-Gi-Oh!
 * Cette classe permet de stocker les informations des cartes Monstre présentes sur le champ de jeu, les cartes Monstre du joueur, les cartes Monstre de l'adversaire, les points de vie des joueurs et le tour en cours.
 * Elle utilise des ArrayList pour stocker les informations.
 * Elle contient des méthodes pour ajouter des cartes sur le champ de jeu, pour récupérer les cartes sur le champ de jeu, pour récupérer les points de vie des joueurs, pour sélectionner un joueur au hasard pour commencer la partie, pour changer de joueur, pour récupérer les informations d'une carte, pour diminuer les points de vie d'un joueur et pour vérifier si la partie est en cours.
 * Elle utilise également des switch pour récupérer les informations d'une carte en fonction du joueur.
 *
 * @see fr.ynov.tp3.PExo3.MonsterCard
 * @see java.util.ArrayList
 */
public class YuGiOhField {
    private final ArrayList<MonsterCard> fieldCards = new ArrayList<>();
    private final ArrayList<MonsterCard> playerCards = new ArrayList<>();
    private final ArrayList<String> playerCardsImages = new ArrayList<>();
    private final ArrayList<MonsterCard> opponentCards = new ArrayList<>();
    private final ArrayList<String> opponentCardsImages = new ArrayList<>();
    private final ArrayList<MonsterCard> graveyardCards = new ArrayList<>();
    private String currentPlayer = "";
    private int currentTurn = 1;
    private int playerLifePoints = 8000;
    private int opponentLifePoints = 8000;
    private boolean isGameRunning = false;

    /**
     * Méthode getGameStatus : retourne l'état de la partie.
     *
     * @return état de la partie
     */
    public boolean getGameStatus() {
        return isGameRunning;
    }

    /**
     * Méthode changeGameStatus : change l'état de la partie.
     * Cette méthode permet de changer l'état de la partie.
     */
    public void changeGameStatus() {
        isGameRunning = !isGameRunning;
    }

    /**
     * Méthode addCardToField : ajoute une carte Monstre sur le champ de jeu.
     *
     * @param card carte Monstre à ajouter sur le champ de jeu
     */
    public void addCardToField(final MonsterCard card) {
        fieldCards.add(card);
    }

    /**
     * Méthode getFieldCards : retourne les cartes Monstre présentes sur le champ de jeu.
     *
     * @return cartes Monstre présentes sur le champ de jeu
     */
    public ArrayList<MonsterCard> getFieldCards() {
        return fieldCards;
    }

    /**
     * Méthode addCardToPlayer : ajoute une carte Monstre au joueur.
     *
     * @param card carte Monstre à ajouter au joueur
     */
    public void addCardToPlayer(final MonsterCard card) {
        playerCards.add(card);
    }

    /**
     * Méthode addCardImageToPlayer : ajoute une image de carte Monstre au joueur.
     *
     * @param cardImage image de carte Monstre à ajouter au joueur
     */
    public void addCardImageToPlayer(final String cardImage) {
        playerCardsImages.add(cardImage);
    }

    /**
     * Méthode addCardToOpponent : ajoute une carte Monstre à l'adversaire.
     *
     * @param card carte Monstre à ajouter à l'adversaire
     */
    public void addCardToOpponent(final MonsterCard card) {
        opponentCards.add(card);
    }

    /**
     * Méthode addCardImageToOpponent : ajoute une image de carte Monstre à l'adversaire.
     *
     * @param cardImage image de carte Monstre à ajouter à l'adversaire
     */
    public void addCardImageToOpponent(final String cardImage) {
        opponentCardsImages.add(cardImage);
    }

    /**
     * Méthode getOpponentsCards : retourne les cartes Monstre de l'adversaire.
     *
     * @return cartes Monstre de l'adversaire
     */
    public ArrayList<MonsterCard> getOpponentCards() {
        return opponentCards;
    }

    /**
     * Méthode getPlayerHp : retourne les points de vie du joueur.
     *
     * @return points de vie du joueur
     */
    public String getPlayerHp() {
        return "Vous : " + playerLifePoints + " PV";
    }

    /**
     * Méthode getOpponentHp : retourne les points de vie de l'adversaire.
     *
     * @return points de vie de l'adversaire
     */
    public String getOpponentHp() {
        return "Adversaire : " + opponentLifePoints + " PV";
    }

    /**
     * Méthode pickRandomBeginner : sélectionne un joueur au hasard pour commencer la partie.
     *
     * @return joueur qui commence la partie
     */
    public String pickRandomBeginner() {
        final var random = (int) (Math.random() * 2);
        if (random == 0) {
            currentPlayer = "Joueur";
        } else {
            currentPlayer = "Adversaire";
        }
        return currentPlayer;
    }

    /**
     * Méthode getCurrentPlayer : retourne le joueur qui doit jouer.
     *
     * @return joueur qui doit jouer
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Méthode displayCurrentPlayer : affiche le joueur qui doit jouer.
     *
     * @return joueur qui doit jouer
     */
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

    /**
     * Méthode getCardAttack : retourne l'attaque d'une carte Monstre.
     *
     * @param index  index de la carte Monstre
     * @param player joueur qui possède la carte Monstre
     * @return attaque de la carte Monstre
     */
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

    /**
     * Méthode getCardName : retourne le nom d'une carte Monstre.
     *
     * @param index  index de la carte Monstre
     * @param player joueur qui possède la carte Monstre
     * @return nom de la carte Monstre
     */
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

    /**
     * Méthode changeCurrentPlayer : change le joueur qui doit jouer.
     */
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Joueur") ? "Adversaire" : "Joueur";
        currentTurn++;
    }

    /**
     * Méthode decreasePlayerLifePoints : décrémente les points de vie du joueur ou de l'adversaire.
     *
     * @param attackPoints points d'attaque de la carte Monstre
     * @param cardName     nom de la carte Monstre
     * @return message à afficher
     */
    public String decreasePlayerLifePoints(final int attackPoints, final String cardName) {
        if (currentPlayer.equals("Joueur")) {
            opponentLifePoints -= attackPoints;
            return "<html>Vous utilisez " + cardName + " !<br>Cela inflige " + attackPoints + " points de dégâts à l'adversaire !</html>";
        } else {
            playerLifePoints -= attackPoints;
            return "<html>L'adversaire utilise " + cardName + " !<br>Cela vous inflige " + attackPoints + " points de dégâts !</html>";
        }
    }

    /**
     * Méthode checkPlayerLost : vérifie si le joueur ou l'adversaire a perdu.
     *
     * @return true si le joueur ou l'adversaire a perdu, false sinon
     */
    public boolean checkPlayerLost() {
        return playerLifePoints <= 0 || opponentLifePoints <= 0;
    }

    /**
     * Méthode displayWinner : affiche le gagnant de la partie.
     *
     * @return gagnant de la partie
     */
    public String displayWinner() {
        if (playerLifePoints > 0) {
            return "Vous l'emportez !";
        } else {
            return "L'adversaire l'emporte !";
        }
    }
}