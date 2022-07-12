package ca.umontreal.iro.tp.horaire;

import ca.umontreal.iro.tp.cours.Cours;
import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * La classe Horaire représente un horaire d'étudiant contenant des cours disponibles et des cours inscrits
 */
public class Horaire {
    private final int creditsMax;   // nombre maximal de crédits pour cet horaire
    private int credits;    // les crédits inscrits à cet horaire
    private final List<Cours> coursDisponibles;     // Liste des cours disponibles pour l'inscription
    private final List<Cours> coursInscrits;    // Liste des cours inscrits

    /**
     * Constructeur d'un objet Horaire
     * @param creditsMax Nombre maximum de crédits pour l'horaire
     */
    public Horaire(int creditsMax) {
        this.creditsMax = creditsMax;
        credits = 0;
        coursDisponibles = new ArrayList<>();
        coursInscrits = new ArrayList<>();
    }

    /**
     * Creer un cours et l'ajouter à l'horaire
     * @param matiere La matire du cours (ex.: IFT)
     * @param numero Le numero du cours (ex.: 1025)
     * @return vrai si le cours est créee et ajouté avec succès
     */
    public boolean creerCours(String matiere, int numero) {
        Scanner scanner = new Scanner(System.in);

        Cours cours;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");   // format de la date
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");        // format de l'heure

        try {
            // Limiter à 10 cours au plus
            if (coursDisponibles.size() == 10) throw new Exception("Vous avez atteint la limite de cours");

            // Si cours est deja crée
            for (Cours c : coursDisponibles) {
                if (c.getMatiere().equals(matiere) && c.getNumero() == numero) {
                    throw new Exception("Ce cours existe deja!");
                }
            }


            System.out.print("CREDITS : ");
            int credits = Integer.parseInt(scanner.nextLine());

            System.out.print("DATE DE DEBUT (AAAA-MM-JJ) : ");
            LocalDate dateDebut = LocalDate.parse(scanner.nextLine(), df);

            System.out.print("DATE DE FIN (AAA-MM-JJ) : ");
            LocalDate dateFin = LocalDate.parse(scanner.nextLine(), df);

            if (dateFin.isBefore(dateDebut)) {
                throw new DateTimeException("La date de fin dois etre apres la date de debut!");
            }

            // instancier le cours
            cours = new Cours(matiere, numero, credits, dateDebut, dateFin);

            System.out.print("HEURE DEBUT DE L'EXAMEN FINAL : ");
            LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

            System.out.print("HEURE FIN DE L'EXAMEN FINAL : ");
            LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

            if (fin.isBefore(debut)) {
                throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
            }

            // Ajouter l'examen final au cours avec l'heure de début et l'heure de fin
            if (cours.ajouterFinal(debut, fin)) {
                System.out.println("Examen final ajouté avec succes.");
            } else {
                System.out.println("Echec dans l'ajout de l'examen final.");
            }

            System.out.println(cours);

        } catch (NumberFormatException e) {
            System.out.println("Nombre Invalid! Veuillez recommencer.");
            return false;
        } catch (DateTimeParseException e) {
            System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Veuillez recommencer.");
            return false;
        }

        coursDisponibles.add(cours);        // ajouter le cours à la liste de cours disponibles
        modifierCours(matiere, numero);     // lancer le menu de modification du cours

        return true;
    }

    /**
     * Supprimer un cours de la liste des cours disponibles <br><br>
     * Cette méthode aussi retire le cours de la liste des cours inscrits.
     * @param matiere Matière du cours à supprimer
     * @param numero Numéro du cours à supprimer
     * @return vrai si le cours est supprimé avec succès, sinon faux
     */
    public boolean supprimerCours(String matiere, int numero) {
        // retirer si un cours contient meme matière et meme numero
        if (coursDisponibles.removeIf(c -> c.getMatiere().equals(matiere) && c.getNumero() == numero)) {
            desinscrireCours(matiere, numero);  // aussi désinscrire des cours inscrits

            return true;
        }
        return false;
    }

