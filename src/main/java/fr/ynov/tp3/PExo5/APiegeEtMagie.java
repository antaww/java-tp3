package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serial;

public abstract class APiegeEtMagie implements ICarteYuGiOh {
    protected String name;
    protected SpecialType type;
    protected SpecialIcon specialIcon;
    protected String reference;
    protected String description;

    @Serial
    private void readObject(final java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    @Serial
    private void writeObject(final java.io.ObjectOutputStream stream) throws java.io.IOException {
        stream.defaultWriteObject();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type.getDisplayName();
    }

    @Override
    public void setType(final SpecialType type) {
        this.type = type;
    }

    @Override
    public String getSpecialIcon() {
        return specialIcon.getDisplayName();
    }

    @Override
    public void setSpecialIcon(final SpecialIcon specialIcon) {
        this.specialIcon = specialIcon;
    }

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public void setReference(final String reference) {
        this.reference = reference;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getStats() {
        return "";
    }

    @Override
    public void setStats(final int[] stats) {
        //Aucune stats pour les cartes pieges et magies
    }

    @Override
    public String getTypes() {
        return "";
    }

    @Override
    public void setTypes(final String types) {
        //Aucun type pour les cartes pieges et magies
    }

    @Override
    public String getAttribute() {
        return "";
    }

    @Override
    public void setAttribute(final Attribute attribute) {
        //Aucun attribut pour les cartes pieges et magies
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(final int level) {
        //Aucun level pour les cartes pieges et magies
    }

}
