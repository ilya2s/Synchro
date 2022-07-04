package ca.umontreal.iro.tp.plage;

import ca.umontreal.iro.tp.seance.Seance;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Plage {

    protected final LocalDate jour;
    protected final LocalTime debut;
    protected final LocalTime fin;

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

    public static boolean checkConflit(Plage p1, Plage p2) {
        if (!p1.getJour().getDayOfWeek().equals(p2.getJour().getDayOfWeek())) return false;

        return (p2.debut.isAfter(p1.debut) && p2.debut.isBefore(p1.fin))
                || (p1.debut.isAfter(p2.debut) && p1.debut.isBefore(p2.fin))
                || (p2.debut.equals(p1.debut) && p2.fin.equals(p1.fin));
    }
}