    /**
     * Modifier les informations d'un cours disponible.
     * @param matiere Matière du cours à modifier
     * @param numero Numero du cours à modifier
     * @return vrai si le cours est modifié avec succès, sinon faux
     */
    public boolean modifierCours(String matiere, int numero) {
        Scanner scanner = new Scanner(System.in);

        // format de la date et de l'heure
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

        Cours cours = fetchCoursDisponible(matiere, numero);    // chercher le cours à modifier

        if (cours == null) return false;    // si cours n'existe pas quitter



        int choix = Integer.MIN_VALUE;
        while (choix != 0) {
            try {

                // Les options du menu de modification de cours
                System.out.println("Veuillez choisir une option :");
                System.out.println("(1) Ajouter seances");
                System.out.println("(2) Supprimer une seance");
                System.out.println("(3) Modifier une seance");
                System.out.println("(4) Ajouter examen intra");
                System.out.println("(5) Supprimer examen intra");
                System.out.println("(6) Modifer examen intra");
                System.out.println("(7) Modifer examen final");
                System.out.println("(0) Sauvegarder cours");

                choix = Integer.parseInt(scanner.nextLine());

                if (choix < 0 || choix > 7) {   // si choix n'est pas dans le range du menu
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Choix invalid! Veuillez recommencer.");
            }

            switch (choix) {
                case 1 -> {     // ajouter seances
                    try {
                        // lire type de seance
                        System.out.print("(T)héorie ou (P)ratique ?");
                        String type = scanner.nextLine();
                        // valider le type de la seance
                        if (!type.equalsIgnoreCase("T") && !type.equalsIgnoreCase("P")) {
                            throw new InputMismatchException("Type de seance invalid!");
                        }
                        Type typeSeance = type.equalsIgnoreCase("T") ? Type.Theorie : Type.Pratique;

                        // choix du jour de semaine
                        System.out.println("Veuillez choisir un jour de la semaine :");
                        System.out.println("(1) Lundi");
                        System.out.println("(2) Mardi");
                        System.out.println("(3) Mercredi");
                        System.out.println("(4) Jeudi");
                        System.out.println("(5) Vendredi");
                        int jour = Integer.parseInt(scanner.nextLine());
                        // valider le choix
                        if (jour < 1 || jour > 5) {
                            throw new InputMismatchException("Jour de semaine invalid!");
                        }
                        DayOfWeek jourSeance = DayOfWeek.of(jour);

                        System.out.print("Heure de début de la seance: ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Heure de fin de la seance: ");
                        LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

                        if (fin.isBefore(debut)) {
                            throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
                        }

                        // si la séance est ajoutée avec succès
                        if (cours.ajouterSeances(typeSeance, jourSeance, debut, fin)) {
                            System.out.println("Seance ajoutée avec succes.");
                        } else {
                            System.out.println("Echec dans l'ajout de la seance.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage() + " Veuillez recommnecer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }

                case 2 -> {     // supprimer seances
                    try {
                        System.out.print("(T)héorie ou (P)ratique ?");
                        String type = scanner.nextLine();
                        if (!type.equalsIgnoreCase("T") && !type.equalsIgnoreCase("P")) {
                            throw new InputMismatchException("Type de seance invalid!");
                        }
                        Type typeSeance = type.equalsIgnoreCase("T") ? Type.Theorie : Type.Pratique;

                        System.out.println("Veuillez choisir un jour de la semaine :");
                        System.out.println("(1) Lundi");
                        System.out.println("(2) Mardi");
                        System.out.println("(3) Mercredi");
                        System.out.println("(4) Jeudi");
                        System.out.println("(5) Vendredi");
                        int jour = Integer.parseInt(scanner.nextLine());
                        if (jour < 1 || jour > 5) {
                            throw new InputMismatchException("Jour de semaine invalid!");
                        }
                        DayOfWeek jourSeance = DayOfWeek.of(jour);

                        System.out.print("Heure de début de la seance: ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        // si la séance est supprimée avec succès
                        if (cours.supprimerSeances(typeSeance, jourSeance, debut)) {
                            System.out.println("Seance supprimee avec succes.");
                        } else {
                            System.out.println("Echec dans la suppression de seance.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }

                case 3 -> {     // Modifier une seance
                    try {
                        System.out.print("(T)héorie ou (P)ratique ?");
                        String type = scanner.nextLine();
                        if (!type.equalsIgnoreCase("T") && !type.equalsIgnoreCase("P")) {
                            throw new InputMismatchException("Type de seance invalid!");
                        }
                        Type typeSeance = type.equalsIgnoreCase("T") ? Type.Theorie : Type.Pratique;

                        System.out.println("Veuillez choisir un jour de la semaine :");
                        System.out.println("(1) Lundi");
                        System.out.println("(2) Mardi");
                        System.out.println("(3) Mercredi");
                        System.out.println("(4) Jeudi");
                        System.out.println("(5) Vendredi");
                        int jour = Integer.parseInt(scanner.nextLine());
                        if (jour < 1 || jour > 5) {
                            throw new InputMismatchException("Jour de semaine invalid!");
                        }
                        DayOfWeek jourSeance = DayOfWeek.of(jour);

                        System.out.print("Heure de début de la seance: ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.println("Veuillez choisir un nouveau jour de la semaine :");
                        System.out.println("(1) Lundi");
                        System.out.println("(2) Mardi");
                        System.out.println("(3) Mercredi");
                        System.out.println("(4) Jeudi");
                        System.out.println("(5) Vendredi");
                        int autreJour = Integer.parseInt(scanner.nextLine());
                        if (autreJour < 1 || autreJour > 5) {
                            throw new InputMismatchException("Jour de semaine invalid!");
                        }
                        DayOfWeek autreJourSeance = DayOfWeek.of(autreJour);

                        System.out.print("Nouvelle heure de début de la seance: ");
                        LocalTime autreDebut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Nouvelle heure de fin de la seance: ");
                        LocalTime autreFin = LocalTime.parse(scanner.nextLine(), tf);

                        // si la séance est modifiée avec succès
                        if (cours.modifierSeances(typeSeance,
                                jourSeance,
                                debut,
                                autreJourSeance,
                                autreDebut,
                                autreFin)) {
                            System.out.println("Seance modifiee avec succes.");
                        } else {
                            System.out.println("Echec dans la modification de seance.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage() + " Veuillez recommnecer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }

                case 4 -> {     // ajouter examen intra
                    try {
                        System.out.print("Date de l'examen intra (AAAA-MM-JJ) : ");
                        LocalDate dateIntra = LocalDate.parse(scanner.nextLine(), df);

                        if (dateIntra.isAfter(cours.getDateFin())) {
                            throw new DateTimeException("La date de l'intra ne peu pas etre apres la date de fin!");
                        }

                        if (dateIntra.isBefore(cours.getDateDebut())) {
                            throw new DateTimeException("La date de l'intra ne peu pas etre avant la date de debut!");
                        }

                        System.out.print("Heure de début de l'examen intra : ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Heure de fin de l'examen intra : ");
                        LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

                        if (fin.isBefore(debut)) {
                            throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
                        }

                        // si l'examen intra est ajouté avec succès
                        if (cours.ajouterIntra(dateIntra, debut, fin)) {
                            System.out.println("Intra ajouté avec succes.");
                        } else {
                            System.out.println("Echec dans l'ajout de l'examen Intra.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage() + " Veuillez recommnecer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }
                case 5 -> {     // supprimer examen intra
                    try {
                        System.out.print("Date de l'examen intra à supprimer : ");
                        LocalDate dateIntra = LocalDate.parse(scanner.nextLine(), df);

                        System.out.print("Heure de début de l'examen intra a supprimer : ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        // si l'examen est supprimé avec succès
                        if (cours.supprimerIntra(dateIntra, debut)) {
                            System.out.println("Intra supprimee avec succes.");
                        } else {
                            System.out.println("Echec dans la suppression de l'examen intra.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }
                case 6 -> {     // modifier examen intra
                    try {
                        System.out.print("Date de l'examen intra à modifier : ");
                        LocalDate dateIntra = LocalDate.parse(scanner.nextLine(), df);

                        System.out.print("Heure de début de l'examen intra a modifier : ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Nouvelle date de l'examen intra : ");
                        LocalDate autreDate = LocalDate.parse(scanner.nextLine(), df);

                        System.out.print("Nouvelle heure de début de l'examen intra : ");
                        LocalTime autreDebut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Nouvelle heure de fin de l'examen intra : ");
                        LocalTime autreFin = LocalTime.parse(scanner.nextLine(), tf);

                        // si l'examen intra est modifié avec succès
                        if (cours.modifierIntra(dateIntra, debut, autreDate, autreDebut, autreFin)) {
                            System.out.println("Intra modifiee avec succes.");
                        } else {
                            System.out.println("Echec dans la modification de l'examen intra.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage() + " Veuillez recommnecer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }
                case 7 -> {     // modifier examen final
                    try {
                        System.out.print("Nouvelle heure de début de l'examen final : ");
                        LocalTime autreDebut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Nouvelle heure de fin de l'examen final : ");
                        LocalTime autreFin = LocalTime.parse(scanner.nextLine(), tf);

                        // si l'examen final est modifié avec succès
                        if (cours.modifierFinal(autreDebut, autreFin)) {
                            System.out.println("Final modifiee avec succes.");
                        } else {
                            System.out.println("Echec dans la modification de l'examen final.");
                        }

                        System.out.println(cours);

                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date ou heure invalid! Veuillez recommencer.");
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage() + " Veuillez recommnecer.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Veuillez recommencer.");
                    } catch (Exception e) {
                        System.out.println("Choix invalid! Veuillez recommencer.");
                    }
                    System.out.println("----------------------------------------------------------");
                }
            }
        }

        return false;
    }

    /**
     * Inscire un cours disponible dans l'horaire
     * @param matiere Matière du cours à inscrire
     * @param numero Numéro du cours à inscrire
     * @return vrai si le cours est inscrit avec succès
     */
    public boolean inscrireCours(String matiere, int numero) {
        // Cherche si le cours existe dans coursDisponibles
        Cours cours = fetchCoursDisponible(matiere, numero);

        if (cours == null) return false;    // si cours n'existe pas

        // si le nombre de crédits dépasse le max autorisé
        if (credits + cours.getCredits() > creditsMax) {
            System.out.println("Impossible d'inscire le cours! Vous n'avez le droit qu'à " + creditsMax + " credits.");
            return false;
        }

        // verifier qu'il n y pas de conflit avec les autres coursInscrits
        for (Seance s : cours.getSeances()) {   // pour chaque séance du cours à ajouter

            // explorer

            for (Cours c : coursInscrits) {  // pour chaque cours inscrit
                if (!c.equals(cours)) {     // si ce n'est pas le meme cours
                    for (Seance seance : c.getSeances()) {  // pour chauqe séance
                        if (s.isConflict(seance)) {     // valider si il y'a un conflit
                            System.out.println("Impossible d'inscire le cours! Il y a conflit d'horaire.");
                            return false;
                        }
                    }
                }
            }
        }

        // ajouter cours dans coursInscrits à partir de coursDisponibles
        credits += cours.getCredits();
        return coursInscrits.add(cours);
    }

    /**
     * Désinscrire un cours de l'horaire. Ne le retire pas de la liste de cours disponibles.
     * @param matiere Matière du cours à désinscrire
     * @param numero Numéro du cours à désinscire
     * @return vrai si le cours est désinscrit avec succès
     */
    public boolean desinscrireCours(String matiere, int numero) {
        Cours cours = fetchCoursInscrit(matiere, numero);   // trouver le cours dans la liste de cours inscrits

        if (cours == null) return false;    // si n'existe pas

        credits -= cours.getCredits();  // modifier les crédits

        return coursInscrits.removeIf(c -> c.equals(cours));    // retirer le cours de la liste des cours inscrits
    }

    /**
     * Trouver un cours ayant une matière et un numéro donné en paramètre dans la liste de cours disponibles
     * @param matiere Matière du cours à trouver
     * @param numero Numéro du cours à trouver
     * @return Objet Cours trouvé si le cours existe, sinon null
     */
    private Cours fetchCoursDisponible(String matiere, int numero) {
        for (Cours c : coursDisponibles) {
            if (c.getMatiere().equalsIgnoreCase(matiere) && c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    /**
     * Trouver un cours ayant une matière et un numéro donné en paramètre dans la liste de cours inscrits
     * @param matiere Matière du cours à trouver
     * @param numero Numéro du cours à trouver
     * @return Objet COurs trouvé si le cours existe, sinon null
     */
    private Cours fetchCoursInscrit(String matiere, int numero) {
        for (Cours c : coursInscrits) {
            if (c.getMatiere().equalsIgnoreCase(matiere) && c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    /**
     * @return Affichage formaté des cours disponibles
     */
    private String stringifyCoursDisponibles() {
        StringBuilder output = new StringBuilder();

        for (Cours c : coursDisponibles) {
            output.append(c);
        }

        return output.toString();
    }

    /**
     * @return Affichage formaté des cours inscrits
     */
    private String stringifyCoursInscrits() {
        StringBuilder output = new StringBuilder();

        for (Cours c : coursInscrits) {
            output.append(c);
        }

        return output.toString();
    }

    /**
     * Affiche l'horaire d'un étudiant avec le nombdre de crédits inscrits, les cours disponibles et les cours inscrits
     * @return Affichage formaté de l'horaire
     */
    @Override
    public String toString() {
        return "========================================\n" +
                credits +
                " credits inscrits (" +
                (creditsMax - credits) + " credits restants)\n" +
                "========================================\n" +
                "Cours disponibles :\n" +
                stringifyCoursDisponibles() +
                "\n========================================\n" +
                "Cours inscrits :\n" +
                stringifyCoursInscrits() +
                "========================================";

    }

}
