package fr.ynov.tp3.PExo4;

/**
 * Enum SpecialType : création d'un enum pour stocker les différents types des cartes Magie et Piège de Yu-Gi-Oh!
 * Cet enum permet de stocker les types sous forme de constantes, chacune associée à un nom d'affichage.
 * Il utilise également une méthode getDisplayName() pour récupérer le nom d'affichage associé à un type.
 */
public enum SpecialType {
    Spell_Card("Carte Magie"), Trap_Card("Carte Piège");

    final String displayName;

    SpecialType(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * Méthode getDisplayName : retourne le nom d'affichage du type spéciale.
     * Cette méthode permet de retourner le nom d'affichage du type spéciale.
     *
     * @return nom d'affichage du type spéciale
     */
    public String getDisplayName() {
        return displayName;
    }
}
