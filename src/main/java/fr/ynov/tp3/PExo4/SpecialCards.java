package fr.ynov.tp3.PExo4;

import fr.ynov.tp3.PExo5.APiegeEtMagie;

/**
 * Classe SpecialCards : représente les cartes magies et pièges de Yu-Gi-Oh!.
 * Cette classe hérite de la classe APiegeEtMagie et permet de créer des objets de type carte magie ou piège, en définissant leur nom, leur type, leur icône, leur référence et leur description.
 * Elle utilise les enums SpecialType et SpecialIcon pour stocker les différents types et icônes des cartes magies et pièges.
 *
 * @see fr.ynov.tp3.PExo5.APiegeEtMagie
 * @see fr.ynov.tp3.PExo4.SpecialType
 * @see fr.ynov.tp3.PExo4.SpecialIcon
 */
public class SpecialCards extends APiegeEtMagie {
    public SpecialCards(final String name, final SpecialType type, final SpecialIcon specialIcon, final String reference, final String description) {
        this.name = name;
        this.type = type;
        this.specialIcon = specialIcon;
        this.reference = reference;
        this.description = description;
    }
}
