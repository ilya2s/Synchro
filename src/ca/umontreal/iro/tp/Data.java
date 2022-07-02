package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.cours.Cours;
import ca.umontreal.iro.tp.seance.PlageHoraire;

import java.util.ArrayList;

public class Data {

    private ArrayList<Cours> cours;
    private ArrayList<PlageHoraire> plages;
    private int nbCours = 0;

    public Data() {
        intialiser();
    }

    private Data intialiser() {



        return this;
    }

    public ArrayList<Cours> getCours() {
        return cours;
    }

    public ArrayList<PlageHoraire> getPlages() {
        return plages;
    }

    public int getNbCours() {
        return nbCours;
    }
}
