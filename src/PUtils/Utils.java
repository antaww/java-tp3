package PUtils;

import PExo3.*;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static void cleanBodyPanel(JPanel bodyPanel) {
        Component[] bodyPanelComponents = bodyPanel.getComponents();

        if (bodyPanelComponents.length > 1) {
            for (int i = 1; i < bodyPanelComponents.length; i++) {
                bodyPanel.remove(bodyPanelComponents[i]);
            }
            bodyPanel.setLayout(new GridLayout(1, 1));
            bodyPanel.revalidate();
            bodyPanel.repaint();
        }
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, String attribute, PrimaryType primaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, null, monsterType, reference, atk, def, description);
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, String attribute, PrimaryType primaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, null, tertiaryType, monsterType, reference, atk, def, description);
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, String attribute, PrimaryType primaryType, SecondaryType secondaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        setMonsterCard(monsterCard, name, level, attribute, primaryType, secondaryType, null, monsterType, reference, atk, def, description);
    }

    public static void setMonsterCard(MonsterCard monsterCard, String name, int level, String attribute, PrimaryType primaryType, SecondaryType secondaryType, TertiaryType tertiaryType, MonsterType monsterType, String reference, int atk, int def, String description) {
        monsterCard.setName(name);
        monsterCard.setLevel(level);
        monsterCard.setAttribute(attribute);
        monsterCard.setPrimaryType(primaryType);
        monsterCard.setSecondaryType(secondaryType);
        monsterCard.setTertiaryType(tertiaryType);
        monsterCard.setMonsterType(monsterType);
        monsterCard.setReference(reference);
        monsterCard.setStats(new String[]{String.valueOf(atk), String.valueOf(def)});
        monsterCard.setDescription(description);
    }

}
