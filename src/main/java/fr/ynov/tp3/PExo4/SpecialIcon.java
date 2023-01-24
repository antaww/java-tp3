package fr.ynov.tp3.PExo4;

public enum SpecialIcon {
    Normal("Normal"), Continuous("Continu"), Counter("Contre"), Equip("Équipement"), Quick_Play("Jeu-Rapide"), Ritual("Rituel"), Field("Terrain"),
    ;

    final String displayName;

    SpecialIcon(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
