package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.plage.Plage;
import ca.umontreal.iro.tp.plage.PlageCours;
import ca.umontreal.iro.tp.plage.PlageExamen;
import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {

    public static void main(String[] args) {
        Plage p1 = new PlageCours(LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(p1);

        Plage p2 = new PlageExamen(LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(p2);

        System.out.println("------------------------------------------------------");

        Seance s1 = new Seance(Type.Examen, LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s1);
    }
}
