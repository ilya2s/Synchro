package ca.umontreal.iro.tp.cours;

import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.SeanceExamen;
import ca.umontreal.iro.tp.seance.Type;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cours {

    private String matiere;
    private int numero;
    private int credits;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private final List<Seance> seances;

    public Cours(String matiere, int numero, int credits, LocalDate dateDebut, LocalDate dateFin) {
        this.matiere = matiere;
        this.numero = numero;
        this. credits = credits;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = new ArrayList<>();
    }

    public boolean ajouterSeances(Type type, DayOfWeek jour, LocalTime debut, LocalTime fin) {

        // Date du prochain jour de la semaine depuis dateDebut
        LocalDate start = dateDebut.with(TemporalAdjusters.next(jour));

        // toutes les dates des séances selon le jour de semaine entre dateDebut && dateFin
        List<LocalDate> dates = start
                .datesUntil(dateFin, Period.ofWeeks(1)).toList();

        // Si en conflit avec une autre seance ou un examen ne pas ajouter
        Seance seance = new Seance(type, start, debut, fin);
        for (Seance s : seances) {
            if (s.isConflict(seance)) return false;
        }

        // Ajouter les seances
        for (LocalDate date : dates) {
            seances.add(new Seance(type, date, debut, fin));
        }

        return true;
    }

    public boolean supprimerSeances(Type type, DayOfWeek jour, LocalTime debut) {
        return seances.removeIf(seance -> Objects.equals(seance.getType(), type)    // si meme type de seance
                && Objects.equals(seance.getJour().getDayOfWeek(), jour)    // si meme jour de semaine
                && Objects.equals(seance.getDebut(), debut));   // si meme heure de debut
    }

    public boolean modifierSeances(Type type,
                                DayOfWeek jour,
                                LocalTime debut,
                                DayOfWeek autreJour,
                                LocalTime autreDebut,
                                LocalTime autreFin) {

        // supprimer les anciennes seances et remplacer par les nouvelles
        return supprimerSeances(type, jour, debut) && ajouterSeances(type, autreJour, autreDebut, autreFin);
    }

    public boolean ajouterIntra(LocalDate jour, LocalTime debut, LocalTime fin) {
        SeanceExamen intra = new SeanceExamen(Type.Intra, jour, debut, fin);

        // Si en conflit avec un examen ne pas ajouter
        // Intra peu etre en meme temps qu'un cours
        for (Seance s : seances) {
            if (s instanceof SeanceExamen && s.isConflict(intra)) {
                return false;
            }
        }

        return seances.add(intra);
    }

    public boolean supprimerIntra(LocalDate jour, LocalTime debut) {
        return seances.removeIf(s -> Objects.equals(s.getJour(), jour)     // Si le meme jour
                && Objects.equals(s.getDebut(), debut));    // si meme heure de debut
    }

    public boolean modifierIntra(LocalDate jour,
                                 LocalTime debut,
                                 LocalDate autreJour,
                                 LocalTime autreDebut,
                                 LocalTime autreFin) {
        return supprimerIntra(jour, debut) && ajouterIntra(autreJour, autreDebut, autreFin);
    }

    public boolean ajouterFinal(LocalTime debut, LocalTime fin) {
        for (Seance s : seances) {
            if (s instanceof SeanceExamen && Objects.equals(s.getType(), Type.Final)) return false;
        }

        return seances.add(new SeanceExamen(Type.Final, dateFin, debut, fin));
    }

    public boolean supprimerFinal() {
        return seances.removeIf(s -> s instanceof SeanceExamen && Objects.equals(s.getType(), Type.Final));
    }

    public boolean modifierFinal(LocalTime debut, LocalTime fin) {
        return supprimerFinal() && ajouterFinal(debut, fin);
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<Seance> getSeances() {
        return Collections.unmodifiableList(seances);
    }

    public List<Seance> getSeances(Type type) {
        List<Seance> seances = new ArrayList<>();
        for (Seance seance : this.seances) {
            if (seance.getType().equals(type)) {
                seances.add(seance);
            }
        }
        return seances.isEmpty() ? null : seances;
    }

    private String stringifyCours() {
        if (seances.isEmpty()) return "\n";

        StringBuilder output = new StringBuilder();

        output.append(" [");
        Seance seen = seances.get(0);

        for (Seance seance: seances) {
            if (!(seance instanceof SeanceExamen) &&
                    (!Objects.equals(seance.getType(), seen.getType())
                    || !Objects.equals(seance.getJour().getDayOfWeek(), seen.getJour().getDayOfWeek())
                    || !Objects.equals(seance.getDebut(), seen.getDebut()))) {
                output
                        .append(seance)
                        .append("; ");

                seen = seance;
            }
        }

        int semi = output.lastIndexOf("; ");
        if (semi != -1) output.delete(semi, semi + 2);
        output.append("]\n");

        return output.toString();
    }

    private String stringifyExamens() {
        if (seances.isEmpty()) return "\n";

        StringBuilder output = new StringBuilder();

        output.append(" [");
        for (Seance seance: seances) {
            if (seance instanceof SeanceExamen) {
                output
                        .append(seance)
                        .append("; ");
            }
        }

        int semi = output.lastIndexOf("; ");
        if (semi != -1) output.delete(semi, semi + 2);
        output.append("]");

        return output.toString();
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------\n" +
                matiere.toUpperCase() +
                "-" +
                numero +
                " (" +
                credits +
                " crédits):\n" +
                "Cours ->" +
                stringifyCours() +
                "Examens ->" +
                stringifyExamens() +
                "\n----------------------------------------------------------";
    }
}
