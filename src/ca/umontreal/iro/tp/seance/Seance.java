package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class Seance {

    protected Type type;
    protected LocalDate jour;
    protected LocalTime debut;
    protected LocalTime fin;

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

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public boolean checkConflit(Seance seance) {
        if (!this.jour.equals(seance.jour)) return false;

        return (seance.debut.isAfter(this.debut) && seance.debut.isBefore(this.fin))
                || (this.debut.isAfter(seance.debut) && this.debut.isBefore(seance.fin))
                || (seance.debut.equals(this.debut) && seance.fin.equals(this.fin));
    }

    @Override
    public String toString() {

        return type + ": " + jour
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH)
                .toUpperCase()
                + " (" + debut + " - " + fin + ")";
    }
}
