package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serializable;

public interface ICarteYuGiOh extends Serializable {
    String getName();

    void setName(String name);

    String getReference();

    void setReference(String reference);

    String getDescription();

    void setDescription(String description);

    int getLevel();

    void setLevel(int level);

    String getAttribute();

    void setAttribute(Attribute attribute);

    String getTypes();

    void setTypes(String types);

    String getType();

    void setType(SpecialType type);

    String getStats();

    void setStats(int[] stats);

    String getSpecialIcon();

    void setSpecialIcon(SpecialIcon specialIcon);
}

