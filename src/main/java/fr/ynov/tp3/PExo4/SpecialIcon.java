package fr.ynov.tp3.PExo4;

/**
 * Enum SpecialIcon : création d'un enum pour stocker les différentes icônes des cartes Magie et Piège de Yu-Gi-Oh!
 * Cet enum permet de stocker les icônes sous forme de constantes, chacune associée à un nom d'affichage.
 * Il utilise également une méthode getDisplayName() pour récupérer le nom d'affichage associé à une icône.
 */
public enum SpecialIcon {
    Normal("Normal"), Continuous("Continu"), Counter("Contre"), Equip("Équipement"), Quick_Play("Jeu-Rapide"), Ritual("Rituel"), Field("Terrain");

    final String displayName;

    SpecialIcon(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * Méthode getDisplayName : retourne le nom d'affichage de l'icône spéciale.
     * Cette méthode permet de retourner le nom d'affichage de l'icône spéciale.
     *
     * @return nom d'affichage de l'icône spéciale
     */
    public String getDisplayName() {
        return displayName;
    }
}
