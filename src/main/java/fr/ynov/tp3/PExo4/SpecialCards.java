package fr.ynov.tp3.PExo4;

import fr.ynov.tp3.PExo5.APiegeEtMagie;

public class SpecialCards extends APiegeEtMagie {
    public SpecialCards(final String name, final SpecialType type, final SpecialIcon specialIcon, final String reference, final String description) {
        this.name = name;
        this.type = type;
        this.specialIcon = specialIcon;
        this.reference = reference;
        this.description = description;
    }
}
