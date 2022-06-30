package ca.umontreal.iro.horaire.cours;

import java.time.LocalDate;

public class Seance {
    private final int id;
    private final Cours cours;
    private String type;
    private PlageHoraire plageHoraire;

    public Seance(int id, Cours cours, String type) {
        this.id = id;
        this.cours = cours;
        this.type = type;
    }

    public void setPlageHoraire(PlageHoraire plage) {
        this.plageHoraire = plage;
    }

    public int getId() {
        return this.id;
    }

    public Cours getCours() {
        return this.cours;
    }

    public PlageHoraire getPlageHoraire() {
        return this.plageHoraire;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + cours.toString() + ", " + type + ", " + plageHoraire.getPlage() + "]";
    }


}
