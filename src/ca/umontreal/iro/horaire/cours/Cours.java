package ca.umontreal.iro.horaire.cours;

public class Cours {

    private final String matiere;
    private final int numero;

    public Cours(String matiere, int numero) {
        this.matiere = matiere;
        this.numero = numero;
    }

    public String getMatiere() {
        return this.matiere;
    }

    public int getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return this.matiere + "-" + this.numero;
    }


}
