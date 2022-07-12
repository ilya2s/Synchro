package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * La classe Seance represente une seance de cours
 */
public class Seance {

    protected Type type;    // Le type de la seance
    protected LocalDate jour;   // Le jour de la seance
    protected LocalTime debut;  // L'heure de debut de la seance
    protected LocalTime fin;    // L'heure de fin de la seance

    /**
     *
     * @param type Type de la seance
     * @param jour Le jour de la seance (date de la seance)
     * @param debut L'heure de debut de la seance
     * @param fin L'heure de fin de la seance
     */
    public Seance(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        this.type = type;
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * @return type - le type de la seance
     */
    public Type getType() {
        return type;
    }

    /**
     * @return Le jour (date) de la seance
     */
    public LocalDate getJour() {
        return jour;
    }

    /**
     * @return L'heure de debut de la seance
     */
    public LocalTime getDebut() {
        return debut;
    }

    /**
     * Retourne vrai s'il y a un conflit d'horaire entre l'objet Seance actuel et la Seance passée en parametre.
     * @param seance Un objet Seance
     * @return Vrai s'il y a un conflit d'horaire, Faux sinon.
     */
    public boolean isConflict(Seance seance) {

        // si ne se passent pas au meme jour de semaine aucun conflfit
        if (!this.jour.getDayOfWeek().equals(seance.jour.getDayOfWeek())) return false;

        // conditions qui causent un conflit entre deux seances
        return (seance.debut.isAfter(this.debut) && seance.debut.isBefore(this.fin))
                || (this.debut.isAfter(seance.debut) && this.debut.isBefore(seance.fin))
                || (seance.debut.equals(this.debut) || seance.fin.equals(this.fin));
    }

    /**
     * Affiche la seance avec le type, le jour de semaine en francais et l'heure de debut et de fin
     * Cette methode Override le toString() de Seance
     * @return String formate de la Seance
     */
    @Override
    public String toString() {

        return type + ": " + jour
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH)
                .toUpperCase()
                + " (" + debut + " - " + fin + ")";
    }
}
