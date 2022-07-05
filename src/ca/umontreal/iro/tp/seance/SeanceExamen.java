package ca.umontreal.iro.tp.seance;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceExamen extends Seance {

    public SeanceExamen(Type type, LocalDate jour, LocalTime debut, LocalTime fin) {
        super(type, jour, debut, fin);
    }

    @Override
    public String toString() {
        
    }
}
