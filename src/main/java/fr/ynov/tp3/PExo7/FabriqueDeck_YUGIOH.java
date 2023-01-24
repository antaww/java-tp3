package fr.ynov.tp3.PExo7;

import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PExo4.SpecialCards;
import fr.ynov.tp3.PExo5.AMonstre;
import fr.ynov.tp3.PExo5.ICarteYuGiOh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Classe FabriqueDeck_YUGIOH : création d'une classe pour fabriquer un deck de cartes Yu-Gi-Oh!
 * Cette classe permet de fabriquer un deck de cartes Yu-Gi-Oh!.
 * Elle utilise les classes MonsterCard, SpecialCards et ICarteYuGiOh pour fabriquer un deck de cartes Yu-Gi-Oh!.
 * Elle utilise la classe Random pour générer un nombre aléatoire.
 * Elle utilise les classes ArrayList et HashMap pour stocker les cartes et les images des cartes.
 *
 * @see fr.ynov.tp3.PExo3.MonsterCard
 * @see fr.ynov.tp3.PExo4.SpecialCards
 * @see fr.ynov.tp3.PExo5.ICarteYuGiOh
 * @see java.util.Random
 * @see java.util.ArrayList
 * @see java.util.HashMap
 */
public class FabriqueDeck_YUGIOH {
    private final ArrayList<MonsterCard> monsterCards = new ArrayList<>();
    private final ArrayList<String> monsterCardsImages = new ArrayList<>();
    private final ArrayList<SpecialCards> spellCards = new ArrayList<>();
    private final ArrayList<String> spellCardsImages = new ArrayList<>();
    private final ArrayList<SpecialCards> trapCards = new ArrayList<>();
    private final ArrayList<String> trapCardsImages = new ArrayList<>();
    private final Map<ICarteYuGiOh, String> deckList = new HashMap<>();
    private final int randomSize;

    /**
     * Constructeur FabriqueDeck_YUGIOH : création d'un constructeur pour fabriquer un deck de cartes Yu-Gi-Oh!
     * Ce constructeur permet de générer un nombre aléatoire entre 40 et 60, et de vérifier que ce nombre est pair, ce nombre sera la taille du deck.
     */
    public FabriqueDeck_YUGIOH() {
        int randomNum;
        do {
            final var random = new Random();
            randomNum = random.nextInt(21) + 40;
        } while (randomNum % 2 == 0);
        randomSize = randomNum;
    }

    /**
     * Méthode addCardToDeck : ajout d'une carte au deck
     * Cette méthode permet d'ajouter une carte au deck, si le deck n'est pas plein, et si le nombre de cartes monstres ou de cartes magie/piège n'est pas supérieur à la moitié de la taille du deck.
     *
     * @param card     Carte à ajouter au deck
     * @param cardImage Image de la carte à ajouter au deck
     */
    public void addCardToDeck(final ICarteYuGiOh card, final String cardImage) {
        if (deckList.size() <= randomSize) {
            if (card instanceof AMonstre) {
                final var monsterCount = deckList.keySet().stream().filter(card1 -> card1 instanceof AMonstre).count();
                if (monsterCount <= (randomSize / 2)) {
                    deckList.put(card, cardImage);
                }
            } else {
                final var spellTrapCount = deckList.keySet().stream().filter(card1 -> !(card1 instanceof AMonstre)).count();
                if (spellTrapCount <= (randomSize / 2)) {
                    deckList.put(card, cardImage);
                }
            }
        }
    }

    /**
     * Méthode addMonsterCard : ajout d'une carte monstre au deck
     * Cette méthode permet d'ajouter une carte monstre au deck, si le deck n'est pas plein, et si le nombre de cartes monstres n'est pas supérieur à la moitié de la taille du deck.
     *
     * @param card     Carte monstre à ajouter au deck
     * @param cardImage Image de la carte monstre à ajouter au deck
     */
    public void addMonsterCard(final MonsterCard card, final String cardImage) {
        monsterCards.add(card);
        monsterCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    /**
     * Méthode addSpellCard : ajout d'une carte magie/piège au deck
     * Cette méthode permet d'ajouter une carte magie/piège au deck, si le deck n'est pas plein, et si le nombre de cartes magie/piège n'est pas supérieur à la moitié de la taille du deck.
     *
     * @param card     Carte magie/piège à ajouter au deck
     * @param cardImage Image de la carte magie/piège à ajouter au deck
     */
    public void addSpellCard(final SpecialCards card, final String cardImage) {
        spellCards.add(card);
        spellCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    /**
     * Méthode addTrapCard : ajout d'une carte piège au deck
     * Cette méthode permet d'ajouter une carte piège au deck, si le deck n'est pas plein, et si le nombre de cartes piège n'est pas supérieur à la moitié de la taille du deck.
     *
     * @param card     Carte piège à ajouter au deck
     * @param cardImage Image de la carte piège à ajouter au deck
     */
    public void addTrapCard(final SpecialCards card, final String cardImage) {
        trapCards.add(card);
        trapCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    /**
     * Méthode getDeckList : récupération de la liste du deck
     * Cette méthode permet de récupérer la liste du deck.
     *
     * @return La liste du deck
     * @see java.util.Map
     * @see fr.ynov.tp3.PExo5.ICarteYuGiOh
     */
    public Map<ICarteYuGiOh, String> getDeckList() {
        return deckList;
    }

    /**
     * Méthode getRandomSize : récupération de la taille du deck
     *
     * @return La taille du deck
     */
    public int getRandomSize() {
        return randomSize;
    }

    /**
     * Méthode getMonstersCount : récupération du nombre de cartes monstres dans le deck
     *
     * @return Le nombre de cartes monstres dans le deck
     */
    public int getMonstersCount() {
        return (int) deckList.keySet().stream().filter(card1 -> card1 instanceof AMonstre).count();
    }

    /**
     * Méthode getSpecialsCount : récupération du nombre de cartes magie/piège dans le deck
     *
     * @return Le nombre de cartes magie/piège dans le deck
     */
    public int getSpecialsCount() {
        return (int) deckList.keySet().stream().filter(card1 -> !(card1 instanceof AMonstre)).count();
    }
}
