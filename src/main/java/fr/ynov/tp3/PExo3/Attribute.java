package fr.ynov.tp3.PExo3;

public enum Attribute {
    Divin(""),
//    Eau,
//    Feu,
//    Lumiere,
//    Tenebres,
//    Terre,
    WIND("Vent");

    public String getDisplayName() {
        return displayName;
    }

    final String displayName;

    Attribute(String displayName){
        this.displayName = displayName;
    }
}
