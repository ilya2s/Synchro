package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.horaire.Horaire;

public class Tests {

    public static void main(String[] args) {

        /*
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

        System.out.println("========================================================");

        Cours c1 = new Cours("IFT", 1025, 3, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-07-26"));
        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Theorie,
                DayOfWeek.TUESDAY, LocalTime.parse("12:00"), LocalTime.parse("14:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Pratique,
                DayOfWeek.THURSDAY, LocalTime.parse("13:00"), LocalTime.parse("15:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("SUPPRIMER : " + c1.supprimerSeances(Type.Theorie,
                DayOfWeek.TUESDAY, LocalTime.parse("12:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Pratique,
                DayOfWeek.THURSDAY, LocalTime.parse("13:00"), LocalTime.parse("15:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER : " + c1.modifierSeances(Type.Pratique,
                DayOfWeek.THURSDAY, LocalTime.parse("13:00"), DayOfWeek.FRIDAY,
                LocalTime.parse("08:00"), LocalTime.parse("11:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER : " + c1.modifierSeances(Type.Pratique,
                DayOfWeek.FRIDAY, LocalTime.parse("08:00"), DayOfWeek.THURSDAY,
                LocalTime.parse("13:00"), LocalTime.parse("15:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Theorie,
                DayOfWeek.TUESDAY, LocalTime.parse("12:00"), LocalTime.parse("14:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Theorie,
                DayOfWeek.THURSDAY, LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER : " + c1.ajouterSeances(Type.Theorie,
                DayOfWeek.TUESDAY, LocalTime.parse("12:00"), LocalTime.parse("14:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");


        System.out.println("AJOUTER INTRA : " + c1.ajouterIntra(LocalDate.parse("2022-06-14"),
                LocalTime.parse("12:00"), LocalTime.parse("14:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER INTRA : " + c1.ajouterIntra(LocalDate.parse("2022-06-14"),
                LocalTime.parse("10:00"), LocalTime.parse("11:45")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER INTRA : " + c1.modifierIntra(LocalDate.parse("2022-06-14"),
                LocalTime.parse("12:00"), LocalDate.parse("2022-06-15"), LocalTime.parse("08:00"),
                LocalTime.parse("11:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER INTRA : " + c1.modifierIntra(LocalDate.parse("2022-06-14"),
                LocalTime.parse("12:00"), LocalDate.parse("2022-06-15"), LocalTime.parse("08:00"),
                LocalTime.parse("11:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER INTRA : " + c1.modifierIntra(LocalDate.parse("2022-06-15"),
                LocalTime.parse("08:00"), LocalDate.parse("2022-06-14"), LocalTime.parse("12:00"),
                LocalTime.parse("14:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("AJOUTER FINAL : " + c1.ajouterFinal(LocalTime.parse("08:00"),
                LocalTime.parse("11:00")));
        System.out.println(c1);

        System.out.println("--------------------------------------------------------");

        System.out.println("MODIFIER FINAL : " + c1.modifierFinal(LocalTime.parse("12:00"),
                LocalTime.parse("15:00")));
        System.out.println(c1);

        System.out.println("========================================================");
        */


        Horaire e22 = new Horaire(18);
        System.out.println(e22);


        boolean created = false;
        while (!created) {
            created = e22.creerCours();
            System.out.println("CREER COURS : " + created);
        }

        System.out.println(e22);


    }
}
