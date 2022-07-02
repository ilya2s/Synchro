package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.cours.Cours;
import ca.umontreal.iro.tp.seance.PlageHoraire;
import ca.umontreal.iro.tp.seance.Seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        PlageHoraire p1 = new PlageHoraire(LocalDate.parse("2022-07-05")
                , LocalTime.parse("12:00")
                , LocalTime.parse("14:00"));
        System.out.println(p1);

        PlageHoraire p2 = new PlageHoraire(LocalDate.parse("2022-06-30")
                , LocalTime.parse("12:00")
                , LocalTime.parse("13:00"));
        System.out.println(p2);

        PlageHoraire p3 = new PlageHoraire(LocalDate.parse("2022-06-30")
                , LocalTime.parse("13:00")
                , LocalTime.parse("15:00"));
        System.out.println(p3);

        PlageHoraire p4 = new PlageHoraire(LocalDate.parse("2022-07-26")
                , LocalTime.parse("12:00")
                , LocalTime.parse("15:00"));
        System.out.println(p4);

        PlageHoraire p5 = new PlageHoraire(LocalDate.parse("2022-06-14")
                , LocalTime.parse("12:00")
                , LocalTime.parse("14:00"));
        System.out.println(p5);

        System.out.println("-----------------------------------------------------------------");


        Seance s1 = new Seance("Théorie", p1);
        System.out.println(s1);

        Seance s2 = new Seance("Théorie", p2);
        System.out.println(s2);

        Seance s3 = new Seance("Pratique", p3);
        System.out.println(s3);

        Seance s4 = new Seance("Intra", p5);
        System.out.println(s4);

        Seance s5 = new Seance("Final", p4);
        System.out.println(s5);

        System.out.println("-----------------------------------------------------------------");

        ArrayList<Seance> seances1 = new ArrayList<>();
        seances1.add(s1);
        seances1.add(s2);
        seances1.add(s3);
        seances1.add(s4);
        seances1.add(s5);

        Cours c1 = new Cours("IFT", 1025, 3, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-07-22"), seances1);
        System.out.println(c1);

        System.out.println("-----------------------------------------------------------------");



    }
}
