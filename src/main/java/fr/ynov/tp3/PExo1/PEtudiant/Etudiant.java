package fr.ynov.tp3.PExo1.PEtudiant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Etudiant : une classe qui contient les informations sur un étudiant
 * Cette classe permet de stocker les informations sur un étudiant telles que le nom, le prénom et les notes de l'étudiant dans différentes matières.
 * Elle utilise une Map pour stocker les notes de l'étudiant, où la clé est le nom de la matière et la valeur est une Map des evaluations de l'étudiant dans cette matière.
 * Elle contient également des méthodes pour afficher les notes de l'étudiant, calculer la moyenne de l'étudiant et ajouter des notes à l'étudiant.
 *
 * @see java.util.Arrays
 * @see java.util.HashMap
 * @see java.util.Map
 */
public class Etudiant {
    public final String nom;
    public final String prenom;
    public final Map<String, Map<String, Map<Integer, Double>>> notes;

    /**
     * Constructeur Etudiant : création d'un étudiant.
     *
     * @param nom    nom de l'étudiant
     * @param prenom prénom de l'étudiant
     */
    public Etudiant(final String nom, final String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new HashMap<>();
    }

    /**
     * Méthode afficherNote : méthode pour afficher les notes de l'étudiant.
     *
     * @return tableau de String contenant les notes de l'étudiant
     */
    public String[] afficherNote() {
        var notesArray = new String[0];
        for (final var entry : notes.entrySet()) {
            final var matiere = entry.getKey();
            final var notesMatiere = entry.getValue();
            for (final var entry2 : notesMatiere.entrySet()) {
                final var evaluation = entry2.getKey();
                final var notesEvaluation = entry2.getValue();
                for (final var entry3 : notesEvaluation.entrySet()) {
                    final int coef = entry3.getKey();
                    final double note = entry3.getValue();
                    notesArray = Arrays.copyOf(notesArray, notesArray.length + 1);
                    notesArray[notesArray.length - 1] = matiere + " : " + evaluation + " : " + note + " (" + "coefficient " + coef + ")";
                }
            }
        }
        return notesArray;
    }

    /**
     * Méthode afficherNote : méthode pour afficher les notes d'une matière de l'étudiant.
     *
     * @param matiere matière pour laquelle afficher les notes de l'étudiant
     * @return tableau de String contenant les notes de l'étudiant pour la matière passée en paramètre
     */
    public String[] afficherNote(final String matiere) {
        final var notesMatiere = notes.get(matiere);
        if (notesMatiere == null) {
            return null;
        }
        var notesArray = new String[0];
        for (final var entry : notesMatiere.entrySet()) {
            final var evaluation = entry.getKey();
            final var notesEvaluation = entry.getValue();
            for (final var entry2 : notesEvaluation.entrySet()) {
                final int coef = entry2.getKey();
                final double note = entry2.getValue();
                notesArray = Arrays.copyOf(notesArray, notesArray.length + 1);
                notesArray[notesArray.length - 1] = evaluation + " > " + note + (coef > 1 ? " (" + "x " + coef + ")" : "");
            }
        }
        return notesArray;
    }

    /**
     * Méthode moyenne : méthode pour calculer la moyenne de l'étudiant.
     *
     * @param matiere matière pour laquelle calculer la moyenne de l'étudiant
     * @return moyenne de l'étudiant pour la matière passée en paramètre
     */
    public int moyenne(final String matiere) {
        var hasNote = false;
        double somme = 0;
        var coefTotal = 0;
        if (matiere == null || matiere.equals("")) {
            for (final var entry : notes.entrySet()) {
                final var notesMatiere = entry.getValue();
                for (final var entry2 : notesMatiere.entrySet()) {
                    final var notesEvaluation = entry2.getValue();
                    for (final var entry3 : notesEvaluation.entrySet()) {
                        final int coef = entry3.getKey();
                        final double note = entry3.getValue();
                        if (note != -1) {
                            somme += note * coef;
                            coefTotal += coef;
                            hasNote = true;
                        }
                    }
                }
            }
        } else if (notes.containsKey(matiere)) {
            final var notesMatiere = notes.get(matiere);
            for (final var entry : notesMatiere.entrySet()) {
                final var notesEvaluation = entry.getValue();
                for (final var entry2 : notesEvaluation.entrySet()) {
                    final int coef = entry2.getKey();
                    final double note = entry2.getValue();
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

    /**
     * Méthode setNote : méthode pour ajouter une note à l'étudiant.
     *
     * @param matiere    matière pour laquelle ajouter la note
     * @param evaluation évaluation pour laquelle ajouter la note
     * @param note       note à ajouter
     */
    public void setNote(final String matiere, final String evaluation, final double note) {
        final var notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(1, note);
    }

    /**
     * Méthode setNote : méthode pour ajouter une note à l'étudiant.
     *
     * @param matiere    matière pour laquelle ajouter la note
     * @param evaluation évaluation pour laquelle ajouter la note
     * @param coef       coefficient de la note
     * @param note       note à ajouter
     */
    public void setNote(final String matiere, final String evaluation, final int coef, final double note) {
        final var notesEvaluation = notes.computeIfAbsent(matiere, k -> new HashMap<>()).computeIfAbsent(evaluation, k -> new HashMap<>());
        notesEvaluation.put(coef, note);
    }
}