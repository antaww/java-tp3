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

/**
 * Classe Exo2_3 : affichage aléatoire de cartes d'un jeu de cartes.
 * Cette classe permet d'afficher des cartes d'un jeu de cartes de manière aléatoire lorsque l'utilisateur clique sur le bouton "Tirer une carte".
 * Elle utilise une image contenant toutes les cartes pour sélectionner une carte aléatoire et l'afficher.
 * Lorsque toutes les cartes ont été affichées, une boîte de dialogue s'affiche pour indiquer qu'il n'y a plus de cartes disponibles.
 */
public class Exo2_3 {
    private static final int CARD_WIDTH = 640 / 13;
    private static final int CARD_HEIGHT = 256 / 4;
    static Random random = new Random();
    static BufferedImage spriteSheet;
    static ArrayList<Integer> cards = new ArrayList<>();

    /**
     * Méthode main : point d'entrée de l'application.
     * Cette méthode permet d'initialiser l'affichage de l'application et de créer les cartes aléatoires.
     * Elle prend en paramètre un objet de type JFrame qui représente la fenêtre principale de l'application.
     * Elle utilise également des méthodes de la classe Utils pour nettoyer le contenu de la fenêtre et afficher les cartes créées.
     * Elle utilise également une image de sprite pour créer les cartes aléatoirement.
     *
     * @param frame objet JFrame représentant la fenêtre principale de l'application
     * @see fr.ynov.tp3.PUtils.Utils
     * @see javax.swing
     * @see java.awt
     * @see java.awt.image
     * @see java.io
     */
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

    /**
     * Méthode pickRandomCard : permet de sélectionner une carte aléatoire.
     * Cette méthode permet de sélectionner une carte aléatoire parmi les 52 cartes d'un jeu de cartes.
     * Elle prend en paramètre un entier représentant le nombre de cartes déjà affichées.
     * Elle utilise une image de sprite pour sélectionner une carte aléatoire.
     * Elle utilise également une liste pour stocker les cartes déjà affichées afin de ne pas en afficher deux fois.
     *
     * @param numCards entier représentant le nombre de cartes déjà affichées
     * @return objet BufferedImage représentant la carte sélectionnée
     * @see java.awt.image
     * @see java.util
     */
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
