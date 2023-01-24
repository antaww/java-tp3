package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serial;

public abstract class AMonstre implements ICarteYuGiOh {
    protected String name;
    protected int level;
    protected Attribute attribute;
    protected String types;
    protected String reference;
    protected int[] stats = new int[0];
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
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    @Override
    public String getAttribute() {
        return attribute.getDisplayName();
    }

    @Override
    public void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
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
    public String getStats() {
        return (stats[0] != -1 ? "ATK/" + stats[0] : "") + (stats[1] != -1 ? " DEF/" + stats[1] : "");
    }

    @Override
    public void setStats(final int[] stats) {
        this.stats = stats;
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
    public String getTypes() {
        return types;
    }

    @Override
    public void setTypes(final String types) {
        this.types = types;
    }

    @Override
    public String getSpecialIcon() {
        return null;
    }

    @Override
    public void setSpecialIcon(final SpecialIcon specialIcon) {
        //Aucune implémentation nécessaire pour MonsterCard
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void setType(final SpecialType type) {
        //Aucune implémentation nécessaire pour MonsterCard
    }
}