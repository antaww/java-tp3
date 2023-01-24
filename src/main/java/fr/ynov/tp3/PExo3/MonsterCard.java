package fr.ynov.tp3.PExo3;

import fr.ynov.tp3.PExo5.AMonstre;

import java.util.Arrays;

public class MonsterCard extends AMonstre {
    public MonsterCard(final String name, final int level, final Attribute attribute, final String types, final String reference, final int atk, final int def, final String description) {
        this.name = name;
        this.level = level;
        this.attribute = attribute;
        this.types = types;
        this.reference = reference;
        this.stats = new int[]{atk, def};
        this.description = description;
    }

    @Override
    public String toString() {
        return "MonsterCard{" + "name='" + name + '\'' + ", level=" + level + ", attribute=" + attribute + ", types='" + types + '\'' + ", reference='" + reference + '\'' + ", stats=" + Arrays.toString(stats) + ", description='" + description + '\'' + '}';
    }
}