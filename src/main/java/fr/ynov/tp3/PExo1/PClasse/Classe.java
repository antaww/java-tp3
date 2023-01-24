package fr.ynov.tp3.PExo1.PClasse;

import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Classe {
    private final Map<String, Etudiant> etudiants;
    public String nom;

    public Classe(final String nom) {
        this.nom = nom;
        this.etudiants = new HashMap<>();
    }


//    public void afficher() {
//        System.out.println("Voici la liste des étudiants de la classe " + nom + " :");
//        for (var entry : etudiants.entrySet()) {
//            var nomEtudiant = entry.getKey();
//            System.out.println(" - " + nomEtudiant);
//        }
//    }

    public String[] afficher() {
        final var etudiants = new String[this.etudiants.size()];
        var i = 0;
        for (final var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

    public Etudiant getEtudiant(final String nomEtudiant) {
        return etudiants.get(nomEtudiant);
    }

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

    public void setEtudiant(final Etudiant etudiant) {
        final var nomEtudiant = etudiant.nom;
        final var prenomEtudiant = etudiant.prenom;
        final var cle = nomEtudiant + " " + prenomEtudiant;
        etudiants.put(cle, etudiant);
    }

    public String[] getEtudiants() {
        final var etudiants = new String[this.etudiants.size()];
        var i = 0;
        for (final var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

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

    public void loadClasse(final String filePath) {
        try {
            final var fileReader = new FileReader(filePath);
            final var bufferedReader = new BufferedReader(fileReader);
            var line = bufferedReader.readLine();
            this.nom = line;
            System.out.println("TEST 1");

            while ((line = bufferedReader.readLine()) != null && !line.equals("\r \n")) {
                System.out.println("TEST 2");
                final var etudiant = line.split(" ");
                if (etudiant.length == 2) {
                    final var nomEtudiant = etudiant[0];
                    System.out.println("TEST 4");
                    final var prenomEtudiant = etudiant[1];
                    System.out.println("TEST 5");
                    System.out.println(nomEtudiant);
                    System.out.println(prenomEtudiant);
                    final var etudiant1 = new Etudiant(nomEtudiant, prenomEtudiant);
                    while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
                        final var note = line.split(" : ");
                        final var matiere = note[0];
                        final var evaluation = note[1];
                        final var note2 = note[2].split(" ");
                        final var noteEtudiant = Double.parseDouble(note2[0]);
                        final var coef = Integer.parseInt(note2[2].substring(1));
                        etudiant1.setNote(matiere, evaluation, coef, noteEtudiant);
                    }
                    this.setEtudiant(etudiant1);
                }

            }
            bufferedReader.close();
        } catch (final Exception e) {
            System.out.println("Erreur lors du chargement de la classe");
        }
    }
}