package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tests {

    public static void main(String[] args) {

        Seance s1 = new Seance(Type.Theorie, LocalDate.parse("2022-07-05"), LocalTime.parse("12:00"), LocalTime.parse("14:00"));
        System.out.println(s1);

    }
}
