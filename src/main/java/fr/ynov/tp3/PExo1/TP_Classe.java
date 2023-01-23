package fr.ynov.tp3.PExo1;


import fr.ynov.tp3.PExo1.PClasse.Classe;
import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;
import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.nio.file.Paths;
import java.util.List;

public class TP_Classe {
    public static void main(JFrame frame) {
        var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 1 - TP Classe & Étudiant");


        var etudiant1 = new Etudiant("Crews", "Apollo");
        etudiant1.setNote("Mathématiques", "Examen", 2, 10);
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

        var infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(200, 500));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));


        displayStudentInfo(frame, bodyPanel, maClasse, listScrollPanel, infoPanel);

        displayClassInfo(frame, bodyPanel, maClasse, button1, infoPanel);

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


    private static void displayStudentInfo(JFrame frame, JPanel bodyPanel, Classe maClasse, JComboBox<String> listScrollPane, JPanel infoPanel) {
        listScrollPane.addActionListener(e -> {
            infoPanel.removeAll();
            var selectedStudent = Objects.requireNonNull(listScrollPane.getSelectedItem()).toString();
            var student = maClasse.getEtudiant(selectedStudent);

            var studentInfoNameLabel = new JLabel(student.nom + " " + student.prenom);
            var studentInfoActionPanel = new JPanel();
            var studentInfoActionButton1 = new JButton("Moyenne");
            var studentInfoActionButton2 = new JButton("Afficher toutes les notes");
            var studentInfoActionButton3 = new JButton("Afficher les notes d'une matière");
            var studentInfoActionButton4 = new JButton("Ajouter une note");
            var studentInfoActionResultPanel = new JPanel();

            infoPanel.setLayout(new BorderLayout());

            studentInfoNameLabel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            studentInfoNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            studentInfoNameLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            studentInfoActionPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            studentInfoActionResultPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            studentInfoNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionPanel.setFont(new Font("Arial", Font.BOLD, 13));

            infoPanel.add(studentInfoNameLabel, BorderLayout.NORTH);
            infoPanel.add(studentInfoActionPanel, BorderLayout.CENTER);
            infoPanel.add(studentInfoActionResultPanel, BorderLayout.SOUTH);

            studentInfoActionPanel.add(studentInfoActionButton1);
            studentInfoActionPanel.add(studentInfoActionButton2);
            studentInfoActionPanel.add(studentInfoActionButton3);
            studentInfoActionPanel.add(studentInfoActionButton4);


            displayAverage(frame, bodyPanel, student, studentInfoActionButton1, studentInfoActionResultPanel);

            displayAllGrades(frame, bodyPanel, student, studentInfoActionButton2, studentInfoActionResultPanel);

            displaySpecificGrades(frame, bodyPanel, student, studentInfoActionButton3, studentInfoActionResultPanel);

            addGrade(frame, bodyPanel, student, studentInfoActionButton4, studentInfoActionResultPanel);


            bodyPanel.add(infoPanel);
            Utils.displayFrame(frame);
        });
    }

    private static void addGrade(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton4, JPanel studentInfoActionResultPanel) {
        studentInfoActionButton4.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            var studentInfoActionResultComboBox = new JComboBox<>();
            var studentInfoActionResultTextField2 = new JTextField();
            var studentInfoActionResultTextField3 = new JTextField();
            var studentInfoActionResultTextField4 = new JTextField();
            var studentInfoActionResultButton = new JButton("Ajouter la note");
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var matieres = new ArrayList<String>();
            try {
                String filePath = "matieres.txt";
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (var matiere : matieres) {
                studentInfoActionResultComboBox.addItem(matiere);
            }

            studentInfoActionResultTextField2.setBorder(BorderFactory.createTitledBorder(null, "Examen", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField3.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField4.setBorder(BorderFactory.createTitledBorder(null, "Coefficient (optionnel)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));

            studentInfoActionResultComboBox.setPreferredSize(new Dimension(300, 25));
            studentInfoActionResultTextField2.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultTextField3.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultTextField4.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultButton.setPreferredSize(new Dimension(200, 50));
            topPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 250));
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));


            studentInfoActionResultComboBox.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField2.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField3.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultTextField4.setFont(new Font("Arial", Font.BOLD, 18));
            studentInfoActionResultButton.setFont(new Font("Arial", Font.BOLD, 18));

            topPanel.add(studentInfoActionResultComboBox);
            topPanel.add(studentInfoActionResultTextField2);
            topPanel.add(studentInfoActionResultTextField3);
            topPanel.add(studentInfoActionResultTextField4);
            bottomPanel.add(studentInfoActionResultButton);
            studentInfoActionResultPanel.add(topPanel);
            studentInfoActionResultPanel.add(bottomPanel);

            studentInfoActionResultButton.addActionListener(e2 -> {
                var subject = Objects.requireNonNull(studentInfoActionResultComboBox.getSelectedItem()).toString();
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

    private static void displaySpecificGrades(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton3, JPanel infoPanel) {
        studentInfoActionButton3.addActionListener(e1 -> {
            infoPanel.removeAll();
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var comboBoxPanel = new JPanel();
            var comboBox = new JComboBox<>();

            var listModel = new DefaultListModel<String>();
            var studentInfoActionResultList = new JList<>(listModel);
            var matieres = new ArrayList<String>();
            try {
                String filePath = "matieres.txt";
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (var matiere : matieres) {
                comboBox.addItem(matiere);
            }

            studentInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultList.setForeground(Color.WHITE);
            studentInfoActionResultList.setBackground(new Color(33, 33, 33));
            studentInfoActionResultList.setPreferredSize(new Dimension(infoPanel.getWidth(), 350));

            topPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));

            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);

            var notes = student.afficherNote();
            if (notes.length == 0) {
                studentInfoActionResultList.setListData(new String[]{"L'étudiant n'a pas encore de notes"});
                infoPanel.add(studentInfoActionResultList);
            } else {
                comboBox.addActionListener(e2 -> {
                    listModel.removeAllElements();
                    var subject = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                    var notesOfSubject = student.afficherNote(subject);
                    if (notesOfSubject == null) {
                        listModel.addElement("L'étudiant n'a pas encore de notes dans cette matière");
                    } else {
                        for (var note : notesOfSubject) {
                            listModel.addElement(note);
                        }
                    }
                });
                comboBoxPanel.add(comboBox);
                topPanel.add(comboBoxPanel);
                bottomPanel.add(studentInfoActionResultList);
            }

            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displayAllGrades(JFrame frame, JPanel bodyPanel, Etudiant student, JButton studentInfoActionButton2, JPanel studentInfoActionResultPanel) {
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

    private static void displayClassInfo(JFrame frame, JPanel bodyPanel, Classe maClasse, JButton button1, JPanel infoPanel) {
        button1.addActionListener(e -> {
            infoPanel.removeAll();
            var classInfoNameLabel = new JLabel(maClasse.nom);
            var classInfoActionPanel = new JPanel();
            var classInfoActionResultPanel = new JPanel();
            var classInfoActionButton1 = new JButton("Afficher les étudiants");
            var classInfoActionButton2 = new JButton("Afficher les moyennes des étudiants");
            var classInfoActionButton3 = new JButton("Afficher les moyennes des matières");
            var classInfoActionButton4 = new JButton("Ajouter un étudiant");
            var classInfoActionButton5 = new JButton("Ajouter une matière");
            var classInfoActionButton6 = new JButton("Renommer la classe");

            infoPanel.setLayout(new BorderLayout());

            classInfoNameLabel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            classInfoNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classInfoNameLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            classInfoActionPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            classInfoActionResultPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            classInfoNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            classInfoActionPanel.setFont(new Font("Arial", Font.BOLD, 13));

            infoPanel.add(classInfoNameLabel, BorderLayout.NORTH);
            infoPanel.add(classInfoActionPanel, BorderLayout.CENTER);
            infoPanel.add(classInfoActionResultPanel, BorderLayout.SOUTH);

            classInfoActionPanel.add(classInfoActionButton1);
            classInfoActionPanel.add(classInfoActionButton2);
            classInfoActionPanel.add(classInfoActionButton3);
            classInfoActionPanel.add(classInfoActionButton4);
            classInfoActionPanel.add(classInfoActionButton5);
            classInfoActionPanel.add(classInfoActionButton6);

            displayClass(frame, bodyPanel, maClasse, classInfoActionButton1, classInfoActionResultPanel);

            displayClassAverage(frame, bodyPanel, maClasse, classInfoActionButton2, classInfoActionResultPanel);
//
            displaySubjectsAverage(frame, bodyPanel, maClasse, classInfoActionButton3, classInfoActionResultPanel);
//
//            addStudent(frame, bodyPanel, maClasse, classInfoActionButton4, classInfoActionResultPanel);

//            addSubject(frame, bodyPanel, maClasse, classInfoActionButton5, classInfoActionResultPanel);
//
//            renameClass(frame, bodyPanel, maClasse, classInfoActionButton6, classInfoActionResultPanel);

            bodyPanel.add(infoPanel);
            Utils.displayFrame(frame);
        });
    }

    private static void displayClass(JFrame frame, JPanel bodyPanel, Classe maClasse, JButton button1, JPanel infoPanel) {
        button1.addActionListener(e -> {
            infoPanel.removeAll();
            var listModel = new DefaultListModel<String>();
            var classInfoActionResultList = new JList<>(listModel);
            var students = maClasse.afficher();

            classInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            classInfoActionResultList.setForeground(Color.WHITE);
            classInfoActionResultList.setBackground(new Color(33, 33, 33));

            if (students.length == 0) {
                classInfoActionResultList.setListData(new String[]{"La classe n'a pas encore d'étudiants"});
                infoPanel.add(classInfoActionResultList);
            } else {
                classInfoActionResultList.setListData(students);
                JScrollPane scrollPane = new JScrollPane(classInfoActionResultList);
                scrollPane.setPreferredSize(new Dimension(infoPanel.getWidth(), 400));
                infoPanel.add(scrollPane);
            }
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displayClassAverage(JFrame frame, JPanel bodyPanel, Classe maClasse, JButton button1, JPanel infoPanel) {
        button1.addActionListener(e -> {
            infoPanel.removeAll();
            var classInfoResultLabel = new JLabel();
            var studentsAverage = maClasse.moyenneClasse("");


            if (studentsAverage == -1) {
                classInfoResultLabel.setText("La classe n'a pas encore d'étudiants");
            } else {
                classInfoResultLabel.setText("La moyenne de la classe est de " + studentsAverage);
            }

            classInfoResultLabel.setFont(new Font("Arial", Font.BOLD, 35));
            classInfoResultLabel.setForeground(Color.WHITE);
            infoPanel.add(classInfoResultLabel);
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    private static void displaySubjectsAverage(JFrame frame, JPanel bodyPanel, Classe maClasse, JButton button1, JPanel infoPanel) {
        button1.addActionListener(e1 -> {
            infoPanel.removeAll();
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var comboBoxPanel = new JPanel();
            var comboBox = new JComboBox<String>();

            var listModel = new DefaultListModel<String>();
            var classInfoActionResultList = new JList<>(listModel);
            var matieres = new ArrayList<String>();
            try {
                String filePath = "matieres.txt";
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (var matiere : matieres) {
                comboBox.addItem(matiere);
            }

            classInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            classInfoActionResultList.setForeground(Color.WHITE);
            classInfoActionResultList.setBackground(new Color(33, 33, 33));
            classInfoActionResultList.setPreferredSize(new Dimension(infoPanel.getWidth(), 400));

            topPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 100));
            bottomPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 400));
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));

            comboBoxPanel.add(comboBox);
            topPanel.add(comboBoxPanel);


            comboBox.addActionListener(e -> {
                var subjectAverage = maClasse.moyenneClasse(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                System.out.println(subjectAverage);
                if (subjectAverage == -1) {
                    classInfoActionResultList.setListData(new String[]{"La classe n'a pas encore de notes en " + comboBox.getSelectedItem().toString() + "."});
                    bottomPanel.add(classInfoActionResultList);
                } else {
                    classInfoActionResultList.setListData(new String[]{"La moyenne de la classe en " + comboBox.getSelectedItem().toString() + " est de " + subjectAverage});
                    bottomPanel.add(classInfoActionResultList);
                }
            });
            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }
}
