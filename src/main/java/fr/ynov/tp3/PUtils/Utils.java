package fr.ynov.tp3.PUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static void cleanBodyPanel(final JPanel bodyPanel) {
        final var bodyPanelComponents = bodyPanel.getComponents();

        if (bodyPanelComponents.length > 1) {
            for (var i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }
    }

    public static void setPanelsBackgroundColor(final Container container) {
        container.setBackground(new Color(33, 33, 33));
        for (final var c : container.getComponents()) {
            if (c instanceof Container) {
                setPanelsBackgroundColor((Container) c);
            }
            if (c instanceof JButton) {
                c.setBackground(new Color(28, 28, 28));
                ((JButton) c).setBorderPainted(false);
                ((JButton) c).setFocusPainted(false);
            }
            if (c instanceof JComboBox<?>) {
                c.setBackground(new Color(28, 28, 28));
                ((JComboBox<?>) c).setBorder(null);
                ((JComboBox<?>) c).setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public static void setFontColor(final Container container) {
        container.setForeground(new Color(255, 255, 255));
        for (final var c : container.getComponents()) {
            if (c instanceof Container) {
                setFontColor((Container) c);
            }
        }
    }

    public static void setBackgroundColorForMenuBar(final JMenuBar menuBar, final Color color) {
        for (final var c : menuBar.getComponents()) {
            c.setBackground(color);
            c.setFont(new Font("Arial", Font.BOLD, 14));
            if (c instanceof JMenu) {
                for (final var c2 : ((JMenu) c).getMenuComponents()) {
                    c2.setBackground(color);
                    c2.setForeground(new Color(255, 255, 255));
                    c2.setFont(new Font("Arial", Font.BOLD, 14));
                }
            }
        }
    }

    public static void removeMenuBarBorders(final JMenuBar menuBar) {
        for (final var component : menuBar.getComponents()) {
            if (component instanceof JMenu) {
                for (final var component1 : ((JMenu) component).getMenuComponents()) {
                    if (component1 instanceof JMenuItem) {
                        ((JMenuItem) component1).setBorder(null);
                    }
                }
            }
        }
    }

    public static void displayFrame(final JFrame frame) {
        setPanelsBackgroundColor(frame);
        setFontColor(frame);
        frame.setVisible(true);
    }

    public static JsonElement getJsonElement(final JComboBox<String> comboBox, final String cardType) {
        final JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseReader(new FileReader("src/main/resources/cards.json"));
        } catch (final FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        final var monstersList = new ArrayList<String>();
        jsonElement.getAsJsonArray().forEach(jsonElement1 -> {
            final var type = jsonElement1.getAsJsonObject().get("type").getAsString();
            if (type.contains(cardType)) {
                monstersList.add(jsonElement1.getAsJsonObject().get("name").getAsString());
            }
        });
        monstersList.sort(String::compareToIgnoreCase);
        monstersList.forEach(comboBox::addItem);
        return jsonElement;
    }

    public static String translateString(String stringToTranslate) {
        final Map<String, String> translations = new HashMap<>();
        //PrimaryType
        translations.put("Aqua", "Aqua");
        translations.put("Beast", "Bête /");
        translations.put("Beast-Warrior", "Bête-guerrier /");
        translations.put("Creator-God", "Créateur /");
        translations.put("Cyberse", "Cyberse");
        translations.put("Dinosaur", "Dinosaure /");
        translations.put("Divine-Beast", "Bête-divine /");
        translations.put("Dragon", "Dragon");
        translations.put("Fairy", "Elfe /");
        translations.put("Fiend", "Démon /");
        translations.put("Fish", "Poisson /");
        translations.put("Insect", "Insecte /");
        translations.put("Machine", "Machine");
        translations.put("Plant", "Plante /");
        translations.put("Psychic", "Psychique /");
        translations.put("Pyro", "Pyro");
        translations.put("Reptile", "Reptile");
        translations.put("Rock", "Rocher /");
        translations.put("Sea Serpent", "Serpent De Mer /");
        translations.put("Spellcaster", "Magicien /");
        translations.put("Thunder", "Tonnerre /");
        translations.put("Warrior", "Guerrier /");
        translations.put("Winged Beast", "Bête-ailée /");
        translations.put("Wyrm", "Wyrm");
        translations.put("Zombie", "Zombie");

        //SecondaryType
        translations.put("Fusion", "Fusion");
        translations.put("Link", "Lien /");
        translations.put("Pendulum", "Pendule /");
        translations.put("Ritual", "Rituel /");
        translations.put("XYZ", "Xyz /");

        //TertiaryType
        translations.put("Flip", "Flip");
        translations.put("Gemini", "Gémeau /");
        translations.put("Spirit", "Esprit /");
        translations.put("Synchro", "Synchro");
        translations.put("Toon", "Toon");
        translations.put("Tuner", "Synthoniseur /");
        translations.put("Union", "Union");

        //MonsterType
        translations.put("Effect", "Effet");
        translations.put("Normal", ""); // Do not display "Normal" in the card
        translations.put("Monster", ""); // Do not display "Monster" in the card

        final StringBuilder output;
        if (stringToTranslate.contains(" ")) { //If the string to translate is a combination of words
            for (final var entry : translations.entrySet()) {
                if (stringToTranslate.contains(entry.getKey())) {
                    stringToTranslate = stringToTranslate.replace(entry.getKey(), entry.getValue());
                }
            }
        }

        final var words = stringToTranslate.split(" ");
        output = new StringBuilder();
        for (final var word : words) {
            final var translatedWord = translations.get(word);
            if (translatedWord != null) {
                output.append(translatedWord).append(" / ");
            } else {
                output.append(word).append(" ");
            }
        }

        var result = output.toString().trim();
        while (result.endsWith(" /")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public static String convertUnderscoresToSpaces(final String str) {
        return str.replaceAll("_", " ");
    }

    public static String replaceByUnderscore(final String str) {
        return str.replaceAll("[ -]", "_");
    }

    public static Map<String, JComponent> createCardPanel(final JPanel bodyPanel, final String subtitle) {
        final var secondPanel = new JPanel();
        final var subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPanel.add(subtitleLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        final var thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        final var thirdPanelBody = new JPanel(new BorderLayout());
        thirdPanel.add(thirdPanelBody);

        final var comboBoxPanel = new JPanel();
        final var comboBox = new JComboBox<String>();
        comboBoxPanel.add(comboBox);
        thirdPanelBody.add(comboBoxPanel, BorderLayout.NORTH);

        final var resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setText("Choisissez une carte à afficher...");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        final var resultImagePanel = new JPanel();

        final var resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultPanel.add(resultImagePanel);
        thirdPanelBody.add(resultPanel, BorderLayout.CENTER);

        bodyPanel.add(thirdPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        return Map.of("comboBox", comboBox, "resultLabel", resultLabel, "resultImagePanel", resultImagePanel, "resultPanel", resultPanel);
    }

    public static void displayCardImage(final JLabel resultLabel, final JPanel resultImagePanel, final JPanel resultPanel, final String cardImage) {
        try {
            final var url = new URL(cardImage);
            final var image = ImageIO.read(url);
            final var scaledImage = image.getScaledInstance(280, 435, Image.SCALE_SMOOTH); // Default size : 525 × 768
            final var picLabel = new JLabel(new ImageIcon(scaledImage));
            resultImagePanel.add(picLabel);
        } catch (final IOException ioException) {
            final var errorLabel = new JLabel("Image error 404");
            resultImagePanel.add(errorLabel);
        }
        resultImagePanel.setVisible(true);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultLabel.setPreferredSize(new Dimension(402, 250));
    }
}
