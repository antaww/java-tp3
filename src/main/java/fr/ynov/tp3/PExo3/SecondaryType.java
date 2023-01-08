package fr.ynov.tp3.PExo3;

public enum SecondaryType {
    Fusion("Fusion"),
    Link("Lien"),
    Pendulum("Pendule"),
    Ritual("Rituel"),
    Synchro("Synchro"),
    XYZ("Xyz");

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    SecondaryType(String displayName){
        this.displayName = displayName;
    }
}
