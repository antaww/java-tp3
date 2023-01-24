package fr.ynov.tp3.PExo1.PClasse;

import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Classe : gère les informations sur les étudiants et les matières d'une classe
 * Cette classe permet de stocker les informations sur les étudiants et les matières d'une classe, notamment leurs noms, prénoms, notes et moyennes. Elle contient également des méthodes pour afficher les informations sur les étudiants, calculer la moyenne de la classe pour une matière donnée, récupérer un étudiant en particulier, ajouter un étudiant à la classe et sauvegarder les informations sur la classe dans un fichier.
 * Elle utilise les classes Etudiant, HashMap, FileReader et FileWriter pour gérer les informations sur les étudiants et les matières.
 *
 * @see fr.ynov.tp3.PExo1.PEtudiant.Etudiant
 * @see java.util.HashMap
 * @see java.io.FileReader
 * @see java.io.FileWriter
 */
public class Classe {
    private final Map<String, Etudiant> etudiants;
    public String nom;

    /**
     * Constructeur Classe : création d'une classe.
     *
     * @param nom nom de la classe
     */
    public Classe(final String nom) {
        this.nom = nom;
        this.etudiants = new HashMap<>();
    }

    /**
     * Méthode afficher : méthode pour afficher les étudiants de la classe.
     * Elle retourne un tableau de String contenant les noms des étudiants de la classe.
     *
     * @return tableau de String contenant les noms des étudiants de la classe
     */
    public String[] afficher() {
        final var etudiants = new String[this.etudiants.size()];
        var i = 0;
        for (final var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

    /**
     * Méthode getEtudiant : méthode pour récupérer un étudiant de la classe.
     * Elle retourne l'étudiant de la classe dont le nom est passé en paramètre.
     *
     * @param nomEtudiant nom de l'étudiant à récupérer
     * @return étudiant de la classe dont le nom est passé en paramètre
     */
    public Etudiant getEtudiant(final String nomEtudiant) {
        return etudiants.get(nomEtudiant);
    }

    /**
     * Méthode moyenneClasse : méthode pour calculer la moyenne de la classe.
     *
     * @param matiere matière pour laquelle calculer la moyenne de la classe
     * @return moyenne de la classe
     */
    public float moyenneClasse(final String matiere) {
        double somme = 0;
        var nbEtudiants = 0;
        if (matiere == null) {
            for (final var entry : etudiants.entrySet()) {
                final var etudiant = entry.getValue();
                if (etudiant.moyenne("") != -1) {
                    somme += etudiant.moyenne("");
                    nbEtudiants++;
                }
            }
        } else {
            var hasNote = false;
            for (final var entry : etudiants.entrySet()) {
                final var etudiant = entry.getValue();
                if (etudiant.moyenne(matiere) != -1) {
                    somme += etudiant.moyenne(matiere);
                    nbEtudiants++;
                    hasNote = true;
                }
            }
            if (!hasNote) {
                return -1;
            }
        }
        return (float) (somme / nbEtudiants);
    }

    /**
     * Méthode setEtudiant : méthode pour ajouter un étudiant à la classe.
     *
     * @param etudiant étudiant à ajouter à la classe
     */
    public void setEtudiant(final Etudiant etudiant) {
        final var nomEtudiant = etudiant.nom;
        final var prenomEtudiant = etudiant.prenom;
        final var cle = nomEtudiant + " " + prenomEtudiant;
        etudiants.put(cle, etudiant);
    }

    /**
     * Méthode getEtudiants : méthode pour récupérer les étudiants de la classe.
     *
     * @return tableau de String contenant les noms des étudiants de la classe
     */
    public String[] getEtudiants() {
        final var etudiants = new String[this.etudiants.size()];
        var i = 0;
        for (final var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

    /**
     * Méthode saveClasse : méthode pour sauvegarder la classe dans un fichier.
     *
     * @return String contenant le chemin du fichier
     */
    public String saveClasse() {
        final var filePath = "maClasse.txt";
        try {
            final var fileWriter = new FileWriter(filePath);
            fileWriter.write("");

            fileWriter.write(this.nom);
            fileWriter.write("\r \n");
            for (final var entry : etudiants.entrySet()) {
                final var nomEtudiant = entry.getKey();
                fileWriter.write(nomEtudiant);
                fileWriter.write("\n");
                for (final var entry2 : entry.getValue().notes.entrySet()) {
                    final var matiere = entry2.getKey();
                    for (final var entry3 : entry2.getValue().entrySet()) {
                        final var evaluation = entry3.getKey();
                        fileWriter.write(matiere + " : " + evaluation + " : ");
                        for (final var entry4 : entry3.getValue().entrySet()) {
                            final int coef = entry4.getKey();
                            final double note = entry4.getValue();
                            fileWriter.write(String.valueOf(note));
                            fileWriter.write(" (x" + coef + ")");
                            fileWriter.write("\n");
                        }
                    }
                }
                fileWriter.write("\r \n");
            }
            fileWriter.close();
        } catch (final Exception e) {
            return "Erreur lors de la sauvegarde de la classe";
        }
        return "Classe sauvegardée";
    }

    /**
     * Méthode loadClasse : méthode pour charger une classe depuis un fichier.
     *
     * @param filePath chemin du fichier à charger
     */
    public void loadClasse(final String filePath) {
        try {
            final var fileReader = new FileReader(filePath);
            final var bufferedReader = new BufferedReader(fileReader);
            var line = bufferedReader.readLine();
            this.nom = line;
            while ((line = bufferedReader.readLine()) != null && !line.equals("\r \n")) {
                final var etudiant = line.split(" ");
                if (etudiant.length == 2) {
                    final var nomEtudiant = etudiant[0];
                    final var prenomEtudiant = etudiant[1];
                    final var etudiant1 = new Etudiant(nomEtudiant, prenomEtudiant);
                    while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
                        final var note = line.split(" : ");
                        final var matiere = note[0];
                        final var evaluation = note[1];
                        final var note2 = note[2].split(" ");
                        final var noteEtudiant = Double.parseDouble(note2[0]);
                        final var coef = Integer.parseInt(note2[1].substring(2, 3));
                        etudiant1.setNote(matiere, evaluation, coef, noteEtudiant);
                    }
                    this.setEtudiant(etudiant1);
                }
            }
            bufferedReader.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}