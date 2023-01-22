package fr.ynov.tp3.PExo1;


import fr.ynov.tp3.PExo1.PClasse.Classe;
import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;
import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TP_Classe {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 1 - TP Classe & Étudiant");


        var etudiant1 = new Etudiant("Crews", "Apollo");
        etudiant1.setNote("Maths", "Examen", 2, 10);
        etudiant1.setNote("Français", "Partiels", 5, 2);
        etudiant1.setNote("Français", "Dissertation", 1, 7);
        etudiant1.setNote("EPS", "Badminton", 1, 19.5);
        var etudiant2 = new Etudiant("Buzz", "Léclair");

        var maClasse = new Classe("B2 - Linux/Réseaux");
        maClasse.setEtudiant(etudiant1);
        maClasse.setEtudiant(etudiant2);

        var student3 = maClasse.getEtudiant("Crews Apollo");
        student3.afficherNote();


        var listScrollPanel = new JComboBox<String>();
        var studentList = new ArrayList<>(Arrays.asList(maClasse.getEtudiants()));
        studentList.sort(String::compareToIgnoreCase);
        for (var student : studentList) {
            listScrollPanel.addItem(student);
        }

        var buttonsPanel = new JPanel();
        var button1 = new JButton(maClasse.nom);
        var studentLabel = new JLabel("Sélectionnez un étudiant pour afficher ses informations");
        var classLabel = new JLabel("Cliquez sur la classe pour afficher ses informations");

        listScrollPanel.setPreferredSize(new Dimension(200, 30));
        listScrollPanel.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setPreferredSize(new Dimension(200, 50));
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        studentLabel.setFont(new Font("Arial", Font.BOLD, 13));
        classLabel.setFont(new Font("Arial", Font.BOLD, 13));


        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(studentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(classLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(listScrollPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        buttonsPanel.add(button1, gbc);

        bodyPanel.add(buttonsPanel);

        var studentInfoPanel = new JPanel();
        studentInfoPanel.setPreferredSize(new Dimension(200, 500));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));


        displayStudentInfo(frame, bodyPanel, maClasse, listScrollPanel, studentInfoPanel);

        Utils.displayFrame(frame);


        var panels = bodyPanel.getComponents();
        for (var i = 0; i < panels.length; i++) {
            var panel = (JPanel) panels[i];
            if (i % 2 == 0) {
                panel.setBackground(new Color(0x2ECC71));
            } else {
                panel.setBackground(new Color(0x3498DB));
            }
        }
    }

    private static void displayStudentInfo(JFrame frame, JPanel bodyPanel, Classe maClasse, JComboBox<String> listScrollPane, JPanel studentInfoPanel) {
        listScrollPane.addActionListener(e -> {
            studentInfoPanel.removeAll();
            var selectedStudent = Objects.requireNonNull(listScrollPane.getSelectedItem()).toString();
            var student = maClasse.getEtudiant(selectedStudent);

            var studentInfoNameLabel = new JLabel(student.nom + " " + student.prenom);
            var studentInfoActionPanel = new JPanel();
            var studentInfoActionButton1 = new JButton("Moyenne");
            var studentInfoActionButton2 = new JButton("Afficher toutes les notes");
            var studentInfoActionButton3 = new JButton("Afficher les notes d'une matière");
            var studentInfoActionButton4 = new JButton("Ajouter une note");
            var studentInfoActionResultPanel = new JPanel();

            studentInfoPanel.setLayout(new BorderLayout());

            studentInfoNameLabel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            studentInfoNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            studentInfoNameLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            studentInfoActionPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            studentInfoActionResultPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            studentInfoNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionPanel.setFont(new Font("Arial", Font.BOLD, 13));

            studentInfoPanel.add(studentInfoNameLabel, BorderLayout.NORTH);
            studentInfoPanel.add(studentInfoActionPanel, BorderLayout.CENTER);
            studentInfoPanel.add(studentInfoActionResultPanel, BorderLayout.SOUTH);

            studentInfoActionPanel.add(studentInfoActionButton1);
            studentInfoActionPanel.add(studentInfoActionButton2);
            studentInfoActionPanel.add(studentInfoActionButton3);
            studentInfoActionPanel.add(studentInfoActionButton4);


            displayAverage(frame, bodyPanel, student, studentInfoActionButton1, studentInfoActionResultPanel);

            displayAllNotes(frame, bodyPanel, student, studentInfoActionButton2, studentInfoActionResultPanel);

            displaySpecificGrades(frame, bodyPanel, student, studentInfoActionButton3, studentInfoActionResultPanel);

            addGrade(frame, bodyPanel, student, studentInfoActionButton4, studentInfoActionResultPanel);


            bodyPanel.add(studentInfoPanel);
            Utils.displayFrame(frame);
        });
    }

    // add a grade with a subject, exam name, a grade and a coefficient (not obligatory)
    private static void addGrade(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton4, JPanel studentInfoActionResultPanel) {
        studentInfoActionButton4.addActionListener(e1 -> {
            System.out.println("addGrade");
            studentInfoActionResultPanel.removeAll();
            var studentInfoActionResultTextField1 = new JTextField();
            var studentInfoActionResultTextField2 = new JTextField();
            var studentInfoActionResultTextField3 = new JTextField();
            var studentInfoActionResultTextField4 = new JTextField();
            var studentInfoActionResultButton = new JButton("Ajouter la note");
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();

            studentInfoActionResultTextField1.setBorder(BorderFactory.createTitledBorder(null, "Matière", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField2.setBorder(BorderFactory.createTitledBorder(null, "Examen", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField3.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField4.setBorder(BorderFactory.createTitledBorder(null, "Coefficient (optionnel)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));

            studentInfoActionResultTextField1.setPreferredSize(new Dimension(300, 75));
            studentInfoActionResultTextField2.setPreferredSize(new Dimension(300, 75));
            studentInfoActionResultTextField3.setPreferredSize(new Dimension(300, 75));
            studentInfoActionResultTextField4.setPreferredSize(new Dimension(300, 75));
            studentInfoActionResultButton.setPreferredSize(new Dimension(200, 50));
            topPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 250));
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));



            studentInfoActionResultTextField1.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField2.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField3.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField4.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultButton.setFont(new Font("Arial", Font.BOLD, 18));

            topPanel.add(studentInfoActionResultTextField1);
            topPanel.add(studentInfoActionResultTextField2);
            topPanel.add(studentInfoActionResultTextField3);
            topPanel.add(studentInfoActionResultTextField4);
            bottomPanel.add(studentInfoActionResultButton);
            studentInfoActionResultPanel.add(topPanel);
            studentInfoActionResultPanel.add(bottomPanel);

            studentInfoActionResultButton.addActionListener(e2 -> {
                var subject = studentInfoActionResultTextField1.getText();
                var exam = studentInfoActionResultTextField2.getText();
                var grade = studentInfoActionResultTextField3.getText();
                var coefficient = studentInfoActionResultTextField4.getText();

                if (subject.isEmpty() || exam.isEmpty() || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Double.parseDouble(grade);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "La note doit être un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Double.parseDouble(grade) < 0 || Double.parseDouble(grade) > 20) {
                    JOptionPane.showMessageDialog(frame, "La note doit être comprise entre 0 et 20", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!coefficient.isEmpty()) {
                    try {
                        Integer.parseInt(coefficient);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "Le coefficient doit être un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (coefficient.isEmpty()) {
                    student.setNote(subject, exam, Double.parseDouble(grade));
                } else {
                    student.setNote(subject, exam, Integer.parseInt(coefficient), Double.parseDouble(grade));
                }

                JOptionPane.showMessageDialog(frame, "Note ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            });
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displaySpecificGrades(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton3, JPanel studentInfoActionResultPanel) {
        studentInfoActionButton3.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            var listModel = new DefaultListModel<String>();
            var studentInfoActionResultList = new JList<>(listModel);
            var notes = student.afficherNote();

            studentInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultList.setForeground(Color.WHITE);
            studentInfoActionResultList.setBackground(new Color(33, 33, 33));

            if (notes.length == 0) {
                studentInfoActionResultList.setListData(new String[]{"L'étudiant n'a pas encore de notes"});
                studentInfoActionResultPanel.add(studentInfoActionResultList);
            } else {
                var subject = JOptionPane.showInputDialog("Quelle matière voulez-vous afficher ?");
                var notesBySubject = student.afficherNote(subject);
                if (notesBySubject == null) {
                    studentInfoActionResultList.setListData(new String[]{"L'étudiant n'a pas encore de notes pour cette matière"});
                    studentInfoActionResultPanel.add(studentInfoActionResultList);
                } else {
                    studentInfoActionResultList.setListData(notesBySubject);
                    JScrollPane scrollPane = new JScrollPane(studentInfoActionResultList);
                    scrollPane.setPreferredSize(new Dimension(studentInfoActionResultPanel.getWidth(), 400));
                    studentInfoActionResultPanel.add(scrollPane);
                }
            }
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displayAllNotes(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton2, JPanel studentInfoActionResultPanel) {
        studentInfoActionButton2.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            var listModel = new DefaultListModel<String>();
            var studentInfoActionResultList = new JList<>(listModel);
            var notes = student.afficherNote();
            Arrays.sort(notes);

            studentInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultList.setForeground(Color.WHITE);
            studentInfoActionResultList.setBackground(new Color(33, 33, 33));

            if (notes.length == 0) {
                studentInfoActionResultList.setListData(new String[]{"L'étudiant n'a pas encore de notes"});
                studentInfoActionResultPanel.add(studentInfoActionResultList);
            } else {
                studentInfoActionResultList.setListData(notes);
                JScrollPane scrollPane = new JScrollPane(studentInfoActionResultList);
                scrollPane.setPreferredSize(new Dimension(studentInfoActionResultPanel.getWidth(), 400));
                studentInfoActionResultPanel.add(scrollPane);
            }
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displayAverage(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton1, JPanel studentInfoActionResultPanel) {
        studentInfoActionButton1.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            var studentInfoActionResultLabel = new JLabel();
            var moyenne = student.moyenne("");

            if (moyenne == -1) {
                studentInfoActionResultLabel.setText("L'étudiant n'a pas encore de notes");
            } else {
                studentInfoActionResultLabel.setText("La moyenne de l'étudiant est de " + moyenne);
            }

            studentInfoActionResultLabel.setFont(new Font("Arial", Font.BOLD, 35));
            studentInfoActionResultLabel.setForeground(Color.WHITE);
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 300));
            studentInfoActionResultPanel.add(studentInfoActionResultLabel);
            frame.revalidate();
            frame.repaint();
        });
    }
}
