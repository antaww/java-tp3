package fr.ynov.tp3.PExo4;

public enum SpecialType {
    Spell_Card("Carte Magie"),
    Trap_Card("Carte Piège");

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    SpecialType(String displayName){
        this.displayName = displayName;
    }
}
