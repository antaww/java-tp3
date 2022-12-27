package Exo2;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class password {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("BoxLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create the panel and set its layout to BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the name input and password input to the panel
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(50, 30));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(50, 30));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Create a panel for the buttons and set its layout to FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Add the "OK" and "Cancel" buttons to the panel
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel);

        // Add the panel to the frame
        frame.add(panel);

        // Display the frame
        frame.setVisible(true);
    }
}


