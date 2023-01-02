package PExo1;

public class Test {
    public void testEtudiant() {
        // Création d'un étudiant et ajout de quelques notes
        Etudiant etudiant1 = new Etudiant("Dupont", "Jean");
        Etudiant etudiant2 = new Etudiant("Némar", "Jean");
        Etudiant etudiant3 = new Etudiant("Doe", "John");
        Etudiant etudiant4 = new Etudiant("Doe", "Jane");
        etudiant1.setNote("Maths", "Examen", 2, 10);
        etudiant1.setNote("Maths", "Devoir", 8);
        etudiant1.setNote("Physique", "Examen", 2, 9);
        etudiant1.setNote("Physique", "Devoir", 6);
        etudiant1.setNote("SVT", "Examen", 2, 6);
        etudiant1.setNote("SVT", "Devoir", 7);

        // Test de la fonction afficherNote
        System.out.println("\033[31mTest de la fonction afficherNote :\033[0m");
        etudiant1.afficherNote();
        System.out.println("\n\033[31mTest de la fonction afficherNote(matiere) :\033[0m");
        etudiant1.afficherNote("Maths");
        System.out.println("\n");

        // Test de la fonction setNote
        etudiant1.setNote("Maths", "Devoir avec coefficient", 2, 5);
        System.out.println("\033[31mTest de la fonction setNote avec un coefficient :\033[0m");
        etudiant1.afficherNote("Maths");
        System.out.println("\n");

        // Test de la fonction moyenne
        System.out.println("\033[31mTest de la fonction moyenne :\033[0m");
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en Maths : " + (etudiant1.moyenne("Maths") == -1 ? "Pas de note" : etudiant1.moyenne("Maths")));
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en Physique : " + (etudiant1.moyenne("Physique") == -1 ? "Pas de note" : etudiant1.moyenne("Physique")));
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en SVT : " + (etudiant1.moyenne("SVT") == -1 ? "Pas de note" : etudiant1.moyenne("SVT")));
        System.out.println("Moyenne générale de "+ etudiant1.nom + " " + etudiant1.prenom + " : " + (etudiant1.moyenne("") == -1 ? "Pas de note" : etudiant1.moyenne("")));
        System.out.println("Moyenne générale de "+ etudiant2.nom + " " + etudiant2.prenom + " : " + (etudiant2.moyenne("") == -1 ? "Pas de note" : etudiant2.moyenne("")));
        System.out.println("\n");

        // Test d'ajout de note pour changer la moyenne
        etudiant1.setNote("Maths", "Devoir maison", 10, 3);
        etudiant1.setNote("Physique", "Devoir maison",10, 18);
        etudiant1.setNote("SVT", "Devoir maison",10, 13);
        System.out.println("\033[31mTest d'ajout de note pour changer la moyenne :\033[0m");
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en Maths : " + (etudiant1.moyenne("Maths") == -1 ? "Pas de note" : etudiant1.moyenne("Maths")));
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en Physique : " + (etudiant1.moyenne("Physique") == -1 ? "Pas de note" : etudiant1.moyenne("Physique")));
        System.out.println("Moyenne de "+ etudiant1.nom + " " + etudiant1.prenom + " en SVT : " + (etudiant1.moyenne("SVT") == -1 ? "Pas de note" : etudiant1.moyenne("SVT")));
        System.out.println("Moyenne générale de "+ etudiant1.nom + " " + etudiant1.prenom + " : " + (etudiant1.moyenne("") == -1 ? "Pas de note" : etudiant1.moyenne("")));
        System.out.println("Moyenne générale de "+ etudiant2.nom + " " + etudiant2.prenom + " : " + (etudiant2.moyenne("") == -1 ? "Pas de note" : etudiant2.moyenne("")));
        System.out.println("\n");



        Classe maClasse = new Classe("B2 - Linux/Réseaux");
        maClasse.setEtudiant(etudiant1);
        maClasse.setEtudiant(etudiant2);
        maClasse.setEtudiant(etudiant3);
        maClasse.setEtudiant(etudiant4);

        // Affichage des notes de chaque étudiant
        maClasse.afficher();

        // Calcul et affichage de la moyenne de la classe en mathématiques
        System.out.println("Moyenne générale de la classe : " + maClasse.moyenneClasse(""));
    }
}
