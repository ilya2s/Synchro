package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.cours.Cours;
import ca.umontreal.iro.tp.plage.Plage;
import ca.umontreal.iro.tp.plage.PlageCours;
import ca.umontreal.iro.tp.plage.PlageExamen;
import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tests {

    public static void main(String[] args) {
        Plage p1 = new PlageCours(LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(p1);
        System.out.println("Jour : " + p1.getJour());
        System.out.println("Debut : " + p1.getDebut());
        System.out.println("Fin : " + p1.getFin());
        System.out.println("---------------------------------------------");

        Plage p2 = new PlageExamen(LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(p2);
        System.out.println("Jour : " + p2.getJour());
        System.out.println("Debut : " + p2.getDebut());
        System.out.println("Fin : " + p2.getFin());

        System.out.println("==============================================================");

        Seance s1 = new Seance(Type.Intra, LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s1);
        System.out.println("Type: " + s1.getType());
        System.out.println("Plage: " + s1.getPlage());
        System.out.println("---------------------------------------------");

        Seance s2 = new Seance(Type.Theorie, LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s2);
        System.out.println("Type: " + s2.getType());
        System.out.println("Plage: " + s2.getPlage());
        System.out.println("---------------------------------------------");

        Seance s3 = new Seance(Type.Theorie, LocalDate.parse("2022-07-07"), LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        System.out.println(s3);
        System.out.println("Type: " + s3.getType());
        System.out.println("Plage: " + s3.getPlage());
        System.out.println("---------------------------------------------");

        Seance s4 = new Seance(Type.Pratique, LocalDate.parse("2022-07-07"), LocalTime.parse("13:00"), LocalTime.parse("15:00"));
        System.out.println(s4);
        System.out.println("Type: " + s4.getType());
        System.out.println("Plage: " + s4.getPlage());
        System.out.println("---------------------------------------------");

        Seance s5 = new Seance(Type.Final, LocalDate.parse("2022-07-26"), LocalTime.parse("12:00"), LocalTime.parse("15:00"));
        System.out.println(s5);
        System.out.println("Type: " + s5.getType());
        System.out.println("Plage: " + s5.getPlage());

        System.out.println("==============================================================");

        Cours c1 = new Cours("IFT", 1025, 3, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-07-26"));
        c1.ajouterSeance(s1);
        c1.ajouterSeance(s2);
        c1.ajouterSeance(s3);
        c1.ajouterSeance(s4);
        c1.ajouterSeance(s5);
        System.out.println(c1);

        System.out.println("---------------------------------------------");

        System.out.println("Matière: " + c1.getMatiere());
        System.out.println("Numéro: " + c1.getNumero());
        System.out.println("Crédits: " + c1.getCredits());
        System.out.println("Date de début: " + c1.getDateDebut());
        System.out.println("Date de fin: " + c1.getDateFin());
        System.out.println("Séances:");
        for (Seance s : c1.getSeances()) {
            System.out.println(s);
        }
        System.out.println("Séances Théoriques:");
        for (Seance s : c1.getSeances(Type.Theorie)) {
            System.out.println(s);
        }
        System.out.println("Séances Pratiques:");
        for (Seance s : c1.getSeances(Type.Pratique)) {
            System.out.println(s);
        }
        System.out.println("Intras:");
        for (Seance s : c1.getSeances(Type.Intra)) {
            System.out.println(s);
        }
        System.out.println("Final:");
        for (Seance s : c1.getSeances(Type.Final)) {
            System.out.println(s);
        }





    }
}
