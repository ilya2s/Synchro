package ca.umontreal.iro.tp.plage;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Plage {

    private final LocalDate jour;
    private final LocalTime debut;
    private final LocalTime fin;

    public Plage(LocalDate jour, LocalTime debut, LocalTime fin) {
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
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
}
