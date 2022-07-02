package ca.umontreal.iro.tp.seance;

public class Seance {
    private final String type;
    private final PlageHoraire plage;

    public Seance(String type, PlageHoraire plage) {
        this.type = type;
        this.plage = plage;
    }

    public String getType() {
        return type;
    }

    public PlageHoraire getPlage() {
        return plage;
    }

    @Override
    public String toString() {
        return type + ": " + plage;
    }
}
