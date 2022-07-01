package ca.umontreal.iro.tp.cours;

import java.time.LocalDate;
import java.time.LocalTime;

public record PlageHoraire(LocalDate jour, LocalTime debut, LocalTime fin) {

    @Override
    public String toString() {
        return jour.getDayOfWeek().toString() + " (" + debut + " - " + fin + ")";
    }

}
