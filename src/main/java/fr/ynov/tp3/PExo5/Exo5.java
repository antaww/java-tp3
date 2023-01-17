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
import java.util.ArrayList;

public class Exo5 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 5 - Yu-Gi-Oh!");

        var secondPanel = new JPanel(new GridLayout(3, 5));
        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        var resultLabel = new JLabel();

        secondPanel.add(resultLabel);

        //todo: add a button to create a monstercard or a specialcard
        var fileName = "myCard.txt";

        //to graphically create monstercard :
        //name : text field
        //level : spinner
        //attribute : combo box
        //types: checklist
        //reference : text field
        //attack : spinner
        //defense : spinner
        //description : text area
        var nameField = new JTextField();
        var levelSpinner = new JSpinner();
        var attributeComboBox = new JComboBox<String>();
        var typesList = new JList<String>();
        var referenceField = new JTextField();
        var attackSpinner = new JSpinner();
        var defenseSpinner = new JSpinner();
        var descriptionArea = new JTextField();
        var createButton = new JButton("Créer");
        var clearButton = new JButton("Effacer");

        nameField.setBorder(BorderFactory.createTitledBorder(null, "Nom", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        levelSpinner.setBorder(BorderFactory.createTitledBorder(null, "Niveau", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        attributeComboBox.setBorder(BorderFactory.createTitledBorder(null, "Attribut", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        typesList.setBorder(BorderFactory.createTitledBorder(null, "Types", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        referenceField.setBorder(BorderFactory.createTitledBorder(null, "Référence", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        attackSpinner.setBorder(BorderFactory.createTitledBorder(null, "Attaque", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        defenseSpinner.setBorder(BorderFactory.createTitledBorder(null, "Défense", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        descriptionArea.setBorder(BorderFactory.createTitledBorder(null, "Description", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));

        nameField.setFont(new Font("Arial", Font.BOLD, 12));
        levelSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        attributeComboBox.setFont(new Font("Arial", Font.BOLD, 12));
        typesList.setFont(new Font("Arial", Font.BOLD, 12));
        referenceField.setFont(new Font("Arial", Font.BOLD, 12));
        attackSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        defenseSpinner.setFont(new Font("Arial", Font.BOLD, 12));
        descriptionArea.setFont(new Font("Arial", Font.BOLD, 12));

        checkNegativeSpinnerInt(levelSpinner);
        checkNegativeSpinnerInt(attackSpinner);
        checkNegativeSpinnerInt(defenseSpinner);
        MoreIntToSpinner(attackSpinner);
        MoreIntToSpinner(defenseSpinner);

        for (var attribute : Attribute.values()) {
            attributeComboBox.addItem(attribute.getDisplayName());
        }

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
                new CheckList.CheckListItem("Elfe"),
                new CheckList.CheckListItem("Démon"),
                new CheckList.CheckListItem("Poisson"),
                new CheckList.CheckListItem("Insecte"),
                new CheckList.CheckListItem("Machine"),
                new CheckList.CheckListItem("Plante"),
                new CheckList.CheckListItem("Psychique"),
                new CheckList.CheckListItem("Pyro"),
                new CheckList.CheckListItem("Reptile"),
                new CheckList.CheckListItem("Rocher"),
                new CheckList.CheckListItem("Serpent De Mer"),
                new CheckList.CheckListItem("Magicien"),
                new CheckList.CheckListItem("Tonnerre"),
                new CheckList.CheckListItem("Guerrier"),
                new CheckList.CheckListItem("Bête-ailée"),
                new CheckList.CheckListItem("Wyrm"),
                new CheckList.CheckListItem("Zombie"),
                new CheckList.CheckListItem("Fusion"),
                new CheckList.CheckListItem("Lien"),
                new CheckList.CheckListItem("Pendule"),
                new CheckList.CheckListItem("Rituel"),
                new CheckList.CheckListItem("Xyz"),
                new CheckList.CheckListItem("Flip"),
                new CheckList.CheckListItem("Gémeau"),
                new CheckList.CheckListItem("Esprit"),
                new CheckList.CheckListItem("Synchro"),
                new CheckList.CheckListItem("Toon"),
                new CheckList.CheckListItem("Synthoniseur"),
                new CheckList.CheckListItem("Union"),
                new CheckList.CheckListItem("Effet")
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
            }
        });

        secondPanel.add(nameField);
        secondPanel.add(levelSpinner);
        secondPanel.add(attributeComboBox);
        secondPanel.add(new JScrollPane(list));
        secondPanel.add(referenceField);
        secondPanel.add(attackSpinner);
        secondPanel.add(defenseSpinner);
        secondPanel.add(descriptionArea);
        secondPanel.add(createButton);
        secondPanel.add(clearButton);

        createButton.addActionListener(e -> {
            var name = nameField.getText();
            var level = (int) levelSpinner.getValue();
            var attribute = fromDisplayName((String) attributeComboBox.getSelectedItem());
            var types = new ArrayList<String>();
            fillTypes(list, types);
            var reference = referenceField.getText();
            var attack = (int) attackSpinner.getValue();
            var defense = (int) defenseSpinner.getValue();
            var description = descriptionArea.getText();

            if (name.isEmpty() || types.isEmpty() || reference.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ICarteYuGiOh myCard = new MonsterCard(name, level, attribute, types.toString(), reference, attack, defense, description);
            System.out.println(myCard);

            try {
                saveCard(myCard, fileName);
                System.out.println("Card successfully saved to " + fileName);
            } catch (IOException f) {
                f.printStackTrace();
            }

            try {
                var card = readCard(fileName);
                resultLabel.setText(card.getName());
            } catch (IOException | ClassNotFoundException g) {
                g.printStackTrace();
            }
        });

        clearButton.addActionListener(e -> {
            for (var component : secondPanel.getComponents()) {
                if (component instanceof JSpinner) {
                    ((JSpinner) component).setValue(0);
                } else if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
                if(component instanceof JScrollPane){
                    var list1 = (JList) ((JScrollPane) component).getViewport().getView();
                    System.out.println(list1);
                    for (var i = 0; i < list1.getModel().getSize(); i++) {
                        var item = (CheckList.CheckListItem) list1.getModel().getElementAt(i);
                        item.setSelected(false);
                        list.repaint(list.getCellBounds(i, i));
                        System.out.println(item);
                    }
                }
            }
        });

        //to graphically create specialcard :
        //name : text field
        //type : combo box
        //icon : combo box
        //reference : text field
        //description : text area

        Utils.displayFrame(frame);
    }

    private static void MoreIntToSpinner(JSpinner attackSpinner) {
        attackSpinner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    attackSpinner.setValue((int) attackSpinner.getValue() + 100);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    attackSpinner.setValue((int) attackSpinner.getValue() - 100);
                }
            }
        });
    }

    private static void checkNegativeSpinnerInt(JSpinner levelSpinner) {
        levelSpinner.addChangeListener(e -> {
            if ((int) levelSpinner.getValue() < 0) {
                levelSpinner.setValue(0);
            }
        });
    }

    private static void fillTypes(JList<CheckList.CheckListItem> list, ArrayList<String> types) {
        for (var i = 0; i < list.getModel().getSize(); i++) {
            var item = (CheckList.CheckListItem) list.getModel().getElementAt(i);
            if (item.isSelected()) {
                types.add(item.toString());
            }
        }
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

    private static Attribute fromDisplayName(String displayName) {
        for (var a : Attribute.values()) {
            if (a.getDisplayName().equalsIgnoreCase(displayName)) {
                return a;
            }
        }
        throw new IllegalArgumentException("No constant with display name " + displayName + " found");
    }
}