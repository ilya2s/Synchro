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

public class Horaire {
    private final int creditsMax;
    private int credits;
    private final List<Cours> coursDisponibles;
    private final List<Cours> coursInscrits;

    public Horaire(int creditsMax) {
        this.creditsMax = creditsMax;
        credits = 0;
        coursDisponibles = new ArrayList<>();
        coursInscrits = new ArrayList<>();
    }

    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean creerCours() {
        Scanner scanner = new Scanner(System.in);

        Cours cours;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

        try {
            if (coursDisponibles.size() == 3) throw new Exception("Vous avez atteint la limite de cours");

            System.out.print("MATIERE : ");
            String matiere = scanner.nextLine().toUpperCase();
            if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

            System.out.print("NUMERO : ");
            int numero = Integer.parseInt(scanner.nextLine());

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

            cours = new Cours(matiere, numero, credits, dateDebut, dateFin);

            System.out.print("HEURE DEBUT DE L'EXAMEN FINAL : ");
            LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

            System.out.print("HEURE FIN DE L'EXAMEN FINAL : ");
            LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

            if (fin.isBefore(debut)) {
                throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
            }

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



        return coursDisponibles.add(cours);
    }

    public boolean supprimerCours() {
        return false;
    }

    public boolean modifierCours() {
        Scanner scanner = new Scanner(System.in);

        Cours cours;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

        try {
            System.out.print("MATIERE : ");
            String matiere = scanner.nextLine();
            if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

            System.out.print("NUMERO : ");
            int numero = Integer.parseInt(scanner.nextLine());

            // Cherche si le cours existe dans coursDisponibles
            cours = fetchCoursDisponible(matiere, numero);

            if (cours == null) return false;    // si cours n'existe pas
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Veuillez recommencer.");
        }



        int choix = Integer.MIN_VALUE;
        while (choix != 0) {
            try {
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

                if (choix < 0 || choix > 7) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Choix invalid! Veuillez recommencer.");
            }

            switch (choix) {
                case 1 -> {
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

                        System.out.print("Heure de fin de la seance: ");
                        LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

                        if (fin.isBefore(debut)) {
                            throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
                        }

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
                case 2 -> {
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
                case 3 -> {
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
                case 4 -> {
                    try {
                        System.out.print("Date de l'examen intra (AAAA-MM-JJ) : ");
                        LocalDate dateIntra = LocalDate.parse(scanner.nextLine(), df);

                        if (dateIntra.isAfter(cours.getDateFin())) {
                            throw new DateTimeException("La date de l'intra ne peu pas etre apres la date de fin!");
                        }

                        System.out.print("Heure de début de l'examen intra : ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Heure de fin de l'examen intra : ");
                        LocalTime fin = LocalTime.parse(scanner.nextLine(), tf);

                        if (fin.isBefore(debut)) {
                            throw new DateTimeException("L'heure de fin doit etre apres l'heure de debut!");
                        }

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
                case 5 -> {
                    try {
                        System.out.print("Date de l'examen intra à supprimer : ");
                        LocalDate dateIntra = LocalDate.parse(scanner.nextLine(), df);

                        System.out.print("Heure de début de l'examen intra a supprimer : ");
                        LocalTime debut = LocalTime.parse(scanner.nextLine(), tf);

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
                case 6 -> {
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
                case 7 -> {
                    try {
                        System.out.print("Nouvelle heure de début de l'examen final : ");
                        LocalTime autreDebut = LocalTime.parse(scanner.nextLine(), tf);

                        System.out.print("Nouvelle heure de fin de l'examen final : ");
                        LocalTime autreFin = LocalTime.parse(scanner.nextLine(), tf);

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

    public boolean inscrireCours() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("MATIERE : ");
            String matiere = scanner.nextLine();
            if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

            System.out.print("NUMERO : ");
            int numero = Integer.parseInt(scanner.nextLine());

            // Cherche si le cours existe dans coursDisponibles
            Cours cours = fetchCoursDisponible(matiere, numero);

            if (cours == null) return false;    // si cours n'existe pas

            if (credits + cours.getCredits() > creditsMax) {
                System.out.println("Impossible d'inscire le cours! Vous n'avez le droit qu'à " + creditsMax + " credits.");
                return false;
            }

            // verifier qu'il n y pas de conflit avec les autres coursInscrits
            for (Seance s : cours.getSeances()) {
                for (Cours c : coursDisponibles) {
                    if (!c.equals(cours)) {
                        for (Seance seance : c.getSeances()) {
                            if (s.isConflict(seance)) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage() + " Veuillez recommencer.");
        }

        return false;
    }

    private Cours fetchCoursDisponible(String matiere, int numero) {
        for (Cours c : coursDisponibles) {
            if (c.getMatiere().equalsIgnoreCase(matiere) && c.getNumero() == numero) {
                return c;
            }
        }

        return null;
    }

    private Cours fetchCoursInscrit(String matiere, int numero) {
        for (Cours c : coursInscrits) {
            if (c.getMatiere().equalsIgnoreCase(matiere) && c.getNumero() == numero) {
                return c;
            }
        }

        return null;
    }

    public boolean desinscrireCours() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("MATIERE : ");
            String matiere = scanner.nextLine();
            if (isNumeric(matiere)) throw new Exception("La matiere ne peut pas etre un numero!");

            System.out.print("NUMERO : ");
            int numero = Integer.parseInt(scanner.nextLine());

            Cours cours = fetchCoursInscrit(matiere, numero);

            if (cours == null) return false;

            credits -= cours.getCredits();

            return coursInscrits.removeIf(c -> c.equals(cours));

        } catch (Exception e) {
            System.out.println(e.getMessage() + " Veuillez recommencer.");
        }

        return false;
    }

    private String stringifyCoursDisponibles() {
        StringBuilder output = new StringBuilder();

        for (Cours c : coursDisponibles) {
            output.append(c);
        }

        return output.toString();
    }

    private String stringifyCoursInscrits() {
        StringBuilder output = new StringBuilder();

        for (Cours c : coursInscrits) {
            output.append(c);
        }

        return output.toString();
    }

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
