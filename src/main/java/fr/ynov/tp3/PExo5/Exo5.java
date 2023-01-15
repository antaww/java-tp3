package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Exo5 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 5 - Yu-Gi-Oh!");

        var secondPanel = new JPanel(new GridLayout(2, 4));
        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        var resultLabel = new JLabel();

        secondPanel.add(resultLabel);

        //todo: add a button to create a monstercard or a specialcard
        ICarteYuGiOh myCard = new MonsterCard("Derk Magician", 7, Attribute.DARK, "Spellcaster", "XXXX-YYYYY", 2500, 2100,"The ultimate wizard in terms of attack and defense.");
        var fileName = "myCard.txt";

        //to graphically create monstercard :
        //name : text field
        //level : spinner
        //types: combo box
        //reference : text field
        //attack : spinner
        //defense : spinner
        //description : text area
        var nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Nom"));
        var levelSpinner = new JSpinner();
        levelSpinner.setBorder(BorderFactory.createTitledBorder("Niveau"));
        var typeComboBox = new JComboBox<String>();
        typeComboBox.setBorder(BorderFactory.createTitledBorder("Type"));
        var referenceField = new JTextField();
        var attackSpinner = new JSpinner();
        var defenseSpinner = new JSpinner();
        var descriptionArea = new JTextArea();
        secondPanel.add(nameField);
        secondPanel.add(levelSpinner);
        secondPanel.add(typeComboBox);
        secondPanel.add(referenceField);
        secondPanel.add(attackSpinner);
        secondPanel.add(defenseSpinner);
        secondPanel.add(descriptionArea);

        //to graphically create specialcard :
        //name : text field
        //type : combo box
        //icon : combo box
        //reference : text field
        //description : text area

        try {
            saveCard(myCard, fileName);
            System.out.println("Card successfully saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            var card = readCard(fileName);
            System.out.println(card);
            resultLabel.setText(card.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Utils.displayFrame(frame);
    }

    private static void saveCard(ICarteYuGiOh card, String fileName) throws IOException {
        var fos = new FileOutputStream(fileName);
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(card);
        oos.close();
        fos.close();
    }

    private static ICarteYuGiOh readCard(String fileName) throws IOException, ClassNotFoundException {
        var fis = new FileInputStream(fileName);
        var ois = new ObjectInputStream(fis);
        var card = (ICarteYuGiOh) ois.readObject();
        ois.close();
        fis.close();
        return card;
    }
}
