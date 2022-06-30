package ca.umontreal.iro.horaire.cours;

public class PlageHoraire {
    private int id;
    private String plage;

    public PlageHoraire(int id, String plage) {
        this.id = id;
        this.plage = plage;
    }

    public int getId() {
        return this.id;
    }

    public String getPlage() {
        return this.plage;
    }
}
