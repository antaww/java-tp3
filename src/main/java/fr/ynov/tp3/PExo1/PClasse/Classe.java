package fr.ynov.tp3.PExo1.PClasse;

import fr.ynov.tp3.PExo1.PEtudiant.Etudiant;

import java.util.HashMap;
import java.util.Map;

public class Classe {
    public final String nom;
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
}