package PExo3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static PUtils.Utils.cleanBodyPanel;
import static PUtils.Utils.setMonsterCard;

public class Exo3 {
    public static void main(JFrame frame) {
        JPanel bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        cleanBodyPanel(bodyPanel);
        JLabel titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 3.1 Yu-Gi-Oh!");

        JPanel secondPanel = new JPanel();
        JLabel subtitleLabel = new JLabel(" Carte Monstre");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPanel.add(subtitleLabel);
        bodyPanel.add(secondPanel);
        bodyPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
        JPanel thirdPanelBody = new JPanel(new BorderLayout());
        thirdPanel.add(thirdPanelBody);

        JPanel displayButtonPanel = new JPanel();
        JButton displayButton = new JButton("Afficher la carte");
        displayButton.setPreferredSize(new Dimension(200, 30));
        displayButtonPanel.add(displayButton);
        thirdPanelBody.add(displayButtonPanel, BorderLayout.NORTH);

        JLabel resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setText("Cliquer sur le bouton pour afficher la carte...");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        JPanel resultImagePanel = new JPanel();

        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultPanel.add(resultImagePanel);
        thirdPanelBody.add(resultPanel, BorderLayout.CENTER);

        bodyPanel.add(thirdPanel);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        final Boolean[] isDisplayButtonClicked = {false};

        displayButton.addActionListener(e -> {
            if (!isDisplayButtonClicked[0]) {
                isDisplayButtonClicked[0] = true;
                MonsterCard monsterCard1 = new MonsterCard();
                setMonsterCard(monsterCard1, "Invocateur Dragon Bleu", 4, "Vent", PrimaryType.Magicien, MonsterType.Effet, "ys14-fr017", 1500, 600, "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck a votre main.");
                resultLabel.setText("<html>" +
                        "<div>" +
                        "<p><u>Nom</u> : " + monsterCard1.getName() +
                        "<br><u>Niveau</u> : " + monsterCard1.getLevel() +
                        "<br><u>Attribut</u> : " + monsterCard1.getAttribute() +
                        "<br><u>Types</u> : " + monsterCard1.getAllTypes() +
                        "<br><u>Référence</u> : " + monsterCard1.getReference().toUpperCase() +
                        "<br><u>Statistiques</u> : " + monsterCard1.getStats() +
                        "<br><u>Description</u> : " + monsterCard1.getDescription() +
                        "</div>" +
                        "</html>");
                try {
                    String reference = monsterCard1.getReference();
                    String firstPart = reference.substring(0, reference.indexOf("-"));
                    URL url = new URL("https://www.ultrajeux.com/images/yugioh/scan/normal/fr/" + firstPart + "/" + reference + ".jpg");
                    BufferedImage image = ImageIO.read(url);
                    Image scaledImage = image.getScaledInstance(image.getWidth() / 2, image.getHeight() / 2, Image.SCALE_SMOOTH);
                    JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
                    resultImagePanel.add(picLabel);
                } catch (IOException ioException) {
                    JLabel errorLabel = new JLabel("Image error 404");
                    resultImagePanel.add(errorLabel);
                }
                displayButton.setText("Masquer la carte");
                resultImagePanel.setVisible(true);
                resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
                resultLabel.setPreferredSize(new Dimension(402, 250));
            } else {
                isDisplayButtonClicked[0] = false;
                displayButton.setText("Afficher la carte");
                resultLabel.setText("Cliquer sur le bouton pour afficher la carte...");
                resultPanel.setLayout(new FlowLayout());
                resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
//                resultPanel.setPreferredSize(new Dimension(402, 0));
                resultImagePanel.removeAll();
                resultImagePanel.setVisible(false);
            }
            System.out.println(resultPanel.getLayout());
        });
        System.out.println(resultPanel.getLayout());
        frame.setVisible(true);
    }
}