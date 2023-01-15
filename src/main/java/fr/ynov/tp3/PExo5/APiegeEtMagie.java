package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serial;

public abstract class APiegeEtMagie implements ICarteYuGiOh {
    @Serial
    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        stream.defaultWriteObject();
    }

    protected String name;
    protected SpecialType type;
    protected SpecialIcon specialIcon;
    protected String reference;

    protected String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type.getDisplayName();
    }

    public void setType(SpecialType type) {
        this.type = type;
    }

    public String getSpecialIcon() {
        return specialIcon.getDisplayName();
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
