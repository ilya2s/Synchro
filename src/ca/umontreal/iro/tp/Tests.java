package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.SeanceExamen;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tests {

    public static void main(String[] args) {

        Seance s1 = new Seance(Type.Theorie, LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s1);

        Seance s2 = new Seance(Type.Theorie, LocalDate.parse("2022-07-07"), LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        System.out.println(s2);

        Seance s3 = new Seance(Type.Pratique, LocalDate.parse("2022-07-07"), LocalTime.parse("13:00"), LocalTime.parse("15:00"));
        System.out.println(s3);

        Seance s4 = new SeanceExamen(Type.Intra, LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s4);

        Seance s5 = new SeanceExamen(Type.Final, LocalDate.parse("2022-07-26"), LocalTime.parse("12:00"), LocalTime.parse("15:00"));
        System.out.println(s5);

    }
}
