package ca.umontreal.iro.tp.cours;

public record Seance(String type, PlageHoraire plage) {

    @Override
    public String toString() {
        return type + ": " + plage;
    }

}
