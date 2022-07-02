package ca.umontreal.iro.tp.plage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class PlageCours extends Plage{

    public PlageCours(LocalDate jour, LocalTime debut, LocalTime fin) {
        super(jour, debut, fin);
    }

    @Override
    public String toString() {
        return jour
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH)
                .toUpperCase()
                + " (" + debut + " - " + fin + ")";
    }
}
