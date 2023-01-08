package fr.ynov.tp3.PExo3;

import fr.ynov.tp3.Card;

public class MonsterCard implements Card {
    private String name;
    private int level; // 1-12
    private Attribute attribute; // Eau, Feu, Terre, Vent, Lumière, Ténèbres, Divin
    private PrimaryType primaryType; // Magicien, Bête, Dinosaure, Pyro, Psychique, Dragon, Bête-ailée, Reptile, Tonnerre, Bête-divine, Zombie,
    // Démon, Poisson, Rocher, Wyrm, Guerrier, Elfe, Serpent De Mer, Plante, Cyberse, Bête-guerrier, Insecte, Aqua, Machine, Créateur
    private SecondaryType secondaryType = null; // Fusion, Synchro, Xyz, Rituel, Pendule, Lien
    private TertiaryType tertiaryType = null; // Flip, Toon, Spirit, Union, Gémeau, Synthoniseur
    private MonsterType monsterType; // Normal, Effet
    // Types primaire et monstre obligatoires, types secondaire et type tertiaire facultatifs
    // Peut avoir 2 à 4 types (primaire, ?secondaire, ?tertiaire, monstre)
    private String reference; // XXXX-XXXXX
    private String[] stats = new String[0]; // [0] = ATK/{int} [1] = DEF/{int}
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttribute() {
        return attribute.getDisplayName();
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getAllTypes() {
        return "[" + primaryType.getDisplayName() + (secondaryType == null ? "" : " / " + secondaryType.getDisplayName()) + (tertiaryType == null ? "" : " / " + tertiaryType.getDisplayName()) + " / " + monsterType.getDisplayName() + "]";
    }

    public void setPrimaryType(PrimaryType primaryType) {
        this.primaryType = primaryType;
    }

    public void setSecondaryType(SecondaryType secondaryType) {
        this.secondaryType = secondaryType;
    }

    public void setTertiaryType(TertiaryType tertiaryType) {
        this.tertiaryType = tertiaryType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStats() {
        return "ATK/" + stats[0] + " DEF/" + stats[1];
    }

    public void setStats(String[] stats) {
        this.stats = stats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


