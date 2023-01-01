package Exo3;

public class MonsterCard {
    private String name;
    private int level; // 1-12
    private String attribute; // Eau, Feu, Terre, Vent, Lumière, Ténèbres, Divin
    private String primaryType; // Magicien, Bête, Dinosaure, Pyro, Psychique, Dragon, Bête-ailée, Reptile, Tonnerre, Bête-divine, Zombie,
    // Démon, Poisson, Rocher, Wyrm, Guerrier, Elfe, Serpent De Mer, Plante, Cyberse, Bête-guerrier, Insecte, Aqua, Machine, Créateur
    private String secondaryType = ""; // Fusion, Synchro, Xyz, Rituel, Pendule, Lien
    private String tertiaryType = ""; // Flip, Toon, Spirit, Union, Gémeau, Synthoniseur
    private String monsterType; // Normal, Effet
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
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAllTypes() {
        return "[" + primaryType + (secondaryType.equals("") ? "" : " / " + secondaryType) + (tertiaryType.equals("") ? "" : " / " + tertiaryType) + " / " + monsterType + "]";
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType = secondaryType;
    }

    public void setTertiaryType(String tertiaryType) {
        this.tertiaryType = tertiaryType;
    }

    public void setMonsterType(String monsterType) {
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


