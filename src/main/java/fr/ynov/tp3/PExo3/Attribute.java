package fr.ynov.tp3.PExo3;

public enum Attribute {
    DARK("Ténèbres"),
    DIVINE("Divine"),
    EARTH("Terre"),
    FIRE("Feu"),
    LIGHT("Lumiere"),
    WATER("Eau"),
    WIND("Vent");

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    Attribute(String displayName){
        this.displayName = displayName;
    }
}