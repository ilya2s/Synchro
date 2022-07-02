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

        Seance s1 = new Seance(Type.Examen, LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
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

        System.out.println("==============================================================");

        Cours c1 = new Cours("IFT", 1025, 3, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-07-26"));
        System.out.println(c1);

    }
}
