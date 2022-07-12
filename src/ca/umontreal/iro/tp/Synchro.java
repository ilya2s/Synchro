package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.horaire.Horaire;

import java.util.Scanner;

/**
 * Fonction main du programme, Ici on prend les inputs de l'utilisateur et on appelle les methodes appropriées de
 * la Classe Horaire
 */
public class Synchro {

    /**
     * Permet de verifier si un String est un numéro ou non
     * @param s Le String à valider
     * @return vrai si le String est un numéro, sinon faux
     */
    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // le nombre de crédits max par session est determiné par l'administration
        // ni l'étudiant ni une TDGE ne peut le modifier donc il ne fait pas partie des inputs
        Horaire e22 = new Horaire(18);
        System.out.println(e22);

        int choix = Integer.MIN_VALUE;
        while (choix != 0) {

            try {
                System.out.println("Veuillez choisir une option :");
                System.out.println("(1) Creer un cours");
                System.out.println("(2) Supprimer un cours");
                System.out.println("(3) Modifier un cours");
                System.out.println("(4) Inscrire cours a l'horaire");
                System.out.println("(5) Desinscire cours de l'horaire");
                System.out.println("(0) Quitter");

                choix = Integer.parseInt(scanner.nextLine());

                if (choix < 0 || choix > 5) {
                    throw new Exception("Choix invalid!");
                }

                switch (choix) {
                    case 1 -> {
                        System.out.print("MATIERE : ");
                        String matiere = scanner.nextLine().toUpperCase();
                        if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

                        System.out.print("NUMERO : ");
                        int numero = Integer.parseInt(scanner.nextLine());

                        boolean created = e22.creerCours(matiere, numero);

                        if (created) {
                            System.out.println("Cours ajouté aux cours disponibles.");
                        } else {
                            System.out.println("Le cours n'a pas été ajouté.");
                        }
                        System.out.println(e22);
                    }

                    case 2 -> {

                        System.out.print("MATIERE : ");
                        String matiere = scanner.nextLine().toUpperCase();
                        if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

                        System.out.print("NUMERO : ");
                        int numero = Integer.parseInt(scanner.nextLine());

                        boolean inscrit = e22.supprimerCours(matiere, numero);
                        if (inscrit) {
                            System.out.println("Cours supprimé.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre supprimé.");
                        }
                        System.out.println(e22);

                    }

                    case 3 -> {
                        System.out.print("MATIERE : ");
                        String matiere = scanner.nextLine().toUpperCase();
                        if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

                        System.out.print("NUMERO : ");
                        int numero = Integer.parseInt(scanner.nextLine());

                        boolean inscrit = e22.modifierCours(matiere, numero);
                        if (inscrit) {
                            System.out.println("Cours modifié.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre modifié.");
                        }
                        System.out.println(e22);

                    }

                    case 4 -> {
                        System.out.print("MATIERE : ");
                        String matiere = scanner.nextLine().toUpperCase();
                        if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

                        System.out.print("NUMERO : ");
                        int numero = Integer.parseInt(scanner.nextLine());

                        boolean inscrit = e22.inscrireCours(matiere, numero);
                        
                        if (inscrit) {
                            System.out.println("Cours inscrit.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre inscrit.");
                        }
                        System.out.println(e22);
                    }
                    
                    case 5 -> {
                        System.out.print("MATIERE : ");
                        String matiere = scanner.nextLine().toUpperCase();
                        if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

                        System.out.print("NUMERO : ");
                        int numero = Integer.parseInt(scanner.nextLine());

                        boolean desinscrit = e22.desinscrireCours(matiere, numero);
                        
                        if (desinscrit) {
                            System.out.println("Cours desinscrit.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre desinscrit.");
                        }
                        System.out.println(e22);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Veuillez recommencer.");
            }
        }
    }
}
