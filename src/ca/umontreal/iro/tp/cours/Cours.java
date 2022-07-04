package ca.umontreal.iro.tp.cours;

import ca.umontreal.iro.tp.plage.Plage;
import ca.umontreal.iro.tp.plage.PlageCours;
import ca.umontreal.iro.tp.plage.PlageExamen;
import ca.umontreal.iro.tp.seance.Seance;
import ca.umontreal.iro.tp.seance.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cours {

    private String matiere;
    private int numero;
    private int credits;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private final List<Seance> seances;

    public Cours(String matiere, int numero, int credits,LocalDate dateDebut, LocalDate dateFin) {
        this.matiere = matiere;
        this.numero = numero;
        this. credits = credits;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = new ArrayList<>();
    }

    public boolean ajouterSeance(Seance seance) {
        if (seance.getPlage() instanceof PlageExamen) {     // Si on essaie de rajouter un examen
            for (Seance s : seances) {
                if (s.getPlage() instanceof PlageCours) continue;
                if (Plage.checkConflit(seance.getPlage(), s.getPlage())) return false;
            }
        } else {
            for (Seance s : seances) {
                if (Plage.checkConflit(seance.getPlage(), s.getPlage())) return false;
            }
        }

        seances.add(seance);
        return true;
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
