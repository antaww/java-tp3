package fr.ynov.tp3.PExo3;

public enum PrimaryType {
    //todo: on setMonsterCard, replace spaces & - with underscores
    Aqua("Aqua"),
    Beast("Bête"),
    Beast_Warrior("Bête-guerrier"),
    Creator_God("Créateur"),
    Cyberse("Cyberse"),
    Dinosaur("Dinosaure"),
    Divine_Beast("Bête-divine"),
    Dragon("Dragon"),
    Fairy("Elfe"),
    Fiend("Démon"),
    Fish("Poisson"),
    Insect("Insecte"),
    Machine("Machine"),
    Plant("Plante"),
    Psychic("Psychique"),
    Pyro("Pyro"),
    Reptile("Reptile"),
    Rock("Rocher"),
    Sea_Serpent("Serpent De Mer"),
    Spellcaster("Magicien"),
    Thunder("Tonnerre"),
    Warrior("Guerrier"),
    Winged_Beast("Bête-ailée"),
    Wyrm("Wyrm"),
    Zombie("Zombie");

    final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    PrimaryType(String displayName){
        this.displayName = displayName;
    }
}