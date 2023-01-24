package fr.ynov.tp3.PExo4;

public enum SpecialType {
    Spell_Card("Carte Magie"), Trap_Card("Carte Piège");

    final String displayName;

    SpecialType(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
