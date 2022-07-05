package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Seance {

    private Type type;
    private LocalDate jour;
    private LocalTime debut;
    private LocalTime fin;

    public Seance(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        this.type = type;
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getJour() {
        return jour;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public static boolean checkConflit(Seance s1, Seance s2) {

    }

    @Override
    public String toString() {
        return type + ": " + plage;
    }
}
