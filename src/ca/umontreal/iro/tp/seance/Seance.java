package ca.umontreal.iro.tp.seance;

import ca.umontreal.iro.tp.plage.Plage;
import ca.umontreal.iro.tp.plage.PlageCours;
import ca.umontreal.iro.tp.plage.PlageExamen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Seance {

    private final Type type;
    private Plage plage;

    public Seance(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        this.type = type;
        makePlage(jour, debut, fin);
    }

    private void makePlage(LocalDate jour, LocalTime debut, LocalTime fin) {
        if (Objects.equals(type, Type.Examen)) {
            plage = new PlageExamen(jour, debut, fin);
        } else {
            plage = new PlageCours(jour, debut, fin);
        }
    }

    public Type getType() {
        return type;
    }

    public Plage getPlage() {
        return plage;
    }

    @Override
    public String toString() {
        return type + ": " + plage;
    }
}
