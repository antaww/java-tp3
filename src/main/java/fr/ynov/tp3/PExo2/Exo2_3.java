package fr.ynov.tp3.PExo2;

import fr.ynov.tp3.PUtils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Exo2_3 {
    private static final int CARD_WIDTH = 640 / 13;
    private static final int CARD_HEIGHT = 256 / 4;
    static Random random = new Random();
    static BufferedImage spriteSheet;
    static ArrayList<Integer> cards = new ArrayList<>();

    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.3 - Afficher Cartes");

        final var secondPanel = new JPanel(new BorderLayout());
        final var buttonPanel = new JPanel();
        final var cardsPanel = new JPanel();
        final var pickCardButton = new JButton("Tirer une carte");
        pickCardButton.setFont(new Font("Arial", Font.BOLD, 15));
        buttonPanel.add(pickCardButton);
        secondPanel.add(buttonPanel, BorderLayout.NORTH);
        secondPanel.add(cardsPanel, BorderLayout.CENTER);
        secondPanel.setPreferredSize(new Dimension(750, 300));

        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        try {
            final var SPRITE_SHEET_FILENAME = "src/main/resources/deck.png";
            spriteSheet = ImageIO.read(new File(SPRITE_SHEET_FILENAME));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        pickCardButton.addActionListener(e -> {
            final var numCards = cardsPanel.getComponentCount();
            if (numCards < 52) {
                final var cardImage = pickRandomCard(numCards);
                final var scaledCardImage = cardImage.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                final var icon = new ImageIcon(scaledCardImage);
                final var label = new JLabel(icon);
                cardsPanel.add(label);
                cardsPanel.revalidate();
                cardsPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Il n'y a plus de cartes disponibles");
            }
        });
        Utils.displayFrame(frame);
    }

    private static BufferedImage pickRandomCard(final int numCards) {
        cards = numCards == 0 ? new ArrayList<>() : cards;
        int index;
        do {
            index = random.nextInt(52);
        } while (cards.contains(index));
        cards.add(index);
        cards.sort(Integer::compareTo);
        final var x = index % 13 * CARD_WIDTH;
        final var y = index / 13 * CARD_HEIGHT;
        return spriteSheet.getSubimage(x, y, CARD_WIDTH, CARD_HEIGHT);
    }
}
