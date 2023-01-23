package fr.ynov.tp3.PExo1.PEtudiant;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Etudiant {
    public final String nom;
    public final String prenom;
    final Map<String, Map<String, Map<Integer, Double>>> notes;

    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new HashMap<>();
    }

//    public void afficherNote() {
//        System.out.println("Voici toutes les notes de l'étudiant " + prenom + " " + nom + " :");
//        for (var entry : notes.entrySet()) {
//            var matiere = entry.getKey();
//            var notesMatiere = entry.getValue();
//            for (var entry2 : notesMatiere.entrySet()) {
//                var evaluation = entry2.getKey();
//                var notesEvaluation = entry2.getValue();
//                for (var entry3 : notesEvaluation.entrySet()) {
//                    int coef = entry3.getKey();
//                    double note = entry3.getValue();
//                    System.out.println("  " + evaluation + " (" + matiere + ", coefficient " + coef + ") : " + note);
//                }
//            }
//        }
//    }

    public String[] afficherNote() {
        String[] notesArray = new String[0];
        for (var entry : notes.entrySet()) {
            var matiere = entry.getKey();
            var notesMatiere = entry.getValue();
            for (var entry2 : notesMatiere.entrySet()) {
                var evaluation = entry2.getKey();
                var notesEvaluation = entry2.getValue();
                for (var entry3 : notesEvaluation.entrySet()) {
                    int coef = entry3.getKey();
                    double note = entry3.getValue();
                    notesArray = Arrays.copyOf(notesArray, notesArray.length + 1);
                    notesArray[notesArray.length - 1] = matiere + " : " + evaluation + " > " + note + " (" + "coefficient " + coef + ")";
                }
            }
        }
        return notesArray;
    }

//    public void afficherNote (String matiere) {
//        var notesMatiere = notes.get(matiere);
//        if (notesMatiere == null) {
//            System.out.println("L'étudiant " + prenom + " " + nom + " n'a pas de notes en " + matiere);
//            return;
//        }
//        System.out.println("Notes de l'étudiant " + prenom + " " + nom + " en " + matiere + " :");
//        for (var entry : notesMatiere.entrySet()) {
//            var evaluation = entry.getKey();
//            var notesEvaluation = entry.getValue();
//            for (var entry2 : notesEvaluation.entrySet()) {
//                int coef = entry2.getKey();
//                double note = entry2.getValue();
//                System.out.println("  " + evaluation + " (coefficient " + coef + ") : " + note);
//            }
//        }
//    }

    public String[] afficherNote(String matiere) {
        var notesMatiere = notes.get(matiere);
        if (notesMatiere == null) {
            return null;
        }
        String[] notesArray = new String[0];
        for (var entry : notesMatiere.entrySet()) {
            var evaluation = entry.getKey();
            var notesEvaluation = entry.getValue();
            for (var entry2 : notesEvaluation.entrySet()) {
                int coef = entry2.getKey();
                double note = entry2.getValue();
                notesArray = Arrays.copyOf(notesArray, notesArray.length + 1);
                notesArray[notesArray.length - 1] = evaluation + " > " + note + (coef > 1 ? " (" + "x " + coef + ")" : "");
            }
        }
        return notesArray;
    }

    public int moyenne(String matiere) {
        var hasNote = false;
        double somme = 0;
        var coefTotal = 0;
        if (matiere == null || matiere.equals("")) {
            for (var entry : notes.entrySet()) {
                var notesMatiere = entry.getValue();
                for (var entry2 : notesMatiere.entrySet()) {
                    var notesEvaluation = entry2.getValue();
                    for (var entry3 : notesEvaluation.entrySet()) {
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
        } else if (notes.containsKey(matiere)){
            var notesMatiere = notes.get(matiere);
            for (var entry : notesMatiere.entrySet()) {
                var notesEvaluation = entry.getValue();
                for (var entry2 : notesEvaluation.entrySet()) {
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
        var notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(1, note);
    }

    public void setNote(String matiere, String evaluation, int coef, double note) {
        var notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(coef, note);
    }
}