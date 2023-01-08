package fr.ynov.tp3.PExo3;

public enum TertiaryType {
    Flip("Flip"),
    Gemini("Gémeau"),
    Spirit("Spirit"),
    Toon("Toon"),
    Tuner("Synthoniseur"),
    Union("Union");

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    TertiaryType(String displayName){
        this.displayName = displayName;
    }
}
