package fr.ynov.tp3.PExo1;

import fr.ynov.tp3.PExo1.PClasse.Classe;
import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;
import fr.ynov.tp3.PUtils.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe TP_Classe : classe principale de l'exercice 1 du TP3.
 * Cette classe permet de créer une classe et d'ajouter des étudiants à cette classe. Elle contient également des méthodes pour afficher les étudiants de la classe, calculer la moyenne de la classe pour une matière donnée, récupérer un étudiant en particulier, ajouter un étudiant à la classe et sauvegarder les informations sur la classe dans un fichier.
 * Elle utilise les classes Classe, Etudiant, FileWriter, File, JFrame, JPanel, JLabel, JComboBox, ArrayList, Arrays, Utils et Files pour gérer les informations sur les étudiants et les matières.
 *
 * @see fr.ynov.tp3.PExo1.PClasse.Classe
 * @see fr.ynov.tp3.PExo1.PEtudiant.Etudiant
 * @see java.io.FileWriter
 * @see java.io.File
 * @see javax.swing.JFrame
 * @see javax.swing.JPanel
 * @see javax.swing.JLabel
 * @see javax.swing.JComboBox
 * @see java.util.ArrayList
 * @see java.util.Arrays
 * @see fr.ynov.tp3.PUtils.Utils
 * @see java.nio.file.Files
 */
public class TP_Classe {
    static Classe maClasse;

