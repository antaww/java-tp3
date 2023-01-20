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
import java.util.Objects;

public class Exo5 {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 5 - Yu-Gi-Oh!");

        var secondPanel = new JPanel();
        var thirdPanel = new JPanel();
        var itemsSize = new Dimension(150, 60);

        var buttonsPanel = new JPanel();
        var resultLabel = new JLabel();
        var resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(thirdPanel);
        bodyPanel.add(resultPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 120)));
        bodyPanel.add(buttonsPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        resultLabel.setPreferredSize(new Dimension(bodyPanel.getWidth()-100, 200));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        //todo: add a button to create a monstercard or a specialcard
        var fileName = "myCard.txt";

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

        createButton.setPreferredSize(new Dimension(100, 40));
        clearButton.setPreferredSize(new Dimension(100, 40));

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

        var levelSpinnerModel = new SpinnerNumberModel(0, 0, 12, 1);
        var attackSpinnerModel = new SpinnerNumberModel(0, 0, 5000, 100);
        var defenseSpinnerModel = new SpinnerNumberModel(0, 0, 5000, 100);
        levelSpinner.setModel(levelSpinnerModel);
        attackSpinner.setModel(attackSpinnerModel);
        defenseSpinner.setModel(defenseSpinnerModel);

        var listElements = new String[]{
                "Aqua", "Bête", "Bête-ailée", "Bête-divine", "Bête-guerrier", "Créateur", "Cyberse", "Dinosaure", "Dragon",
                "Démon", "Effet", "Elfe", "Esprit", "Flip", "Fusion", "Guerrier", "Gémeau", "Insecte", "Lien", "Machine",
                "Magicien", "Pendule", "Plante", "Poisson", "Psychique", "Pyro", "Reptile", "Rituel", "Rocher", "Serpent De Mer",
                "Synchro", "Synthoniseur", "Tonnerre", "Toon", "Union", "Wyrm", "Xyz", "Zombie"
        };

        var checkListItems = new CheckList.CheckListItem[listElements.length];
        for (var i = 0; i < listElements.length; i++) {
            checkListItems[i] = new CheckList.CheckListItem(listElements[i]);
        }
        var list = new JList<>(checkListItems);
        list.setCellRenderer(new CheckList.CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        secondPanel.add(nameField);
        secondPanel.add(levelSpinner);
        secondPanel.add(attributeComboBox);
        secondPanel.add(new JScrollPane(list));
        thirdPanel.add(referenceField);
        thirdPanel.add(attackSpinner);
        thirdPanel.add(defenseSpinner);
        thirdPanel.add(descriptionArea);
        buttonsPanel.add(createButton);
        buttonsPanel.add(clearButton);

        setComponentSize(secondPanel, itemsSize);
        setComponentSize(thirdPanel, itemsSize);

        for (var attribute : Attribute.values()) {
            attributeComboBox.addItem(attribute.getDisplayName());
        }

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
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var myCard = new MonsterCard(name, level, attribute, types.toString(), reference, attack, defense, description);

            try {
                saveCard(myCard, fileName);
                System.out.println("Card successfully saved to " + fileName);
                var card = readCard(fileName);
                resultLabel.setText(Utils.convertUnderscoresToSpaces("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + card.getName() +
                        "<br><u>Niveau</u> : " + (card.getLevel() != -1 ? card.getLevel() : "Aucun niveau disponible pour cette carte.") +
                        "<br><u>Attribut</u> : " + card.getAttribute() +
                        "<br><u>Types</u> : " + Utils.translateString(card.getTypes()) +
                        "<br><u>Référence</u> : " + (!Objects.equals(card.getReference(), "N/A") ? card.getReference().toUpperCase() : "Aucune référence disponible pour cette carte.") +
                        "<br><u>Statistiques</u> : " + card.getStats() +
                        "<br><u>Description</u> : " + card.getDescription() +
                        "</div>" +
                        "</html>")
                );
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        clearButton.addActionListener(e -> {
            for (var panel : new JPanel[]{secondPanel, thirdPanel}) {
                for (var component : panel.getComponents()) {
                    if (component instanceof JSpinner) {
                        ((JSpinner) component).setValue(0);
                    } else if (component instanceof JTextField) {
                        ((JTextField) component).setText("");
                    }
                    if(component instanceof JScrollPane){
                        var list1 = (JList) ((JScrollPane) component).getViewport().getView();
                        for (var i = 0; i < list1.getModel().getSize(); i++) {
                            var item = (CheckList.CheckListItem) list1.getModel().getElementAt(i);
                            item.setSelected(false);
                            list.repaint(list.getCellBounds(i, i));
                        }
                    }
                }
            }
            attributeComboBox.setSelectedIndex(0);
            resultLabel.setText("");
        });

        //to graphically create specialcard :
        //name : text field
        //type : combo box
        //icon : combo box
        //reference : text field
        //description : text area

        Utils.displayFrame(frame);
    }

    private static void setComponentSize(JPanel panel, Dimension size) {
        for (var component : panel.getComponents()) {
            component.setPreferredSize(size);
        }
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