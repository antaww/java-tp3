package fr.ynov.tp3.PUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Classe CheckList : création d'une classe pour définir une liste de cases à cocher.
 * Elle est utilisée pour définir la liste des attributs d'une carte Monstre, Magie ou Piège de Yu-Gi-Oh!, dans l'exercice 5.
 */
public class CheckList {
    /**
     * Classe CheckListItem : création d'une classe pour définir un élément de liste de cases à cocher.
     */
    public static class CheckListItem {

        private final String label;
        private boolean isSelected = false;

        /**
         * Constructeur CheckListItem : création d'un élément de liste de cases à cocher.
         *
         * @param label libellé de l'élément de liste de cases à cocher
         */
        public CheckListItem(final String label) {
            this.label = label;
        }

        /**
         * Méthode isSelected : méthode pour récupérer l'état de sélection de l'élément de liste de cases à cocher.
         *
         * @return état de sélection de l'élément de liste de cases à cocher
         */
        public boolean isSelected() {
            return isSelected;
        }

        /**
         * Méthode setSelected : méthode pour définir l'état de sélection de l'élément de liste de cases à cocher.
         *
         * @param isSelected état de sélection de l'élément de liste de cases à cocher
         */
        public void setSelected(final boolean isSelected) {
            this.isSelected = isSelected;
        }

        /**
         * Méthode toString : méthode pour récupérer le libellé de l'élément de liste de cases à cocher.
         *
         * @return libellé de l'élément de liste de cases à cocher
         */
        @Override
        public String toString() {
            return label;
        }
    }

    /**
     * Classe CheckListRenderer : création d'une classe pour définir le rendu d'une liste de cases à cocher.
     * Elle hérite de JCheckBox.
     * Elle implémente ListCellRenderer.
     */
    public static class CheckListRenderer extends JCheckBox implements ListCellRenderer {
        /**
         * Méthode getListCellRendererComponent : méthode pour définir le rendu d'une liste de cases à cocher.
         *
         * @param list       liste de cases à cocher
         * @param value      valeur de l'élément de liste de cases à cocher
         * @param index      index de l'élément de liste de cases à cocher
         * @param isSelected état de sélection de l'élément de liste de cases à cocher
         * @param hasFocus   état de focus de l'élément de liste de cases à cocher
         * @return composant de rendu de l'élément de liste de cases à cocher
         */
        public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus) {
            setEnabled(list.isEnabled());
            setSelected(((CheckListItem) value).isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.toString());
            return this;
        }
    }
}