    /**
     * Méthode main : méthode principale de la classe TP_Classe.
     * Cette méthode permet de créer une classe et d'ajouter des étudiants à cette classe. Elle contient également des méthodes pour afficher les étudiants de la classe, calculer la moyenne de la classe pour une matière donnée, récupérer un étudiant en particulier, ajouter un étudiant à la classe et sauvegarder les informations sur la classe dans un fichier.
     * Elle utilise les classes Classe, Etudiant, FileWriter, File, JFrame, JPanel, JLabel, JComboBox, ArrayList, Arrays, Utils et Files pour gérer les informations sur les étudiants et les matières.
     *
     * @param frame fenêtre principale de l'application
     * @see fr.ynov.tp3.PExo1.PClasse.Classe
     * @see fr.ynov.tp3.PExo1.PEtudiant.Etudiant
     * @see java.io.FileWriter
     * @see java.io.File
     * @see javax.swing.JFrame
     * @see javax.swing.JPanel
     * @see javax.swing.JLabel
     * @see javax.swing.JComboBox
     * @see java.util.ArrayList
     * @see java.util.Arrays
     * @see fr.ynov.tp3.PUtils.Utils
     * @see java.nio.file.Files
     */
    public static void main(final JFrame frame) {
        final var bodyPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        Utils.cleanBodyPanel(bodyPanel);
        final var titleLabel = (JLabel) ((JPanel) ((JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0);
        titleLabel.setText("Exercice 1 - TP Classe & Étudiant");

        maClasse = new Classe("");
        maClasse.loadClasse("maClasse.txt");

        final var listScrollPanel = new JComboBox<String>();
        final var studentList = new ArrayList<>(Arrays.asList(maClasse.getEtudiants()));
        studentList.sort(String::compareToIgnoreCase);
        for (final var student : studentList) {
            listScrollPanel.addItem(student);
        }

        final var buttonsPanel = new JPanel();
        final var classButton = new JButton(maClasse.nom);
        final var studentLabel = new JLabel("Sélectionnez un étudiant pour afficher ses informations");
        final var classLabel = new JLabel("Cliquez sur la classe pour afficher ses informations");

        listScrollPanel.setPreferredSize(new Dimension(200, 30));
        listScrollPanel.setFont(new Font("Arial", Font.BOLD, 15));
        classButton.setPreferredSize(new Dimension(200, 50));
        classButton.setFont(new Font("Arial", Font.BOLD, 16));
        studentLabel.setFont(new Font("Arial", Font.BOLD, 13));
        classLabel.setFont(new Font("Arial", Font.BOLD, 13));

        buttonsPanel.setLayout(new GridBagLayout());
        final var gbc = new GridBagConstraints();
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
        buttonsPanel.add(classButton, gbc);
        bodyPanel.add(buttonsPanel);

        final var infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(200, 500));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        displayStudentInfo(frame, bodyPanel, listScrollPanel, infoPanel);
        displayClassInfo(frame, bodyPanel, classButton, listScrollPanel, infoPanel);
        Utils.displayFrame(frame);
    }

    /**
     * Méthode displayStudentInfo : méthode qui permet d'afficher les informations sur un étudiant.
     * Cette méthode permet d'afficher les informations sur un étudiant sélectionné dans la liste déroulante. Elle contient également des méthodes pour afficher la moyenne de l'étudiant, afficher toutes les notes de l'étudiant, afficher les notes d'une matière de l'étudiant et ajouter une note à l'étudiant.
     * Elle utilise les classes JFrame, JPanel, JComboBox, ArrayList, Arrays, Utils et Files pour gérer les informations sur les étudiants et les matières.
     *
     * @param frame Fenêtre de l'application.
     * @param bodyPanel Panneau principal de l'application.
     * @param listScrollPane Liste déroulante des étudiants.
     * @param infoPanel Panneau d'affichage des informations sur l'étudiant.
     * @see javax.swing.JFrame
     * @see javax.swing.JPanel
     * @see javax.swing.JComboBox
     * @see java.util.ArrayList
     * @see java.util.Arrays
     * @see fr.ynov.tp3.PUtils.Utils
     * @see java.nio.file.Files
     */
    private static void displayStudentInfo(final JFrame frame, final JPanel bodyPanel, final JComboBox<String> listScrollPane, final JPanel infoPanel) {
        listScrollPane.addActionListener(e -> {
            infoPanel.removeAll();
            final var selectedStudent = Objects.requireNonNull(listScrollPane.getSelectedItem()).toString();
            final var student = maClasse.getEtudiant(selectedStudent);

            final var studentInfoActionPanel = new JPanel();
            final var studentInfoActionButton1 = new JButton("Moyenne");
            final var studentInfoActionButton2 = new JButton("Afficher toutes les notes");
            final var studentInfoActionButton3 = new JButton("Afficher les notes d'une matière");
            final var studentInfoActionButton4 = new JButton("Ajouter une note");
            final var studentInfoActionResultPanel = new JPanel();

            infoPanel.setLayout(new BorderLayout());
            studentInfoActionPanel.setFont(new Font("Arial", Font.BOLD, 20));
            infoPanel.add(studentInfoActionPanel, BorderLayout.CENTER);
            infoPanel.add(studentInfoActionResultPanel, BorderLayout.SOUTH);

            studentInfoActionPanel.add(studentInfoActionButton1);
            studentInfoActionPanel.add(studentInfoActionButton2);
            studentInfoActionPanel.add(studentInfoActionButton3);
            studentInfoActionPanel.add(studentInfoActionButton4);

            displayAverage(frame, bodyPanel, student, studentInfoActionButton1, studentInfoActionResultPanel);
            displayAllGrades(frame, bodyPanel, student, studentInfoActionButton2, studentInfoActionResultPanel);
            displayGradesBySubjects(frame, bodyPanel, student, studentInfoActionButton3, studentInfoActionResultPanel);
            addGrade(frame, bodyPanel, student, studentInfoActionButton4, studentInfoActionResultPanel);
            bodyPanel.add(infoPanel);
            Utils.displayFrame(frame);
        });
    }

    /**
     * Méthode addGrade : méthode qui permet d'ajouter une note à un étudiant.
     * Cette méthode permet d'ajouter une note à un étudiant sélectionné dans la liste déroulante. Elle utilise les classes JFrame, JPanel, JComboBox, JTextField, JButton, ArrayList, Arrays, Utils et Files pour gérer les informations sur les étudiants et les matières.
     *
     * @param frame Fenêtre de l'application.
     * @param bodyPanel Panneau principal de l'application.
     * @param student Etudiant sélectionné dans la liste déroulante.
     * @param studentInfoActionButton4 Bouton "Ajouter une note".
     * @param studentInfoActionResultPanel Panneau d'affichage des résultats de l'action.
     * @see javax.swing.JFrame
     * @see javax.swing.JPanel
     * @see javax.swing.JComboBox
     * @see javax.swing.JTextField
     * @see javax.swing.JButton
     * @see java.util.ArrayList
     * @see java.util.Arrays
     * @see fr.ynov.tp3.PUtils.Utils
     * @see java.nio.file.Files
     */
    private static void addGrade(final JFrame frame, final JPanel bodyPanel, final Etudiant student, final JButton studentInfoActionButton4, final JPanel studentInfoActionResultPanel) {
        studentInfoActionButton4.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            final var studentInfoActionResultComboBox = new JComboBox<>();
            final var studentInfoActionResultTextField2 = new JTextField();
            final var studentInfoActionResultTextField3 = new JTextField();
            final var studentInfoActionResultTextField4 = new JTextField();
            final var studentInfoActionResultButton = new JButton("Ajouter la note");
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            var matieres = new ArrayList<String>();
            try {
                final var filePath = "matieres.txt";
                final var lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            for (final var matiere : matieres) {
                studentInfoActionResultComboBox.addItem(matiere);
            }

            studentInfoActionResultTextField2.setBorder(BorderFactory.createTitledBorder(null, "Examen", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField3.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultTextField4.setBorder(BorderFactory.createTitledBorder(null, "Coefficient (optionnel)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
            studentInfoActionResultComboBox.setPreferredSize(new Dimension(300, 35));
            studentInfoActionResultTextField2.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultTextField3.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultTextField4.setPreferredSize(new Dimension(300, 50));
            studentInfoActionResultButton.setPreferredSize(new Dimension(250, 50));
            topPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 250));
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));

            studentInfoActionResultComboBox.setFont(new Font("Arial", Font.BOLD, 20));
            studentInfoActionResultTextField2.setFont(new Font("Arial", Font.BOLD, 20));
            studentInfoActionResultTextField3.setFont(new Font("Arial", Font.BOLD, 20));
            studentInfoActionResultTextField4.setFont(new Font("Arial", Font.BOLD, 20));
            studentInfoActionResultButton.setFont(new Font("Arial", Font.BOLD, 25));

            topPanel.add(studentInfoActionResultComboBox);
            topPanel.add(studentInfoActionResultTextField2);
            topPanel.add(studentInfoActionResultTextField3);
            topPanel.add(studentInfoActionResultTextField4);
            bottomPanel.add(studentInfoActionResultButton);
            studentInfoActionResultPanel.add(topPanel);
            studentInfoActionResultPanel.add(bottomPanel);

            studentInfoActionResultButton.addActionListener(e2 -> {
                final var subject = Objects.requireNonNull(studentInfoActionResultComboBox.getSelectedItem()).toString();
                final var exam = studentInfoActionResultTextField2.getText();
                final var grade = studentInfoActionResultTextField3.getText();
                final var coefficient = studentInfoActionResultTextField4.getText();

                if (subject.isEmpty() || exam.isEmpty() || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Double.parseDouble(grade);
                } catch (final NumberFormatException e) {
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
                    } catch (final NumberFormatException e) {
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

    /**
     * Méthode displayGradesBySubjects : Affiche les notes d'un étudiant par matière
     * Cette méthode affiche les notes d'un étudiant par matière
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param student Etudiant
     * @param studentInfoActionButton3 Bouton d'affichage des notes par matière
     * @param infoPanel Panel d'affichage des informations
     */
    private static void displayGradesBySubjects(final JFrame frame, final JPanel bodyPanel, final Etudiant student, final JButton studentInfoActionButton3, final JPanel infoPanel) {
        studentInfoActionButton3.addActionListener(e1 -> {
            infoPanel.removeAll();
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            final var comboBoxPanel = new JPanel();
            final var comboBox = new JComboBox<>();

            final var listModel = new DefaultListModel<String>();
            final var studentInfoActionResultList = new JList<>(listModel);
            var matieres = new ArrayList<String>();
            try {
                final var filePath = "matieres.txt";
                final var lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            for (final var matiere : matieres) {
                comboBox.addItem(matiere);
            }

            studentInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultList.setForeground(Color.WHITE);
            studentInfoActionResultList.setBackground(new Color(33, 33, 33));
            studentInfoActionResultList.setPreferredSize(new Dimension(infoPanel.getWidth(), 350));

            comboBox.setPreferredSize(new Dimension(300, 35));
            comboBox.setFont(new Font("Arial", Font.BOLD, 20));

            topPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));

            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);

            comboBox.addActionListener(e2 -> {
                listModel.removeAllElements();
                final var subject = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                final var notesOfSubject = student.afficherNote(subject);
                if (notesOfSubject == null) {
                    listModel.addElement(student.prenom + " " + student.nom + " n'a pas de note pour la matière " + subject);
                } else {
                    for (final var note : notesOfSubject) {
                        listModel.addElement(note);
                    }
                }
            });
            comboBoxPanel.add(comboBox);
            topPanel.add(comboBoxPanel);
            bottomPanel.add(studentInfoActionResultList);

            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode displayAllGrades : Affiche toutes les notes d'un étudiant
     * Cette méthode affiche toutes les notes d'un étudiant
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param student Etudiant
     * @param studentInfoActionButton2 Bouton d'affichage de toutes les notes
     * @param studentInfoActionResultPanel Panel d'affichage des informations
     */
    private static void displayAllGrades(final JFrame frame, final JPanel bodyPanel, final Etudiant student, final JButton studentInfoActionButton2, final JPanel studentInfoActionResultPanel) {
        studentInfoActionButton2.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            final var listModel = new DefaultListModel<String>();
            final var studentInfoActionResultList = new JList<>(listModel);
            final var notes = student.afficherNote();
            Arrays.sort(notes);

            studentInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultList.setForeground(Color.WHITE);
            studentInfoActionResultList.setBackground(new Color(33, 33, 33));

            if (notes.length == 0) {
                studentInfoActionResultList.setListData(new String[]{student.prenom + " " + student.nom + " n'a pas encore de note"});
                studentInfoActionResultPanel.add(studentInfoActionResultList);
            } else {
                studentInfoActionResultList.setListData(notes);
                final var scrollPane = new JScrollPane(studentInfoActionResultList);
                scrollPane.setPreferredSize(new Dimension(studentInfoActionResultPanel.getWidth(), 400));
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                studentInfoActionResultPanel.add(scrollPane);
            }
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode displayAverage : Affiche la moyenne d'un étudiant
     * Cette méthode affiche la moyenne d'un étudiant
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param student Etudiant
     * @param studentInfoActionButton1 Bouton d'affichage de la moyenne
     * @param studentInfoActionResultPanel Panel d'affichage des informations
     */
    private static void displayAverage(final JFrame frame, final JPanel bodyPanel, final Etudiant student, final JButton studentInfoActionButton1, final JPanel studentInfoActionResultPanel) {
        studentInfoActionButton1.addActionListener(e1 -> {
            studentInfoActionResultPanel.removeAll();
            final var studentInfoActionResultLabel = new JLabel();
            final var moyenne = student.moyenne("");

            if (moyenne == -1) {
                studentInfoActionResultLabel.setText(student.prenom + " " + student.nom + " n'a pas encore de notes");
            } else {
                studentInfoActionResultLabel.setText("La moyenne de " + student.prenom + " " + student.nom + " est de " + moyenne);
            }

            studentInfoActionResultLabel.setFont(new Font("Arial", Font.BOLD, 25));
            studentInfoActionResultLabel.setForeground(Color.WHITE);
            studentInfoActionResultPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 300));
            studentInfoActionResultPanel.add(studentInfoActionResultLabel);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode displayClassInfo : Affiche les informations d'une classe
     * Cette méthode affiche les informations d'une classe
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param button Bouton d'affichage des informations
     * @param listScrollPane Liste déroulante des classes
     * @param infoPanel Panel d'affichage des informations
     */
    private static void displayClassInfo(final JFrame frame, final JPanel bodyPanel, final JButton button, final JComboBox<String> listScrollPane, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var classInfoActionPanel = new JPanel();
            final var classInfoActionResultPanel = new JPanel();
            final var classInfoActionButton1 = new JButton("Afficher les étudiants");
            final var classInfoActionButton2 = new JButton("Afficher les moyennes des étudiants");
            final var classInfoActionButton3 = new JButton("Afficher les moyennes des matières");
            final var classInfoActionButton4 = new JButton("Ajouter un étudiant");
            final var classInfoActionButton5 = new JButton("Ajouter une matière");
            final var classInfoActionButton6 = new JButton("Renommer la classe");
            final var classInfoActionButton7 = new JButton("Sauvegarder la classe");
            final var classInfoActionButton8 = new JButton("Charger une classe");
            final var bottomButtonPanel = new JPanel();

            infoPanel.setLayout(new BorderLayout());

            infoPanel.add(classInfoActionPanel, BorderLayout.CENTER);
            infoPanel.add(classInfoActionResultPanel, BorderLayout.SOUTH);

            bottomButtonPanel.add(classInfoActionButton7);
            bottomButtonPanel.add(classInfoActionButton8);
            bottomButtonPanel.setPreferredSize(new Dimension(600, 50));

            classInfoActionPanel.add(classInfoActionButton1);
            classInfoActionPanel.add(classInfoActionButton2);
            classInfoActionPanel.add(classInfoActionButton3);
            classInfoActionPanel.add(classInfoActionButton4);
            classInfoActionPanel.add(classInfoActionButton5);
            classInfoActionPanel.add(classInfoActionButton6);
            classInfoActionPanel.add(bottomButtonPanel);

            displayClassStudents(frame, bodyPanel, classInfoActionButton1, classInfoActionResultPanel);
            displayClassAverage(frame, bodyPanel, classInfoActionButton2, classInfoActionResultPanel);
            displayClassAverageBySubject(frame, bodyPanel, classInfoActionButton3, classInfoActionResultPanel);
            addStudent(frame, classInfoActionButton4, listScrollPane, classInfoActionResultPanel);
            addSubject(frame, classInfoActionButton5, classInfoActionResultPanel);
            renameClass(frame, classInfoActionButton6, button, classInfoActionResultPanel);
            saveClass(frame, classInfoActionButton7);
            loadClass(frame, classInfoActionButton8, button, listScrollPane);

            bodyPanel.add(infoPanel);
            Utils.displayFrame(frame);
        });
    }

    /**
     * Méthode displayClassStudents : Affiche les étudiants d'une classe
     * Cette méthode affiche les étudiants d'une classe
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param button Bouton d'affichage des étudiants
     * @param infoPanel Panel d'affichage des informations
     */
    private static void displayClassStudents(final JFrame frame, final JPanel bodyPanel, final JButton button, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var listModel = new DefaultListModel<String>();
            final var classInfoActionResultList = new JList<>(listModel);
            final var students = maClasse.afficher();

            classInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            classInfoActionResultList.setForeground(Color.WHITE);
            classInfoActionResultList.setBackground(new Color(33, 33, 33));

            if (students.length == 0) {
                classInfoActionResultList.setListData(new String[]{"La classe n'a pas encore d'étudiants"});
                infoPanel.add(classInfoActionResultList);
            } else {
                classInfoActionResultList.setListData(students);
                final var scrollPane = new JScrollPane(classInfoActionResultList);
                scrollPane.setPreferredSize(new Dimension(infoPanel.getWidth(), 400));
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                infoPanel.add(scrollPane);
            }
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode displayClassAverage : Affiche la moyenne des étudiants d'une classe
     * Cette méthode affiche la moyenne des étudiants d'une classe
     *
     * @param frame Fenêtre principale
     * @param bodyPanel Panel principal
     * @param button Bouton d'affichage des moyennes
     * @param infoPanel Panel d'affichage des informations
     */
    private static void displayClassAverage(final JFrame frame, final JPanel bodyPanel, final JButton button, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var classInfoResultLabel = new JLabel();
            final var studentsAverage = maClasse.moyenneClasse("");

            if (studentsAverage == -1) {
                classInfoResultLabel.setText("La classe n'a pas encore de notes");
            } else {
                classInfoResultLabel.setText("La moyenne de la classe est de " + studentsAverage);
            }

            classInfoResultLabel.setFont(new Font("Arial", Font.BOLD, 25));
            classInfoResultLabel.setForeground(Color.WHITE);
            infoPanel.add(classInfoResultLabel);
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode displayClassAverageBySubject : affiche la moyenne de la classe par matière.
     *
     * @param frame     la fenêtre principale
     * @param bodyPanel le panel principal
     * @param button    le bouton cliqué
     * @param infoPanel le panel d'information
     */
    private static void displayClassAverageBySubject(final JFrame frame, final JPanel bodyPanel, final JButton button, final JPanel infoPanel) {
        button.addActionListener(e1 -> {
            infoPanel.removeAll();
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            final var comboBoxPanel = new JPanel();
            final var comboBox = new JComboBox<String>();

            final var listModel = new DefaultListModel<String>();
            final var classInfoActionResultList = new JList<>(listModel);
            var matieres = new ArrayList<String>();
            try {
                final var filePath = "matieres.txt";
                final var lines = Files.readAllLines(Paths.get(filePath));
                matieres = new ArrayList<>(lines);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            for (final var matiere : matieres) {
                comboBox.addItem(matiere);
            }

            classInfoActionResultList.setFont(new Font("Arial", Font.BOLD, 25));
            classInfoActionResultList.setForeground(Color.WHITE);
            classInfoActionResultList.setBackground(new Color(33, 33, 33));
            classInfoActionResultList.setPreferredSize(new Dimension(infoPanel.getWidth(), 350));

            comboBox.setPreferredSize(new Dimension(200, 35));
            comboBox.setFont(new Font("Arial", Font.BOLD, 20));


            topPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 100));
            bottomPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 400));
            infoPanel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 400));


            comboBox.addActionListener(e -> {
                final var subjectAverage = maClasse.moyenneClasse(Objects.requireNonNull(comboBox.getSelectedItem()).toString());

                if (subjectAverage == -1) {
                    classInfoActionResultList.setListData(new String[]{"La classe n'a pas encore de notes en " + comboBox.getSelectedItem().toString() + "."});
                } else {
                    classInfoActionResultList.setListData(new String[]{"La moyenne de la classe en " + comboBox.getSelectedItem().toString() + " est de " + subjectAverage});
                }
            });
            comboBoxPanel.add(comboBox);
            topPanel.add(comboBoxPanel);
            bottomPanel.add(classInfoActionResultList);

            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode addStudent : permet d'ajouter un étudiant à la classe.
     *
     * @param frame          la fenêtre principale
     * @param button         le bouton qui permet d'ajouter un étudiant
     * @param listScrollPane la liste des étudiants de la classe
     * @param infoPanel      le panel d'information
     */
    private static void addStudent(final JFrame frame, final JButton button, final JComboBox<String> listScrollPane, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            final var studentFirstNamePanel = new JPanel();
            final var studentFirstNameLabel = new JLabel("Nom de l'étudiant : ");
            final var studentFirstNameTextField = new JTextField();
            final var studentLastNamePanel = new JPanel();
            final var studentLastNameLabel = new JLabel("Prénom de l'étudiant : ");
            final var studentLastNameTextField = new JTextField();
            final var addStudentButton = new JButton("Ajouter l'étudiant");

            studentFirstNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
            studentFirstNameLabel.setForeground(Color.WHITE);
            studentFirstNameTextField.setPreferredSize(new Dimension(200, 30));
            studentFirstNamePanel.add(studentFirstNameLabel);
            studentFirstNamePanel.add(studentFirstNameTextField);

            studentLastNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
            studentLastNameLabel.setForeground(Color.WHITE);
            studentLastNameTextField.setPreferredSize(new Dimension(200, 30));
            studentLastNamePanel.add(studentLastNameLabel);
            studentLastNamePanel.add(studentLastNameTextField);

            topPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 200));
            bottomPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 300));

            topPanel.add(studentFirstNamePanel);
            topPanel.add(studentLastNamePanel);
            bottomPanel.add(addStudentButton);

            addStudentButton.addActionListener(e1 -> {
                final var studentFirstName = studentFirstNameTextField.getText();
                final var studentLastName = studentLastNameTextField.getText();
                if (studentFirstName.equals("") || studentLastName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {
                    final var newStudent = new Etudiant(studentFirstName, studentLastName);
                    maClasse.setEtudiant(newStudent);
                    JOptionPane.showMessageDialog(frame, "L'étudiant a bien été ajouté");
                    listScrollPane.addItem(newStudent.nom + " " + newStudent.prenom);
                }
            });

            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode addSubject : permet d'ajouter une matière.
     * Sauvegarde la matière dans un fichier texte.
     *
     * @param frame     la fenêtre
     * @param button    le bouton d'ajout de matière
     * @param infoPanel le panel d'information
     */
    private static void addSubject(final JFrame frame, final JButton button, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            final var subjectNamePanel = new JPanel();
            final var subjectNameLabel = new JLabel("Nom de la matière : ");
            final var subjectNameTextField = new JTextField();
            final var addSubjectButton = new JButton("Ajouter la matière");

            subjectNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
            subjectNameLabel.setForeground(Color.WHITE);
            subjectNameTextField.setPreferredSize(new Dimension(200, 30));
            subjectNamePanel.add(subjectNameLabel);
            subjectNamePanel.add(subjectNameTextField);

            topPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 200));
            bottomPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 300));

            topPanel.add(subjectNamePanel);
            bottomPanel.add(addSubjectButton);

            addSubjectButton.addActionListener(e1 -> {
                final var subjectName = subjectNameTextField.getText();
                if (subjectName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {

                    try {
                        final var file = new File("matieres.txt");
                        final var filePath = "matieres.txt";
                        final var lines = Files.readAllLines(Paths.get(filePath));
                        final var matieres = new ArrayList<>(lines);
                        if (matieres.contains(subjectName)) {
                            JOptionPane.showMessageDialog(frame, "La matière existe déjà");
                            return;
                        }
                        final var fileWriter = new FileWriter(file, true);
                        fileWriter.write(subjectName + "\n");
                        fileWriter.close();
                    } catch (final IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame, "La matière a bien été ajoutée");
                }
            });

            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Méthode renameClass : renomme la classe.
     *
     * @param frame       la fenêtre
     * @param button      le bouton qui permet de renommer la classe
     * @param classButton le bouton qui affiche le nom de la classe
     * @param infoPanel   le panel d'information
     */
    private static void renameClass(final JFrame frame, final JButton button, final JButton classButton, final JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            final var topPanel = new JPanel();
            final var bottomPanel = new JPanel();
            final var classNamePanel = new JPanel();
            final var classNameLabel = new JLabel("Nom de la classe : ");
            final var classNameTextField = new JTextField();
            final var renameClassButton = new JButton("Renommer la classe");

            classNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
            classNameLabel.setForeground(Color.WHITE);
            classNameTextField.setPreferredSize(new Dimension(200, 30));
            classNamePanel.add(classNameLabel);
            classNamePanel.add(classNameTextField);

            topPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 200));
            bottomPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 300));

