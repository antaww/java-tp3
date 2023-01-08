package fr.ynov.tp3.PExo3;

public enum MonsterType {
    Effect("Effet"),
    Normal(""); // Do not display "Normal" in the card

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    MonsterType(String displayName){
        this.displayName = displayName;
    }
}
