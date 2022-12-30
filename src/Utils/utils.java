package Utils;

import javax.swing.*;
import java.awt.*;

public class utils {
    public static void cleanBodyPanel(JPanel bodyPanel) {
        Component[] bodyPanelComponents = bodyPanel.getComponents();

        if(bodyPanelComponents.length > 1) {
            for (int i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }
    }

}