            topPanel.add(classNamePanel);
            bottomPanel.add(renameClassButton);

            renameClassButton.addActionListener(e1 -> {
                final var className = classNameTextField.getText();
                if (className.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {
                    maClasse.nom = className;
                    classButton.setText(maClasse.nom);
                    JOptionPane.showMessageDialog(frame, "La classe a bien été renommée");
                }
            });
            infoPanel.add(topPanel);
            infoPanel.add(bottomPanel);
            Utils.displayFrame(frame);
            frame.revalidate();
            frame.repaint();
        });

    }

    /**
     * Méthode saveClass : permet de sauvegarder une classe.
     * Sauvegarde les étudiants et le nom de la classe dans un fichier.
     *
     * @param frame  Frame sur laquelle on va sauvegarder la classe
     * @param button Bouton qui permet de sauvegarder la classe
     */
    private static void saveClass(final JFrame frame, final JButton button) {
        button.addActionListener(e -> {
            final var message = maClasse.saveClasse();
            JOptionPane.showMessageDialog(frame, message);
        });
    }

    /**
     * Méthode loadClass : permet de charger une classe.
     * Charge les étudiants et le nom de la classe depuis un fichier.
     *
     * @param frame           Frame sur laquelle on va charger la classe
     * @param button          Bouton qui permet de charger la classe
     * @param classButton     Bouton qui affiche le nom de la classe
     * @param listScrollPanel Panel qui affiche la liste des étudiants
     */
    private static void loadClass(final JFrame frame, final JButton button, final JButton classButton, final JComboBox<String> listScrollPanel) {
        button.addActionListener(e -> {
            final var fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            final var result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                final var selectedFile = fileChooser.getSelectedFile();
                maClasse.loadClasse(selectedFile.getName());

                classButton.setText(maClasse.nom);

                final var studentList = new ArrayList<>(Arrays.asList(maClasse.getEtudiants()));
                studentList.sort(String::compareToIgnoreCase);
                final var comboBoxModel = new DefaultComboBoxModel<String>();
                for (final var student : studentList) {
                    comboBoxModel.addElement(student);
                }
                listScrollPanel.setModel(comboBoxModel);
            }
        });
    }
}
