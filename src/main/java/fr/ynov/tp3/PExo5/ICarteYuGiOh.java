package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serializable;

/**
 * Interface ICarteYuGiOh : création d'une interface pour définir les méthodes communes aux cartes Monstre, Magie et Piège de Yu-Gi-Oh!
 * Cette interface permet de définir les méthodes communes aux cartes Monstre, Magie et Piège de Yu-Gi-Oh!.
 * Elle utilise les classes Attribute, SpecialIcon et SpecialType pour définir les attributs des cartes.
 *
 * @see fr.ynov.tp3.PExo3.Attribute
 * @see fr.ynov.tp3.PExo4.SpecialIcon
 * @see fr.ynov.tp3.PExo4.SpecialType
 */
public interface ICarteYuGiOh extends Serializable {
    /**
     * Méthode getName : retourne le nom de la carte.
     * Cette méthode permet de retourner le nom de la carte.
     *
     * @return nom de la carte
     */
    String getName();

    /**
     * Méthode setName : définit le nom de la carte.
     * Cette méthode permet de définir le nom de la carte.
     *
     * @param name nom de la carte
     */
    void setName(String name);

    /**
     * Méthode getReference : retourne la référence de la carte.
     * Cette méthode permet de retourner la référence de la carte.
     *
     * @return référence de la carte
     */
    String getReference();

    /**
     * Méthode setReference : définit la référence de la carte.
     * Cette méthode permet de définir la référence de la carte.
     *
     * @param reference référence de la carte
     */
    void setReference(String reference);

    /**
     * Méthode getDescription : retourne la description de la carte.
     * Cette méthode permet de retourner la description de la carte.
     *
     * @return description de la carte
     */
    String getDescription();

    /**
     * Méthode setDescription : définit la description de la carte.
     * Cette méthode permet de définir la description de la carte.
     *
     * @param description description de la carte
     */
    void setDescription(String description);

    /**
     * Méthode getLevel : retourne le niveau de la carte.
     * Cette méthode permet de retourner le niveau de la carte.
     *
     * @return niveau de la carte
     */
    int getLevel();

    /**
     * Méthode setLevel : définit le niveau de la carte.
     * Cette méthode permet de définir le niveau de la carte.
     *
     * @param level niveau de la carte
     */
    void setLevel(int level);

    /**
     * Méthode getAttribute : retourne l'attribut de la carte.
     * Cette méthode permet de retourner l'attribut de la carte.
     *
     * @return attribut de la carte
     */
    String getAttribute();

    /**
     * Méthode setAttribute : définit l'attribut de la carte.
     * Cette méthode permet de définir l'attribut de la carte.
     *
     * @param attribute attribut de la carte
     */
    void setAttribute(Attribute attribute);

    /**
     * Méthode getTypes : retourne les types de la carte.
     * Cette méthode permet de retourner les types de la carte.
     *
     * @return types de la carte
     */
    String getTypes();

    /**
     * Méthode setTypes : définit les types de la carte.
     * Cette méthode permet de définir les types de la carte.
     *
     * @param types types de la carte
     */
    void setTypes(String types);

    /**
     * Méthode getType : retourne le type de la carte.
     * Cette méthode permet de retourner le type de la carte.
     *
     * @return type de la carte
     */
    String getType();

    /**
     * Méthode setType : définit le type de la carte.
     * Cette méthode permet de définir le type de la carte.
     *
     * @param type type de la carte
     */
    void setType(SpecialType type);

    /**
     * Méthode getStats : retourne les statistiques de la carte.
     * Cette méthode permet de retourner les statistiques de la carte.
     *
     * @return statistiques de la carte
     */
    String getStats();

    /**
     * Méthode setStats : définit les statistiques de la carte.
     * Cette méthode permet de définir les statistiques de la carte.
     *
     * @param stats statistiques de la carte
     */
    void setStats(int[] stats);

    /**
     * Méthode getSpecialIcon : retourne l'icône spéciale de la carte.
     * Cette méthode permet de retourner l'icône spéciale de la carte.
     *
     * @return icône spéciale de la carte
     */
    String getSpecialIcon();

    /**
     * Méthode setSpecialIcon : définit l'icône spéciale de la carte.
     * Cette méthode permet de définir l'icône spéciale de la carte.
     *
     * @param specialIcon icône spéciale de la carte
     */
    void setSpecialIcon(SpecialIcon specialIcon);
}

