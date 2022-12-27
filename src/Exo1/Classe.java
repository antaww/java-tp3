package Exo1;

import java.util.HashMap;
import java.util.Map;

public class Classe {
    private final String nom;
    private final Map<String, Etudiant> etudiants;

    public Classe(String nom) {
        this.nom = nom;
        this.etudiants = new HashMap<>();
    }

    public void afficher() {
        System.out.println("Voici la liste des étudiants de la classe " + nom + " :");
        for (Map.Entry<String, Etudiant> entry : etudiants.entrySet()) {
            String nomEtudiant = entry.getKey();
            Etudiant etudiant = entry.getValue();
            System.out.println(" - " + nomEtudiant);
        }
    }

    public Etudiant getEtudiant(String nomEtudiant) {
        return etudiants.get(nomEtudiant);
    }

    public float moyenneClasse(String matiere) {
        double somme = 0;
        int nbEtudiants = 0;
        if (matiere == null || matiere.isEmpty()) {
            for (Map.Entry<String, Etudiant> entry : etudiants.entrySet()) {
                Etudiant etudiant = entry.getValue();
                if (etudiant.moyenne(matiere) != -1) {
                    somme += etudiant.moyenne(matiere);
                    nbEtudiants++;
                }
            }
        } else {
            for (Map.Entry<String, Etudiant> entry : etudiants.entrySet()) {
                Etudiant etudiant = entry.getValue();
                if (etudiant.moyenne(matiere) != -1) {
                    somme += etudiant.moyenne(matiere);
                    nbEtudiants++;
                }
            }
        }
        return (float) (somme / nbEtudiants);
    }

    public void setEtudiant(Etudiant etudiant) {
        String nomEtudiant = etudiant.nom;
        String prenomEtudiant = etudiant.prenom;
        String cle = nomEtudiant + " " + prenomEtudiant;
        etudiants.put(cle, etudiant);
    }
}