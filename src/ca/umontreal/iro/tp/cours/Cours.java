package ca.umontreal.iro.tp.cours;

import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cours {

    private final String matiere;
    private final int numero;
    private final int credits;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;
    private final List<Seance> seances;

    public Cours(String matiere, int numero, int credits,LocalDate dateDebut, LocalDate dateFin) {
        this.matiere = matiere;
        this.numero = numero;
        this. credits = credits;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = new ArrayList<>();
    }

    public void ajouterSeance(Seance seance) {
        seances.add(seance);
    }

    public String getMatiere() {
        return matiere;
    }

    public int getNumero() {
        return numero;
    }

    public int getCredits() {
        return credits;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
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
        StringBuilder output = new StringBuilder();

        for (Seance seance: seances) {
            if (!seance.getType().equals(Type.Intra) && !seance.getType().equals(Type.Final)) {
                output
                        .append(seance)
                        .append("; ");
            }
        }

        int semi = output.lastIndexOf("; ");
        output.delete(semi, semi + 2);
        output.append("]\n");

        return output.toString();
    }

    private String stringifyExamens() {
        StringBuilder output = new StringBuilder();

        for (Seance seance: seances) {
            if (!seance.getType().equals(Type.Theorie) && !seance.getType().equals(Type.Pratique)) {
                output
                        .append(seance)
                        .append("; ");
            }
        }

        int semi = output.lastIndexOf("; ");
        output.delete(semi, semi + 2);
        output.append("]\n");

        return output.toString();
    }

    @Override
    public String toString() {
        return matiere.toUpperCase() +
                "-" +
                numero +
                " (" +
                credits +
                " crédits):\n" +
                "Cours -> [" +
                stringifyCours() +
                "Examens -> [" +
                stringifyExamens();
    }
}
