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
        System.out.println("---------------------------------------------");

        Seance s6 = new Seance(Type.Pratique, LocalDate.parse("2022-07-07"), LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        System.out.println(s6);
        System.out.println("Type: " + s6.getType());
        System.out.println("Plage: " + s6.getPlage());
        System.out.println("---------------------------------------------");

        Seance s7 = new Seance(Type.Pratique, LocalDate.parse("2022-07-07"), LocalTime.parse("11:00"), LocalTime.parse("12:30"));
        System.out.println(s7);
        System.out.println("Type: " + s7.getType());
        System.out.println("Plage: " + s7.getPlage());
        System.out.println("---------------------------------------------");

        Seance s8 = new Seance(Type.Pratique, LocalDate.parse("2022-07-05"), LocalTime.parse("12:30"), LocalTime.parse("15:00"));
        System.out.println(s8);
        System.out.println("Type: " + s8.getType());
        System.out.println("Plage: " + s8.getPlage());
        System.out.println("---------------------------------------------");

        Seance s9 = new Seance(Type.Theorie, LocalDate.parse("2022-07-12"), LocalTime.parse("12:30"), LocalTime.parse("13:00"));
        System.out.println(s9);
        System.out.println("Type: " + s9.getType());
        System.out.println("Plage: " + s9.getPlage());
        System.out.println("---------------------------------------------");

        Seance s10 = new Seance(Type.Intra, LocalDate.parse("2022-07-07"), LocalTime.parse("13:00"), LocalTime.parse("15:00"));
        System.out.println(s10);
        System.out.println("Type: " + s10.getType());
        System.out.println("Plage: " + s10.getPlage());
        System.out.println("---------------------------------------------");

        System.out.println("==============================================================");

        Cours c1 = new Cours("IFT", 1025, 3, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-07-26"));
        System.out.println("S1 : " + (c1.ajouterSeance(s1) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S2 : " + (c1.ajouterSeance(s2) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S3 : " + (c1.ajouterSeance(s3) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S4 : " + (c1.ajouterSeance(s4) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S5 : " + (c1.ajouterSeance(s5) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S6 : " + (c1.ajouterSeance(s6) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S7 : " + (c1.ajouterSeance(s7) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S8 : " + (c1.ajouterSeance(s8) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S9 : " + (c1.ajouterSeance(s9) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("S10 : " + (c1.ajouterSeance(s10) ? "Cours ajouté!" : "Erreur il y a conflit d'horaire"));
        System.out.println("---------------------------------------------");

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
