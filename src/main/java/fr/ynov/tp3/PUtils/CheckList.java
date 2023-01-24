package fr.ynov.tp3.PUtils;

import javax.swing.*;
import java.awt.*;

public class CheckList {
    public static class CheckListItem {

        private final String label;
        private boolean isSelected = false;

        public CheckListItem(final String label) {
            this.label = label;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(final boolean isSelected) {
            this.isSelected = isSelected;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static class CheckListRenderer extends JCheckBox implements ListCellRenderer {
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