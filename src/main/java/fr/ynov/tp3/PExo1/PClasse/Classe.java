package fr.ynov.tp3.PExo1.PClasse;

import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Classe {
    public String nom;
    private final Map<String, Etudiant> etudiants;

    public Classe(String nom) {
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
        String[] etudiants = new String[this.etudiants.size()];
        int i = 0;
        for (var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

    public Etudiant getEtudiant(String nomEtudiant) {
        return etudiants.get(nomEtudiant);
    }

    public float moyenneClasse(String matiere) {
        double somme = 0;
        var nbEtudiants = 0;
        if (matiere == null) {
            for (var entry : etudiants.entrySet()) {
                var etudiant = entry.getValue();
                if (etudiant.moyenne("") != -1) {
                    somme += etudiant.moyenne("");
                    nbEtudiants++;
                }
            }
        } else {
            var hasNote = false;
            for (var entry : etudiants.entrySet()) {
                var etudiant = entry.getValue();
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

    public void setEtudiant(Etudiant etudiant) {
        var nomEtudiant = etudiant.nom;
        var prenomEtudiant = etudiant.prenom;
        var cle = nomEtudiant + " " + prenomEtudiant;
        etudiants.put(cle, etudiant);
    }

    public String[] getEtudiants() {
        String[] etudiants = new String[this.etudiants.size()];
        int i = 0;
        for (var entry : this.etudiants.entrySet()) {
            etudiants[i] = entry.getKey();
            i++;
        }
        return etudiants;
    }

    public String saveClasse() {
        String filePath = "maClasse.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("");

            fileWriter.write(this.nom);
            fileWriter.write("\r \n");
            for (var entry : etudiants.entrySet()) {
                var nomEtudiant = entry.getKey();
                fileWriter.write(nomEtudiant);
                fileWriter.write("\n");
                for (var entry2 : entry.getValue().notes.entrySet()) {
                    var matiere = entry2.getKey();
                    for (var entry3 : entry2.getValue().entrySet()) {
                        var evaluation = entry3.getKey();
                        fileWriter.write(matiere + " : " + evaluation + " : ");
                        for (var entry4 : entry3.getValue().entrySet()) {
                            int coef = entry4.getKey();
                            double note = entry4.getValue();
                            fileWriter.write(String.valueOf(note));
                            fileWriter.write(" (x" + coef + ")");
                            fileWriter.write("\n");
                        }
                    }
                }
                fileWriter.write("\r \n");
            }
            fileWriter.close();
        } catch (Exception e) {
            return "Erreur lors de la sauvegarde de la classe";
        }
        return "Classe sauvegardée";
    }

    public void loadClasse(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            this.nom = line;
            while ((line = bufferedReader.readLine()) != null && !line.equals("\r \n")) {
                String[] etudiant = line.split(" ");
                if (etudiant.length == 2) {
                    String nomEtudiant = etudiant[0];
                    String prenomEtudiant = etudiant[1];
                    Etudiant etudiant1 = new Etudiant(nomEtudiant, prenomEtudiant);
                    while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
                        String[] note = line.split(" : ");
                        String matiere = note[0];
                        String evaluation = note[1];
                        String[] note2 = note[2].split(" ");
                        double noteEtudiant = Double.parseDouble(note2[0]);
                        int coef = Integer.parseInt(note2[1].substring(2, 3));
                        etudiant1.setNote(matiere, evaluation, coef, noteEtudiant);
                    }
                    this.setEtudiant(etudiant1);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}