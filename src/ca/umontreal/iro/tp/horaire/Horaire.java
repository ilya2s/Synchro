package ca.umontreal.iro.tp.horaire;

import ca.umontreal.iro.tp.cours.Cours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Horaire {

    private Session session;
    private int annee;
    private int creditsMax;
    private int credits;
    private List<Cours> coursDisponibles;
    private List<Cours> coursInscrits;

    public Horaire(Session session, int annee, int creditsMax) {
        this.session = session;
        this.annee = annee;
        this.creditsMax = creditsMax;
        credits = 0;
        coursDisponibles = new ArrayList<>();
        coursInscrits = new ArrayList<>();
    }

    public boolean creerCours() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("MATIERE : ");
            String matiere = scanner.nextLine();

            System.out.print("NUMERO : ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("CREDITS : ");
            int credits = Integer.parseInt(scanner.nextLine());

            System.out.print("DATE DE DEBUT (AAAA-MM-JJ) : ");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateDebut = LocalDate.parse(scanner.nextLine(), df);

            System.out.print("DATE DE FIN (AAA-MM-JJ) : ");
            LocalDate dateFin = LocalDate.parse(scanner.nextLine(), df);

            Cours cours = new Cours(matiere, numero, credits, dateDebut, dateFin);

        } catch (NumberFormatException e) {
            System.err.println("Nombre Invalid!");
        } catch (DateTimeParseException e) {
            System.err.println("Format de date invalid!");
        } catch (Exception e) {
            System.err.println(e);
        }

        return true;
    }


    @Override
    public String toString() {
        return session + "-" + annee + " (" + credits + " credits inscrits)";
    }

}
