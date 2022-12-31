package Exo3;

import java.lang.reflect.Array;

public class MonsterCard {
    private String name;
    private int level; // 1-12
    private String attribute; // Eau, Feu, Terre, Vent, Lumière, Ténèbres, Divin
    private String primaryType; // Magicien, Bête, Dinosaure, Pyro, Psychique, Dragon, Bête-ailée, Reptile, Tonnerre, Bête-divine, Zombie,
    // Démon, Poisson, Rocher, Wyrm, Guerrier, Elfe, Serpent De Mer, Plante, Cyberse, Bête-guerrier, Insecte, Aqua, Machine, Créateur
    private String secondaryType; // Fusion, Synchro, Xyz, Rituel, Pendule, Lien
    private String tertiaryType; // Flip, Toon, Spirit, Union, Gémeau, Synthoniseur
    private String monsterType; // Normal, Effet
    // Types primaire et monstre obligatoires, types secondaire et type tertiaire facultatifs
    // Peut avoir 2 à 4 types (primaire, ?secondaire, ?tertiaire, monstre)
    private String number; // XXXX-XXXXX
    private Array[] stats; // ATK/[0] DEF/[1]
    private String description;
}
