package fr.ynov.tp3.PExo4;

import fr.ynov.tp3.PExo5.APiegeEtMagie;

public class SpecialCards extends APiegeEtMagie {
    public SpecialCards(String name, SpecialType type, SpecialIcon specialIcon, String reference, String description) {
        this.name = name;
        this.type = type;
        this.specialIcon = specialIcon;
        this.reference = reference;
        this.description = description;
    }
}
