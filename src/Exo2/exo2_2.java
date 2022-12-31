package Exo2;

import javax.swing.*;
import java.awt.*;

import static Utils.utils.cleanBodyPanel;

public class exo2_2 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.2 - BoxLayout");

        JPanel secondPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        JButton okButton = new JButton("OK");
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel(new BorderLayout());
        JButton cancelButton = new JButton("Cancel");


        JPanel centerSubPanel1 = new JPanel();
        JLabel nameLabel = new JLabel("Nom :");
        JTextField nameTextField = new JTextField();
        JPanel centerSubPanel2 = new JPanel();
        JLabel passwordLabel = new JLabel("Mot de passe :");
        JPasswordField passwordField = new JPasswordField();


        bodyPanel.add(secondPanel);
        secondPanel.add(leftPanel, BorderLayout.WEST);
        secondPanel.add(centerPanel, BorderLayout.CENTER);
        secondPanel.add(rightPanel, BorderLayout.EAST);

        leftPanel.add(okButton, BorderLayout.SOUTH);
        rightPanel.add(cancelButton, BorderLayout.SOUTH);
        centerPanel.add(centerSubPanel1);
        centerPanel.add(centerSubPanel2);
        centerSubPanel1.add(nameLabel);
        centerSubPanel1.add(nameTextField);
        centerSubPanel2.add(passwordLabel);
        centerSubPanel2.add(passwordField);

        leftPanel.setPreferredSize(new Dimension((int) (406/1.6/2.5), 180));
        rightPanel.setPreferredSize(new Dimension((int) (406/1.6/2.5), 180));

        secondPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        centerSubPanel1.setBorder(BorderFactory.createLineBorder(Color.RED));
        centerSubPanel2.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        bodyPanel.setLayout(new GridLayout(2, 1));

        frame.setVisible(true);
        System.out.println(secondPanel.getWidth());
        System.out.println(secondPanel.getHeight());
    }
}
