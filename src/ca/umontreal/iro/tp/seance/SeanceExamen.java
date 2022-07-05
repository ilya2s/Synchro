package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class SeanceExamen extends Seance {

    public SeanceExamen(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        super(type, jour, debut, fin);
    }

    @Override
    public boolean checkConflit(Seance seance) {
        if (!this.jour.equals(seance.jour)) return false;

        return (seance.debut.isAfter(this.debut) && seance.debut.isBefore(this.fin))
                || (this.debut.isAfter(seance.debut) && this.debut.isBefore(seance.fin))
                || (seance.debut.equals(this.debut) && seance.fin.equals(this.fin))
                || (this.fin.until(seance.debut, ChronoUnit.MINUTES) < 30)
                || (seance.fin.until(this.debut, ChronoUnit.MINUTES) < 30);
    }

    @Override
    public String toString() {
        return type + ": " +jour
                .format(DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.CANADA_FRENCH))
                .toUpperCase() + " (" + debut + " - " + fin + ")";
    }
}
