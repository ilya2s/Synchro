package ca.umontreal.iro.tp;

import ca.umontreal.iro.tp.cours.Cours;
import ca.umontreal.iro.tp.plage.Plage;

import java.util.ArrayList;

public class Data {

    private ArrayList<Cours> cours;
    private ArrayList<Plage> plages;
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

    public ArrayList<Plage> getPlages() {
        return plages;
    }

    public int getNbCours() {
        return nbCours;
    }
}
