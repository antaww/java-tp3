package Exo3;

import javax.swing.*;

import java.awt.*;

import static Utils.utils.cleanBodyPanel;

public class Exo3 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3.1 Yu-Gi-Oh!");

        JPanel secondPanel = new JPanel();
        JLabel subtitleLabel = new JLabel(" Carte Monstre");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPanel.add(subtitleLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        JPanel thirdPanelBody = new JPanel(new BorderLayout());
        thirdPanel.add(thirdPanelBody);

        JPanel displayButtonPanel = new JPanel();
        JButton displayButton = new JButton("Afficher la carte");
        displayButton.setPreferredSize(new Dimension(150, 30));
        displayButtonPanel.add(displayButton);
        thirdPanelBody.add(displayButtonPanel, BorderLayout.NORTH);
        thirdPanelBody.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel resultLabel = new JLabel();
        resultLabel.setText("La carte n'a pas encore été affichée...");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        thirdPanelBody.add(resultLabel, BorderLayout.CENTER);

        bodyPanel.add(thirdPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
    }
}
