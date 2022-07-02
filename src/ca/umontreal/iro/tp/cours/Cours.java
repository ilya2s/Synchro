package ca.umontreal.iro.tp.cours;

import ca.umontreal.iro.tp.seance.Seance;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cours {
    private final String matiere;
    private final int numero;
    private final int credits;

    private LocalDate debut;
    private LocalDate fin;
    private final ArrayList<Seance> seances;

    public Cours(String matiere, int numero, int credits, LocalDate debut, LocalDate fin, ArrayList<Seance> seances) {
        this.matiere = matiere;
        this.numero = numero;
        this.credits = credits;
        this.debut = debut;
        this.fin = fin;
        this.seances = seances;
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

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }


    public LocalDate getDateIntra() {
        for (Seance s : seances) {
            if (s.getType().equalsIgnoreCase("intra")) {
                return s.getPlage().jour();
            }
        }
        return null;
    }

    public LocalDate getDateFinal() {
        for (Seance s : seances) {
            if (s.getType().equalsIgnoreCase("final")) {
                return s.getPlage().jour();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output
                .append(matiere)
                .append("-")
                .append(numero)
                .append(" (")
                .append(credits)
                .append(" crédits): \n");

        output.append("Horaire -> [");
        for (Seance s : seances) {
            if (!s.getType().equalsIgnoreCase("intra") && !s.getType().equalsIgnoreCase("final")) {
                output.append(s);
                if (!s.equals(seances.get(seances.size() - 1))) output.append("; ");
            }
        }
        output.append("]\n");

        output.append("Examens -> [");
        for (Seance s : seances) {
            if (s.getType().equalsIgnoreCase("intra") || s.getType().equalsIgnoreCase("final")) {
                output
                        .append(s.getPlage().jour())
                        .append(" ")
                        .append(s);
                if (!s.equals(seances.get(seances.size() - 1))) output.append("; ");
            }
        }
        output.append("]");


        return output.toString();
    }

}
