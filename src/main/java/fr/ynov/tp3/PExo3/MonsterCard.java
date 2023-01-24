package fr.ynov.tp3.PExo3;

import fr.ynov.tp3.PExo5.AMonstre;

import java.util.Arrays;

/**
 * Classe MonsterCard : représentation d'une carte monstre Yu-Gi-Oh!.
 * Cette classe hérite de la classe AMonstre et permet de créer une carte monstre avec les informations suivantes : nom, niveau, attribut, types, référence, stats (ATK/DEF) et description.
 * Elle surcharge également la méthode toString() pour afficher les informations de la carte monstre.
 *
 * @see fr.ynov.tp3.PExo5.AMonstre
 * @see java.util.Arrays
 */
public class MonsterCard extends AMonstre {
    /**
     * Constructeur de la classe MonsterCard.
     * Il prend en paramètre les informations suivantes : nom, niveau, attribut, types, référence, stats (ATK/DEF) et description.
     * Il assigne ces valeurs aux attributs correspondants de l'objet de type MonsterCard.
     *
     * @param name        Nom de la carte monstre
     * @param level       Niveau de la carte monstre
     * @param attribute   Attribut de la carte monstre (ténèbres, divine, terre, feu, lumière, eau ou vent)
     * @param types       Types de la carte monstre
     * @param reference   Référence de la carte monstre
     * @param atk         Attaque de la carte monstre
     * @param def         Défense de la carte monstre
     * @param description Description de la carte monstre
     * @see fr.ynov.tp3.PExo3.Attribute
     */
    public MonsterCard(final String name, final int level, final Attribute attribute, final String types, final String reference, final int atk, final int def, final String description) {
        this.name = name;
        this.level = level;
        this.attribute = attribute;
        this.types = types;
        this.reference = reference;
        this.stats = new int[]{atk, def};
        this.description = description;
    }

    /**
     * Méthode toString : affichage des informations de la carte monstre.
     * Cette méthode surcharge la méthode toString() de la classe Object et permet d'afficher les informations de la carte monstre.
     *
     * @return informations de la carte monstre
     * @see java.util.Arrays
     */
    @Override
    public String toString() {
        return "MonsterCard{" + "name='" + name + '\'' + ", level=" + level + ", attribute=" + attribute + ", types='" + types + '\'' + ", reference='" + reference + '\'' + ", stats=" + Arrays.toString(stats) + ", description='" + description + '\'' + '}';
    }
}