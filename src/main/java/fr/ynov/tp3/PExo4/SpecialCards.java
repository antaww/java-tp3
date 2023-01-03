package fr.ynov.tp3.PExo4;

import fr.ynov.tp3.Card;

public class SpecialCards implements Card {
    private String name;
    private SpecialType type; // Magie, Piège
    private SpecialIcon specialIcon; // Continu, Contre, Équipement, Jeu_Rapide, Rituel, Terrain
    private String reference; // XXXX-XXXXX
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return "[" + type + "]";
    }

    public void setType(SpecialType type) {
        this.type = type;
    }

    public String getSpecialIcon() {
        return specialIcon.toString();
    }

    public void setSpecialIcon(SpecialIcon specialIcon) {
        this.specialIcon = specialIcon;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
