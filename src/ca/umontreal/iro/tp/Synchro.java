package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.horaire.Horaire;

import java.util.Scanner;

public class Synchro {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
                    throw new Exception();
                }

                switch (choix) {
                    case 1 -> {
                        boolean created = e22.creerCours();
                        if (created) {
                            System.out.println("Cours ajouté aux cours disponibles.");
                        } else {
                            System.out.println("Le cours n'a pas été ajouté.");
                        }
                        System.out.println(e22);
                    }
                    case 2 -> {
                        boolean inscrit = e22.inscrireCours();
                        if (inscrit) {
                            System.out.println("Cours inscrit.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre inscrit.");
                        }
                        System.out.println(e22);
                    }
                    case 3 -> {
                        boolean desinscrit = e22.desinscrireCours();
                        if (desinscrit) {
                            System.out.println("Cours desinscrit.");
                        } else {
                            System.out.println("Le cours n'a pas pu etre desinscrit.");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Choix invalid! Veuillez recommencer.");
            }
        }
    }
}
