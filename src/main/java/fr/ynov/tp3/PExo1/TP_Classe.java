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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.nio.file.Paths;
import java.util.List;

public class TP_Classe {

    static Classe maClasse;

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

        maClasse = new Classe("B2 - Linux/Réseaux");
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
        var classButton = new JButton(maClasse.nom);
        var studentLabel = new JLabel("Sélectionnez un étudiant pour afficher ses informations");
        var classLabel = new JLabel("Cliquez sur la classe pour afficher ses informations");

        listScrollPanel.setPreferredSize(new Dimension(200, 30));
        listScrollPanel.setFont(new Font("Arial", Font.BOLD, 15));
        classButton.setPreferredSize(new Dimension(200, 50));
        classButton.setFont(new Font("Arial", Font.BOLD, 16));
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
        buttonsPanel.add(classButton, gbc);

        bodyPanel.add(buttonsPanel);

        var infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(200, 500));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));


        displayStudentInfo(frame, bodyPanel, listScrollPanel, infoPanel);

        displayClassInfo(frame, bodyPanel, classButton, listScrollPanel, infoPanel);

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


    private static void displayStudentInfo(JFrame frame, JPanel bodyPanel, JComboBox<String> listScrollPane, JPanel infoPanel) {
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

    private static void displayClassInfo(JFrame frame, JPanel bodyPanel, JButton button, JComboBox<String> listScrollPane, JPanel infoPanel) {
        button.addActionListener(e -> {
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
            var classInfoActionButton7 = new JButton("Sauvegarder la classe");
            var classInfoActionButton8 = new JButton("Charger une classe");

            infoPanel.setLayout(new BorderLayout());

            classInfoNameLabel.setPreferredSize(new Dimension(bodyPanel.getWidth(), 50));
            classInfoNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classInfoNameLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            classInfoActionPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            classInfoActionResultPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            classInfoNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            infoPanel.add(classInfoNameLabel, BorderLayout.NORTH);
            infoPanel.add(classInfoActionPanel, BorderLayout.CENTER);
            infoPanel.add(classInfoActionResultPanel, BorderLayout.SOUTH);

            classInfoActionButton7.setPreferredSize(new Dimension(300, 30));
            classInfoActionButton7.setFont(new Font("Arial", Font.BOLD, 15));
            classInfoActionButton8.setPreferredSize(new Dimension(300, 30));
            classInfoActionButton8.setFont(new Font("Arial", Font.BOLD, 15));


            classInfoActionPanel.add(classInfoActionButton1);
            classInfoActionPanel.add(classInfoActionButton2);
            classInfoActionPanel.add(classInfoActionButton3);
            classInfoActionPanel.add(classInfoActionButton4);
            classInfoActionPanel.add(classInfoActionButton5);
            classInfoActionPanel.add(classInfoActionButton6);
            classInfoActionPanel.add(classInfoActionButton7);
            classInfoActionPanel.add(classInfoActionButton8);

            displayClass(frame, bodyPanel, classInfoActionButton1, classInfoActionResultPanel);

            displayClassAverage(frame, bodyPanel, classInfoActionButton2, classInfoActionResultPanel);

            displaySubjectsAverage(frame, bodyPanel, classInfoActionButton3, classInfoActionResultPanel);

            addStudent(frame, classInfoActionButton4, listScrollPane, classInfoActionResultPanel);

            addSubject(frame, classInfoActionButton5, classInfoActionResultPanel);

            renameClass(frame, classInfoActionButton6, button, classInfoNameLabel, classInfoActionResultPanel);

            saveClass(frame, classInfoActionButton7);

            loadClass(frame, classInfoActionButton8, button, classInfoNameLabel, listScrollPane);

            bodyPanel.add(infoPanel);
            Utils.displayFrame(frame);
        });
    }

    private static void displayClass(JFrame frame, JPanel bodyPanel, JButton button, JPanel infoPanel) {
        button.addActionListener(e -> {
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

    private static void displayClassAverage(JFrame frame, JPanel bodyPanel, JButton button, JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            var classInfoResultLabel = new JLabel();
            var studentsAverage = maClasse.moyenneClasse("");


            if (studentsAverage == -1) {
                classInfoResultLabel.setText("La classe n'a pas encore de notes");
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

    private static void displaySubjectsAverage(JFrame frame, JPanel bodyPanel, JButton button, JPanel infoPanel) {
        button.addActionListener(e1 -> {
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

    private static void addStudent(JFrame frame, JButton button, JComboBox<String> listScrollPane, JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var studentFirstNamePanel = new JPanel();
            var studentFirstNameLabel = new JLabel("Nom de l'étudiant : ");
            var studentFirstNameTextField = new JTextField();
            var studentLastNamePanel = new JPanel();
            var studentLastNameLabel = new JLabel("Prénom de l'étudiant : ");
            var studentLastNameTextField = new JTextField();
            var addStudentButton = new JButton("Ajouter l'étudiant");

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
                var studentFirstName = studentFirstNameTextField.getText();
                var studentLastName = studentLastNameTextField.getText();
                if (studentFirstName.equals("") || studentLastName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {
                    var newStudent = new Etudiant(studentFirstName, studentLastName);
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

    private static void addSubject(JFrame frame, JButton button, JPanel infoPanel) {
        button.addActionListener(e -> {
            infoPanel.removeAll();
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var subjectNamePanel = new JPanel();
            var subjectNameLabel = new JLabel("Nom de la matière : ");
            var subjectNameTextField = new JTextField();
            var addSubjectButton = new JButton("Ajouter la matière");

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
                var subjectName = subjectNameTextField.getText();
                if (subjectName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {

                    try {
                        var file = new File("matieres.txt");
                        String filePath = "matieres.txt";
                        List<String> lines = Files.readAllLines(Paths.get(filePath));
                        var matieres = new ArrayList<>(lines);
                        if (matieres.contains(subjectName)) {
                            JOptionPane.showMessageDialog(frame, "La matière existe déjà");
                            return;
                        }
                        var fileWriter = new FileWriter(file, true);
                        fileWriter.write(subjectName + "\n");
                        fileWriter.close();
                    } catch (IOException ioException) {
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

    private static void renameClass(JFrame frame, JButton button, JButton classButton, JLabel classInfoNameLabel, JPanel infoPanel) {
        button.addActionListener(e -> {
            System.out.println(maClasse.nom);
            infoPanel.removeAll();
            var topPanel = new JPanel();
            var bottomPanel = new JPanel();
            var classNamePanel = new JPanel();
            var classNameLabel = new JLabel("Nom de la classe : ");
            var classNameTextField = new JTextField();
            var renameClassButton = new JButton("Renommer la classe");

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
                var className = classNameTextField.getText();
                if (className.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs");
                } else {
                    maClasse.nom = className;
                    classInfoNameLabel.setText(maClasse.nom);
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

    private static void saveClass(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            var message = maClasse.saveClasse();
            JOptionPane.showMessageDialog(frame, message);
        });
    }

    private static void loadClass(JFrame frame, JButton button, JButton classButton, JLabel classInfoNameLabel, JComboBox<String> listScrollPanel) {
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                maClasse.loadClasse(selectedFile.getName());

                classInfoNameLabel.setText(maClasse.nom);
                classButton.setText(maClasse.nom);

                var studentList = new ArrayList<>(Arrays.asList(maClasse.getEtudiants()));
                studentList.sort(String::compareToIgnoreCase);
                var comboBoxModel = new DefaultComboBoxModel<String>();
                for (var student : studentList) {
                    comboBoxModel.addElement(student);
                }
                listScrollPanel.setModel(comboBoxModel);
            }
        });
    }
}
