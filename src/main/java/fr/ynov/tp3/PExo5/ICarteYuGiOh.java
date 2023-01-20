package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serializable;

public interface ICarteYuGiOh extends Serializable {
    String getName();
    String getReference();
    String getDescription();
    int getLevel();
    String getAttribute();
    String getTypes();
    String getType();
    String getStats();
    String getSpecialIcon();

    void setName(String name);
    void setReference(String reference);
    void setDescription(String description);
    void setLevel(int level);
    void setAttribute(Attribute attribute);
    void setTypes(String types);
    void setType(SpecialType type);
    void setStats(int[] stats);
    void setSpecialIcon(SpecialIcon specialIcon);
}

