package Exo2;

import javax.swing.*;
import java.awt.*;

public class exo2_2 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Component[] bodyPanelComponents = bodyPanel.getComponents();

        if(bodyPanelComponents.length > 1) {
            for (int i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }

        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.2 - BoxLayout");

        frame.setVisible(true);
    }

}
