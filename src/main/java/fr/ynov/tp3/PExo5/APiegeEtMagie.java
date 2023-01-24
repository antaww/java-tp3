package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo4.SpecialIcon;
import fr.ynov.tp3.PExo4.SpecialType;

import java.io.Serial;

/**
 * Classe APiegeEtMagie : classe abstraite pour les cartes Magie et Piège de Yu-Gi-Oh!
 * Cette classe abstraite permet de définir les attributs et méthodes communes aux cartes Magie et Piège de Yu-Gi-Oh!
 * Elle implémente l'interface ICarteYuGiOh et utilise les enums SpecialType et SpecialIcon pour stocker les différents types et icônes des cartes magies et pièges.
 * Elle utilise également les attributs name, reference et description pour stocker le nom, la référence et la description des cartes magies et pièges.
 * Elle permet également de sérialiser et désérialiser les objets de type APiegeEtMagie pour les sauvegarder et les charger à partir d'un fichier.
 *
 * @see fr.ynov.tp3.PExo4.SpecialIcon
 * @see fr.ynov.tp3.PExo4.SpecialType
 * @see fr.ynov.tp3.PExo5.ICarteYuGiOh
 */
public abstract class APiegeEtMagie implements ICarteYuGiOh {
    protected String name;
    protected SpecialType type;
    protected SpecialIcon specialIcon;
    protected String reference;
    protected String description;

    /**
     * Méthode readObject : méthode pour la désérialisation d'un objet de type AMonstre.
     * Cette méthode est utilisée lorsque l'on charge un objet de type AMonstre à partir d'un fichier.
     * Elle prend en paramètre un objet de type java.io.ObjectInputStream qui représente le flux de lecture du fichier.
     * Elle utilise la méthode defaultReadObject pour récupérer les valeurs des attributs de l'objet désérialisé.
     *
     * @param stream objet java.io.ObjectInputStream représentant le flux de lecture du fichier
     * @throws java.io.IOException    exception levée en cas d'erreur d'entrée/sortie
     * @throws ClassNotFoundException exception levée en cas de classe non trouvée
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
     * @throws java.io.IOException exception levée en cas d'erreur d'entrée/sortie
     */
    @Serial
    private void writeObject(final java.io.ObjectOutputStream stream) throws java.io.IOException {
        stream.defaultWriteObject();
    }

    /**
     * Méthode getName : méthode pour récupérer le nom de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut name.
     *
     * @return valeur de l'attribut name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Méthode setName : méthode pour définir le nom de la carte magie ou piège.
     * Cette méthode prend en paramètre une chaîne de caractères représentant le nom de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut name.
     *
     * @param name chaîne de caractères représentant le nom de la carte magie ou piège
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Méthode getType : méthode pour récupérer le type de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut type.
     *
     * @return valeur de l'attribut type
     */
    @Override
    public String getType() {
        return type.getDisplayName();
    }

    /**
     * Méthode setType : méthode pour définir le type de la carte magie ou piège.
     * Cette méthode prend en paramètre une valeur de l'enum SpecialType représentant le type de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut type.
     *
     * @param type valeur de l'enum SpecialType représentant le type de la carte magie ou piège
     */
    @Override
    public void setType(final SpecialType type) {
        this.type = type;
    }

    /**
     * Méthode getSpecialIcon : méthode pour récupérer l'icône de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut specialIcon.
     *
     * @return valeur de l'attribut specialIcon
     */
    @Override
    public String getSpecialIcon() {
        return specialIcon.getDisplayName();
    }

    /**
     * Méthode setSpecialIcon : méthode pour définir l'icône de la carte magie ou piège.
     * Cette méthode prend en paramètre une valeur de l'enum SpecialIcon représentant l'icône de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut specialIcon.
     *
     * @param specialIcon valeur de l'enum SpecialIcon représentant l'icône de la carte magie ou piège
     */
    @Override
    public void setSpecialIcon(final SpecialIcon specialIcon) {
        this.specialIcon = specialIcon;
    }

    /**
     * Méthode getReference : méthode pour récupérer la référence de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut reference.
     *
     * @return valeur de l'attribut reference
     */
    @Override
    public String getReference() {
        return reference;
    }

    /**
     * Méthode setReference : méthode pour définir la référence de la carte magie ou piège.
     * Cette méthode prend en paramètre une chaîne de caractères représentant la référence de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut reference.
     *
     * @param reference chaîne de caractères représentant la référence de la carte magie ou piège
     */
    @Override
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Méthode getDescription : méthode pour récupérer la description de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut description.
     *
     * @return valeur de l'attribut description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Méthode setDescription : méthode pour définir la description de la carte magie ou piège.
     * Cette méthode prend en paramètre une chaîne de caractères représentant la description de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut description.
     *
     * @param description chaîne de caractères représentant la description de la carte magie ou piège
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Méthode getStats : méthode pour récupérer les statistiques de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut stats.
     *
     * @return valeur de l'attribut stats
     */
    @Override
    public String getStats() {
        return "";
    }

    /**
     * Méthode setStats : méthode pour définir les statistiques de la carte magie ou piège.
     * Cette méthode prend en paramètre un tableau d'entiers représentant les statistiques de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut stats.
     *
     * @param stats tableau d'entiers représentant les statistiques de la carte magie ou piège
     */
    @Override
    public void setStats(final int[] stats) {
        //Aucune stats pour les cartes pieges et magies
    }

    /**
     * Méthode getTypes : méthode pour récupérer les types de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut types.
     *
     * @return valeur de l'attribut types
     */
    @Override
    public String getTypes() {
        return "";
    }

    /**
     * Méthode setTypes : méthode pour définir les types de la carte magie ou piège.
     * Cette méthode prend en paramètre une chaîne de caractères représentant les types de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut types.
     *
     * @param types chaîne de caractères représentant les types de la carte magie ou piège
     */
    @Override
    public void setTypes(final String types) {
        //Aucun type pour les cartes pieges et magies
    }

    /**
     * Méthode getAttribute : méthode pour récupérer l'attribut de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut attribute.
     *
     * @return valeur de l'attribut attribute
     */
    @Override
    public String getAttribute() {
        return "";
    }

    /**
     * Méthode setAttribute : méthode pour définir l'attribut de la carte magie ou piège.
     * Cette méthode prend en paramètre une chaîne de caractères représentant l'attribut de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut attribute.
     *
     * @param attribute chaîne de caractères représentant l'attribut de la carte magie ou piège
     */
    @Override
    public void setAttribute(final Attribute attribute) {
        //Aucun attribut pour les cartes pieges et magies
    }

    /**
     * Méthode getLevel : méthode pour récupérer le niveau de la carte magie ou piège.
     * Cette méthode retourne la valeur de l'attribut level.
     *
     * @return valeur de l'attribut level
     */
    @Override
    public int getLevel() {
        return 0;
    }

    /**
     * Méthode setLevel : méthode pour définir le niveau de la carte magie ou piège.
     * Cette méthode prend en paramètre un entier représentant le niveau de la carte magie ou piège.
     * Elle affecte la valeur du paramètre à l'attribut level.
     *
     * @param level entier représentant le niveau de la carte magie ou piège
     */
    @Override
    public void setLevel(final int level) {
        //Aucun level pour les cartes pieges et magies
    }
}
