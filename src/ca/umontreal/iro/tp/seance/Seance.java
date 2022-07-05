package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
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
        if (!s1.jour.equals(s2.jour)) return false;

        return (s2.debut.isAfter(s1.debut) && s2.debut.isBefore(s1.fin))
                || (s1.debut.isAfter(s2.debut) && s1.debut.isBefore(s2.fin))
                || (s2.debut.equals(s1.debut) && s2.fin.equals(s1.fin));
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
