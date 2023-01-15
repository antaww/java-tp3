package fr.ynov.tp3.PExo5;

import fr.ynov.tp3.PExo3.Attribute;
import fr.ynov.tp3.PExo3.MonsterCard;
import fr.ynov.tp3.PUtils.CheckList;
import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Exo5 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 5 - Yu-Gi-Oh!");

        var secondPanel = new JPanel(new GridLayout(3, 4));
        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        var resultLabel = new JLabel();

        secondPanel.add(resultLabel);

        //todo: add a button to create a monstercard or a specialcard
        ICarteYuGiOh myCard = new MonsterCard("Derk Magician", 7, Attribute.DARK, "Spellcaster", "XXXX-YYYYY", 2500, 2100, "The ultimate wizard in terms of attack and defense.");
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
        var levelSpinner = new JSpinner();
        var typesList = new JList<String>();
        var referenceField = new JTextField();
        var attackSpinner = new JSpinner();
        var defenseSpinner = new JSpinner();
        var descriptionArea = new JTextField();
        var createButton = new JButton("Créer");
        var clearButton = new JButton("Effacer");

        nameField.setBorder(BorderFactory.createTitledBorder(null, "Nom", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        levelSpinner.setBorder(BorderFactory.createTitledBorder(null, "Niveau", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        typesList.setBorder(BorderFactory.createTitledBorder(null, "Types", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        referenceField.setBorder(BorderFactory.createTitledBorder(null, "Référence", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        attackSpinner.setBorder(BorderFactory.createTitledBorder(null, "Attaque", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        defenseSpinner.setBorder(BorderFactory.createTitledBorder(null, "Défense", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        descriptionArea.setBorder(BorderFactory.createTitledBorder(null, "Description", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));

        nameField.setFont(new Font("Arial", Font.BOLD, 12));
        levelSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        typesList.setFont(new Font("Arial", Font.BOLD, 12));
        referenceField.setFont(new Font("Arial", Font.BOLD, 12));
        attackSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        defenseSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        descriptionArea.setFont(new Font("Arial", Font.BOLD, 12));

        //todo: convert listElements to checkBoxList
        var listElements = new String[]{
                "Aqua", "Bête", "Bête-guerrier", "Créateur", "Cyberse", "Dinosaure", "Bête-divine",
                "Dragon", "Elfe", "Démon", "Poisson", "Insecte", "Machine", "Plante", "Psychique", "Pyro", "Reptile", "Rocher",
                "Serpent De Mer", "Magicien", "Tonnerre", "Guerrier", "Bête-ailée", "Wyrm", "Zombie", "Fusion", "Lien", "Pendule",
                "Rituel", "Xyz", "Flip", "Gémeau", "Esprit", "Synchro", "Toon", "Synthoniseur", "Union", "Effet"
        };

        var list = new JList<CheckList.CheckListItem>(new CheckList.CheckListItem[] {
                new CheckList.CheckListItem("Aqua"),
                new CheckList.CheckListItem("Bête"),
                new CheckList.CheckListItem("Bête-guerrier"),
                new CheckList.CheckListItem("Créateur"),
                new CheckList.CheckListItem("Cyberse"),
                new CheckList.CheckListItem("Dinosaure"),
                new CheckList.CheckListItem("Bête-divine"),
                new CheckList.CheckListItem("Dragon"),
        });
        list.setCellRenderer(new CheckList.CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                var list = (JList) event.getSource();
                var index = list.locationToIndex(event.getPoint());
                var item = (CheckList.CheckListItem) list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                list.repaint(list.getCellBounds(index, index));
                System.out.println(item);
            }
        });

        secondPanel.add(nameField);
        secondPanel.add(levelSpinner);
        secondPanel.add(list);
        secondPanel.add(referenceField);
        secondPanel.add(attackSpinner);
        secondPanel.add(defenseSpinner);
        secondPanel.add(descriptionArea);
        secondPanel.add(createButton);
        secondPanel.add(clearButton);

        clearButton.addActionListener(e -> {
            for (var component : secondPanel.getComponents()) {
                if (component instanceof JSpinner) {
                    ((JSpinner) component).setValue(0);
                } else if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
        });

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
