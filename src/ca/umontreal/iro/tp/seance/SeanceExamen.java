package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * La classe SeanceExamen est une sous-classe de Seance. Elle représente une Seance d'examen (Intra ou Final)
 */
public class SeanceExamen extends Seance {

    /**
     *
     * @param type Type de la séance (Intra ou Final)
     * @param jour Le jour (date) de la seance
     * @param debut L'heure de debut de la seance
     * @param fin L'heure de fin de la seance
     */
    public SeanceExamen(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        super(type, jour, debut, fin);
    }

    /**
     * Retourne vrai s'il y a un conflit entre une seance d'examen et une autre seance d'examen passee en parametre
     * @param seance Un objet Seance
     * @return vrai s'il y a un conflit d'horaire, faux sinon.
     */
    @Override
    public boolean isConflict(Seance seance) {

        // Si les examens ne sont pas au meme jour de semaine pas de conflit
        if (!this.jour.equals(seance.jour)) return false;

        // conditions qui causent un conflit entre deux examens
        return (seance.debut.isAfter(this.debut) && seance.debut.isBefore(this.fin))
                || (this.debut.isAfter(seance.debut) && this.debut.isBefore(seance.fin))
                || (seance.debut.equals(this.debut) && seance.fin.equals(this.fin))
                || (Math.abs(this.fin.until(seance.debut, ChronoUnit.MINUTES)) < 30)
                || (Math.abs(seance.fin.until(this.debut, ChronoUnit.MINUTES)) < 30);
    }

    /**
     * Affiche la seance d'examen (differament d'une Seance reguliere), On affiche la date de l'examen en plus
     * du jour de la semaine
     * @return String formate de la Seance d'examen
     */
    @Override
    public String toString() {
        return type + ": " +jour
                .format(DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.CANADA_FRENCH))
                .toUpperCase() + " (" + debut + " - " + fin + ")";
    }
}
