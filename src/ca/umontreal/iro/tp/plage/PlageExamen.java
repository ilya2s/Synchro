package ca.umontreal.iro.tp.plage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class PlageExamen extends Plage {

    public PlageExamen(LocalDate jour, LocalTime debut, LocalTime fin) {
        super(jour, debut, fin);
    }

    @Override
    public String toString() {
        return jour
                .format(DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.CANADA_FRENCH))
                .toUpperCase() + " (" + debut + " - " + fin + ")";
    }
}
