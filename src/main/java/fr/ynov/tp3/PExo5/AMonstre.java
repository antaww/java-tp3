package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;

import java.io.Serial;

public abstract class AMonstre implements ICarteYuGiOh {
    @Serial
    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        stream.defaultWriteObject();
    }

    protected String name;
    protected int level;
    protected Attribute attribute;
    protected String types;
    protected String reference;
    protected int[] stats = new int[0];

    protected String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttribute() {
        return attribute.getDisplayName();
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStats() {
        return (stats[0] != -1 ? "ATK/" + stats[0] : "") + (stats[1] != -1 ? " DEF/" + stats[1] : "");
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}

