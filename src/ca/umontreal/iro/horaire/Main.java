package ca.umontreal.iro.horaire;

import ca.umontreal.iro.horaire.cours.*;

public class Main {

    public static void main(String[] args) {
        Cours c = new Cours("IFT", 1025);

        System.out.println(c);

        PlageHoraire p = new PlageHoraire(0, "MARDI : 12:00PM-02:00PM");

        Seance s = new Seance(0, c, "Théorie");
        s.setPlageHoraire(p);

        System.out.println(s);
    }
}
