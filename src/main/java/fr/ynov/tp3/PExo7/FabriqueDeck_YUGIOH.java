package fr.ynov.tp3.PExo7;

import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PExo4.SpecialCards;
import fr.ynov.tp3.PExo5.AMonstre;
import fr.ynov.tp3.PExo5.ICarteYuGiOh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FabriqueDeck_YUGIOH {
    private final ArrayList<MonsterCard> monsterCards = new ArrayList<>();
    private final ArrayList<String> monsterCardsImages = new ArrayList<>();
    private final ArrayList<SpecialCards> spellCards = new ArrayList<>();
    private final ArrayList<String> spellCardsImages = new ArrayList<>();
    private final ArrayList<SpecialCards> trapCards = new ArrayList<>();
    private final ArrayList<String> trapCardsImages = new ArrayList<>();
    private final Map<ICarteYuGiOh, String> deckList = new HashMap<>();
    private final int randomSize;

    public FabriqueDeck_YUGIOH() {
        int randomNum;
        do {
            final var random = new Random();
            randomNum = random.nextInt(21) + 40;
        } while (randomNum % 2 == 0);
        randomSize = randomNum;
    }

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

    public void addMonsterCard(final MonsterCard card, final String cardImage) {
        monsterCards.add(card);
        monsterCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    public void addSpellCard(final SpecialCards card, final String cardImage) {
        spellCards.add(card);
        spellCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    public void addTrapCard(final SpecialCards card, final String cardImage) {
        trapCards.add(card);
        trapCardsImages.add(cardImage);
        addCardToDeck(card, cardImage);
    }

    public Map<ICarteYuGiOh, String> getDeckList() {
        return deckList;
    }

    public int getRandomSize() {
        return randomSize;
    }

    public int getMonstersCount() {
        return (int) deckList.keySet().stream().filter(card1 -> card1 instanceof AMonstre).count();
    }

    public int getSpecialsCount() {
        return (int) deckList.keySet().stream().filter(card1 -> !(card1 instanceof AMonstre)).count();
    }
}
