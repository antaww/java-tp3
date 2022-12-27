package Exo1;

import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    final String nom;
    final String prenom;
    private final Map<String, Map<String, Map<Integer, Double>>> notes;

    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new HashMap<>();
    }

    public void afficherNote() {
        System.out.println("Voici toutes les notes de l'étudiant " + prenom + " " + nom + " :");
        for (Map.Entry<String, Map<String, Map<Integer, Double>>> entry : notes.entrySet()) {
            String matiere = entry.getKey();
            Map<String, Map<Integer, Double>> notesMatiere = entry.getValue();
            for (Map.Entry<String, Map<Integer, Double>> entry2 : notesMatiere.entrySet()) {
                String evaluation = entry2.getKey();
                Map<Integer, Double> notesEvaluation = entry2.getValue();
                for (Map.Entry<Integer, Double> entry3 : notesEvaluation.entrySet()) {
                    int coef = entry3.getKey();
                    double note = entry3.getValue();
                    System.out.println("  " + evaluation + " (" + matiere + ", coefficient " + coef + ") : " + note);
                }
            }
        }
    }

    public void afficherNote(String matiere) {
        Map<String, Map<Integer, Double>> notesMatiere = notes.get(matiere);
        if (notesMatiere == null) {
            System.out.println("L'étudiant " + prenom + " " + nom + " n'a pas de notes en " + matiere);
            return;
        }
        System.out.println("Notes de l'étudiant " + prenom + " " + nom + " en " + matiere + " :");
        for (Map.Entry<String, Map<Integer, Double>> entry : notesMatiere.entrySet()) {
            String evaluation = entry.getKey();
            Map<Integer, Double> notesEvaluation = entry.getValue();
            for (Map.Entry<Integer, Double> entry2 : notesEvaluation.entrySet()) {
                int coef = entry2.getKey();
                double note = entry2.getValue();
                System.out.println("  " + evaluation + " (coefficient " + coef + ") : " + note);
            }
        }
    }

    public int moyenne(String matiere) {
        boolean hasNote = false;
        double somme = 0;
        int coefTotal = 0;
        if (matiere == null || matiere.isEmpty()) {
            for (Map.Entry<String, Map<String, Map<Integer, Double>>> entry : notes.entrySet()) {
                Map<String, Map<Integer, Double>> notesMatiere = entry.getValue();
                for (Map.Entry<String, Map<Integer, Double>> entry2 : notesMatiere.entrySet()) {
                    String evaluation = entry2.getKey();
                    Map<Integer, Double> notesEvaluation = entry2.getValue();
                    for (Map.Entry<Integer, Double> entry3 : notesEvaluation.entrySet()) {
                        int coef = entry3.getKey();
                        double note = entry3.getValue();
                        if (note != -1) {
                            somme += note * coef;
                            coefTotal += coef;
                            hasNote = true;
                        }
                    }
                }
            }
        } else {
            Map<String, Map<Integer, Double>> notesMatiere = notes.get(matiere);
            for (Map.Entry<String, Map<Integer, Double>> entry : notesMatiere.entrySet()) {
                String evaluation = entry.getKey();
                Map<Integer, Double> notesEvaluation = entry.getValue();
                for (Map.Entry<Integer, Double> entry2 : notesEvaluation.entrySet()) {
                    int coef = entry2.getKey();
                    double note = entry2.getValue();
                    if (note != -1) {
                        somme += note * coef;
                        coefTotal += coef;
                        hasNote = true;
                    }
                }
            }
        }
        if (hasNote) {
            return coefTotal > 0 ? (int) (somme / coefTotal) : 0;
        } else {
            return -1;
        }
    }

    public void setNote(String matiere, String evaluation, double note) {
        Map<Integer, Double> notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(1, note);
    }

    public void setNote(String matiere, String evaluation, int coef, double note) {
        Map<Integer, Double> notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(coef, note);
    }
}