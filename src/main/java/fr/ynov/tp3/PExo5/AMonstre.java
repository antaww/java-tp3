package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serial;

/**
 * Classe AMonstre : classe abstraite pour la définition d'une carte Monstre de Yu-Gi-Oh!
 * Cette classe implémente l'interface ICarteYuGiOh et définit les attributs communs à toutes les cartes Monstres, tels que le nom, le niveau, l'attribut, les types, la référence, les points d'attaque et de défense, et la description.
 * Elle utilise également l'enum Attribute pour stocker les différents attributs des cartes.
 * Elle permet également de sérialiser et désérialiser les objets de type AMonstre pour les sauvegarder et les charger à partir d'un fichier.
 *
 * @see fr.ynov.tp3.PExo3.Attribute
 * @see fr.ynov.tp3.PExo4.SpecialIcon
 * @see fr.ynov.tp3.PExo4.SpecialType
 * @see fr.ynov.tp3.PExo5.ICarteYuGiOh
 */
public abstract class AMonstre implements ICarteYuGiOh {
    protected String name;
    protected int level;
    protected Attribute attribute;
    protected String types;
    protected String reference;
    protected int[] stats = new int[0];
    protected String description;

    /**
     * Méthode readObject : méthode pour la désérialisation d'un objet de type AMonstre.
     * Cette méthode est utilisée lorsque l'on charge un objet de type AMonstre à partir d'un fichier.
     * Elle prend en paramètre un objet de type java.io.ObjectInputStream qui représente le flux de lecture du fichier.
     * Elle utilise la méthode defaultReadObject pour récupérer les valeurs des attributs de l'objet désérialisé.
     *
     * @param stream objet java.io.ObjectInputStream représentant le flux de lecture du fichier
     * @throws java.io.IOException    exception pour les entrées/sorties
     * @throws ClassNotFoundException exception pour les classes non trouvées
     */
    @Serial
    private void readObject(final java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    /**
     * Méthode writeObject : méthode pour la sérialisation d'un objet de type AMonstre.
     * Cette méthode est utilisée lorsque l'on sauvegarde un objet de type AMonstre dans un fichier.
     * Elle prend en paramètre un objet de type java.io.ObjectOutputStream qui représente le flux d'écriture du fichier.
     * Elle utilise la méthode defaultWriteObject pour écrire les valeurs des attributs de l'objet sérialisé.
     *
     * @param stream objet java.io.ObjectOutputStream représentant le flux d'écriture du fichier
     * @throws java.io.IOException exception pour les entrées/sorties
     */
    @Serial
    private void writeObject(final java.io.ObjectOutputStream stream) throws java.io.IOException {
        stream.defaultWriteObject();
    }

    /**
     * Méthode getName : méthode pour récupérer le nom de la carte Monstre.
     *
     * @return nom de la carte Monstre
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Méthode setName : méthode pour définir le nom de la carte Monstre.
     *
     * @param name nom de la carte Monstre
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Méthode getLevel : méthode pour récupérer le niveau de la carte Monstre.
     *
     * @return niveau de la carte Monstre
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Méthode setLevel : méthode pour définir le niveau de la carte Monstre.
     *
     * @param level niveau de la carte Monstre
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Méthode getAttribute : méthode pour récupérer l'attribut de la carte Monstre.
     *
     * @return attribut de la carte Monstre
     */
    @Override
    public String getAttribute() {
        return attribute.getDisplayName();
    }

    /**
     * Méthode setAttribute : méthode pour définir l'attribut de la carte Monstre.
     *
     * @param attribute attribut de la carte Monstre
     */
    @Override
    public void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * Méthode getReference : méthode pour récupérer la référence de la carte Monstre.
     *
     * @return référence de la carte Monstre
     */
    @Override
    public String getReference() {
        return reference;
    }

    /**
     * Méthode setReference : méthode pour définir la référence de la carte Monstre.
     *
     * @param reference référence de la carte Monstre
     */
    @Override
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Méthode getStats : méthode pour récupérer les statistiques de la carte Monstre.
     *
     * @return statistiques de la carte Monstre
     */
    @Override
    public String getStats() {
        return (stats[0] != -1 ? "ATK/" + stats[0] : "") + (stats[1] != -1 ? " DEF/" + stats[1] : "");
    }

    /**
     * Méthode setStats : méthode pour définir les statistiques de la carte Monstre.
     *
     * @param stats statistiques de la carte Monstre
     */
    @Override
    public void setStats(final int[] stats) {
        this.stats = stats;
    }

    /**
     * Méthode getDescription : méthode pour récupérer la description de la carte Monstre.
     *
     * @return description de la carte Monstre
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Méthode setDescription : méthode pour définir la description de la carte Monstre.
     *
     * @param description description de la carte Monstre
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Méthode getTypes : méthode pour récupérer les types de la carte Monstre.
     *
     * @return types de la carte Monstre
     */
    @Override
    public String getTypes() {
        return types;
    }

    /**
     * Méthode setTypes : méthode pour définir les types de la carte Monstre.
     *
     * @param types types de la carte Monstre
     */
    @Override
    public void setTypes(final String types) {
        this.types = types;
    }

    /**
     * Méthode getSpecialIcon : méthode pour récupérer l'icône spéciale de la carte Monstre.
     *
     * @return icône spéciale de la carte Monstre
     */
    @Override
    public String getSpecialIcon() {
        return null;
    }

    /**
     * Méthode setSpecialIcon : méthode pour définir l'icône spéciale de la carte Monstre.
     *
     * @param specialIcon icône spéciale de la carte Monstre
     */
    @Override
    public void setSpecialIcon(final SpecialIcon specialIcon) {
        //Aucune implémentation nécessaire pour MonsterCard
    }

    /**
     * Méthode getType : méthode pour récupérer le type de la carte Monstre.
     *
     * @return type de la carte Monstre
     */
    @Override
    public String getType() {
        return null;
    }

    /**
     * Méthode setType : méthode pour définir le type de la carte Monstre.
     *
     * @param type type de la carte Monstre
     */
    @Override
    public void setType(final SpecialType type) {
        //Aucune implémentation nécessaire pour MonsterCard
    }
}