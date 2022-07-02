package ca.umontreal.iro.tp.plage;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlageCours extends Plage{

    public PlageCours(LocalDate jour, LocalTime debut, LocalTime fin) {
        super(jour, debut, fin);
    }

    @Override
    public String toString() {
        return "";
    }
}
