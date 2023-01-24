package fr.ynov.tp3.PExo3;

/**
 * Enum Attribute : énumération des différents attributs d'un objet.
 * Cette énumération permet de définir les différents attributs d'un objet (exemple : DARK, DIVINE, EARTH, FIRE, LIGHT, WATER, WIND).
 * Chacun de ces attributs possède un nom d'affichage (displayName) associé (exemple : Ténèbres, Divine, Terre, Feu, Lumiere, Eau, Vent).
 */
public enum Attribute {
    DARK("Ténèbres"), DIVINE("Divine"), EARTH("Terre"), FIRE("Feu"), LIGHT("Lumiere"), WATER("Eau"), WIND("Vent");

    final String displayName;

    Attribute(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * Méthode getDisplayName : retourne le nom d'affichage de l'attribut.
     * Cette méthode permet de retourner le nom d'affichage de l'attribut.
     *
     * @return nom d'affichage de l'attribut
     */
    public String getDisplayName() {
        return displayName;
    }
}
