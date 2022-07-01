package ca.umontreal.iro.tp.cours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public record PlageHoraire(LocalDate jour, LocalTime debut, LocalTime fin) {

    @Override
    public String toString() {
        return jour
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH)
                .toUpperCase(Locale.ROOT)
                + " (" + debut + " - " + fin + ")";
    }

}
