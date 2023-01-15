package fr.ynov.tp3.PExo5;

import java.io.Serializable;

public interface ICarteYuGiOh extends Serializable {
    String getName();
    String getReference();
    String getDescription();
    void setName(String name);
    void setReference(String reference);
    void setDescription(String description);
}

