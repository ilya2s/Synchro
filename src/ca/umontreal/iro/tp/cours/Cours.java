package ca.umontreal.iro.tp.cours;

import java.util.ArrayList;

public class Cours {
    private final String matiere;
    private final int numero;
    private final int credits;
    private final ArrayList<Seance> seances;

    public Cours(String matiere, int numero, int credits, ArrayList<Seance> seances) {
        this.matiere = matiere;
        this.numero = numero;
        this.credits = credits;
        this.seances = seances;
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
            output.append(s.toString());
            if (!s.equals(seances.get(seances.size() - 1))) output.append("; ");
        }
        output.append("]");


        return output.toString();
    }

}
