package Exo2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Utils.utils.cleanBodyPanel;

public class Exo2_3 {
    private static final int CARD_WIDTH = 640/13;
    private static final int CARD_HEIGHT = 256/4;
    static Random random = new Random();
    static BufferedImage spriteSheet;

    public static void main(JFrame frame){
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 2.3 - Afficher Cartes");

        JPanel secondPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel cardsPanel = new JPanel();
        JButton pickCardButton = new JButton("Tirer une carte");
        buttonPanel.add(pickCardButton);
        secondPanel.add(buttonPanel, BorderLayout.NORTH);
        secondPanel.add(cardsPanel, BorderLayout.CENTER);

        bodyPanel.add(secondPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        try {
            String SPRITE_SHEET_FILENAME = "src/Exo2/images/deck.png";
            spriteSheet = ImageIO.read(new File(SPRITE_SHEET_FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pickCardButton.addActionListener(e -> {
            int numCards = cardsPanel.getComponentCount();
            if (numCards < 52) {
                BufferedImage cardImage = pickRandomCard(numCards);
                Image scaledCardImage = cardImage.getScaledInstance(CARD_WIDTH/2, CARD_HEIGHT/2, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledCardImage);
                JLabel label = new JLabel(icon);
                cardsPanel.add(label);
                cardsPanel.revalidate();
                cardsPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Il n'y a plus de cartes disponibles");
            }
        });

        frame.setVisible(true);
    }

    static ArrayList<Integer> cards = new ArrayList<>();
    private static BufferedImage pickRandomCard(int numCards) {
        cards = numCards == 0 ? new ArrayList<>() : cards;
        int index;
        do {
            index = random.nextInt(52);
        } while (cards.contains(index));
        cards.add(index);
        cards.sort(Integer::compareTo);
        int x = index % 13 * CARD_WIDTH;
        int y = index / 13 * CARD_HEIGHT;
        return spriteSheet.getSubimage(x, y, CARD_WIDTH, CARD_HEIGHT);
    }
}