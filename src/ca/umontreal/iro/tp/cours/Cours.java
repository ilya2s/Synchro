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

/**
 * La classe Cours represente un cours universitaire pouvant faire partie d'un horaire d'etudiant
 */
public class Cours {

    private final String matiere;   // La matiere du cours (ex.: IFT)
    private final int numero;       // le numero du cours (ex.: 1025)
    private final int credits;      // le nombre de credits du cours
    private final LocalDate dateDebut;  // La date du debut du cours
    private final LocalDate dateFin;    // La date de fin du cours (aussi la date de l'examen final)
    private final List<Seance> seances;     // Liste des seances du cours

    /**
     * Constructeur d'un objet Cours
     * @param matiere La matiere du cours (ex.: IFT)
     * @param numero Le numero du cours (ex.: 1025)
     * @param credits Le nombre de credits du cours
     * @param dateDebut La date de debut du cours en LocalDate
     * @param dateFin La date de fin du cours en LocalDate
     */
    public Cours(String matiere, int numero, int credits, LocalDate dateDebut, LocalDate dateFin) {
        this.matiere = matiere;
        this.numero = numero;
        this. credits = credits;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = new ArrayList<>();
    }

    /**
     * Creer les séances de cours et les ajouter à la liste de seances du cours si elles n'entrent pas en conflit avec
     * les autres.
     * @param type Le type de la seance à ajouter au cours
     * @param jour Le jour de semaine de la seance à ajouter au cours
     * @param debut L'heure de debut de la seance à ajouter au cours
     * @param fin L'heure de fin de la seance à ajouter au cours
     * @return vrai si la seance est ajouté avec succees, sinon faux.
     */
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

    /**
     * Supprimer une seance ayant les informations passées en paramètre de la liste des seances du cours.
     * Pour trouver une seance, on a seulement besoin des parametres suivants :
     * @param type Le type de la seance à supprimer
     * @param jour Le jour de semaine de la seance à supprimer
     * @param debut L'heure de debut de la seance à supprimer
     * @return vrai si la seance est supprimée avec succès, sinon faux.
     */
    public boolean supprimerSeances(Type type, DayOfWeek jour, LocalTime debut) {
        return seances.removeIf(seance -> Objects.equals(seance.getType(), type)    // si meme type de seance
                && Objects.equals(seance.getJour().getDayOfWeek(), jour)    // si meme jour de semaine
                && Objects.equals(seance.getDebut(), debut));   // si meme heure de debut
    }

    /**
     * Modifier une seance ayant le type, le jour de semaine et l'heure de debut passés en paramètre par un autre jour
     * de semaine, une autre heure de debut et une autre heure de fin. <br><br>
     *
     * Cette methode va supprimer les anciennes séances et creer des nouvelles séances avec les nouvelles informations.
     *
     * @param type Le tyoe de la seance à modifier
     * @param jour Le jour de semaine de la seance à modifier
     * @param debut L'heure de debut de la seance à modifer
     * @param autreJour Le nouveau jour de semaine de la seance
     * @param autreDebut La nouvelle heure de debut de la seance
     * @param autreFin La nouvelle heure de fin de la seance
     * @return vrai si la seance est modifiée avec succès, sinon faux.
     */
    public boolean modifierSeances(Type type,
                                DayOfWeek jour,
                                LocalTime debut,
                                DayOfWeek autreJour,
                                LocalTime autreDebut,
                                LocalTime autreFin) {

        // supprimer les anciennes seances et remplacer par les nouvelles
        return supprimerSeances(type, jour, debut) && ajouterSeances(type, autreJour, autreDebut, autreFin);
    }

    /**
     * Creer et ajouter un examen intra à la liste de seances du cours.
     * @param jour Date de l'examen intra
     * @param debut heure de debut de l'examen intra
     * @param fin heure de fin de l'examen intra
     * @return vrai si l'examen intra est ajouté avec succès, sinon faux.
     */
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

    /**
     * Supprimer un examen intra de la liste de seances du cours.
     * @param jour Date de l'examen intra à supprimer
     * @param debut Heure de début de l'examen intra à supprimer
     * @return vrai si l'examen intra est supprimé avec succès, sinon faux.
     */
    public boolean supprimerIntra(LocalDate jour, LocalTime debut) {
        return seances.removeIf(s -> Objects.equals(s.getType(), Type.Intra)    // si de type intra
                && Objects.equals(s.getJour(), jour)     // Si le meme jour
                && Objects.equals(s.getDebut(), debut));    // si meme heure de debut
    }

    /**
     * Modifier un examen intra ayant la date et l'heure de debut passés en paramètre par un autre examen intra ayant
     * une autre date, une autre heure de debut et une autre heure de fin. <br><br>
     *
     * Cette methode va supprimer l'ancien examen intra et va en creer un nouveau avec les nouvelles informations.
     * @param jour Date de l'examen intra à modifier
     * @param debut Heure de début de l'examen intra à modifier
     * @param autreJour Nouvelle Date de l'examen intra
     * @param autreDebut Nouvelle heure de début de l'examen intra
     * @param autreFin Nouvelle heure de fin de l'examen intra
     * @return vrai si l'examen intra est modifié avec succès, sinon faux.
     */
    public boolean modifierIntra(LocalDate jour,
                                 LocalTime debut,
                                 LocalDate autreJour,
                                 LocalTime autreDebut,
                                 LocalTime autreFin) {

        // supprimer l'ancien examen intra et creer un nouveau
        return supprimerIntra(jour, debut) && ajouterIntra(autreJour, autreDebut, autreFin);
    }

    /**
     * Creer un examen final et l'ajouter à la liste de seances du cours. <br><br>
     *
     * La date de l'examen final est toujours la date de fin du cours.
     *
     * @param debut Heure de debut de l'examen final
     * @param fin Heure de fin de l'examen final
     * @return vrai si l'examen final est ajouté avec succès, sinon faux.
     */
    public boolean ajouterFinal(LocalTime debut, LocalTime fin) {
        // Si le cours contient deja un examen final quitter
        for (Seance s : seances) {
            if (s instanceof SeanceExamen && Objects.equals(s.getType(), Type.Final)) return false;
        }

        return seances.add(new SeanceExamen(Type.Final, dateFin, debut, fin));
    }

    /**
     * Supprimer un examen final de la liste de séances du cours.
     *
     * @return vrai si l'examen est supprimé avec succès, sinon faux.
     */
    private boolean supprimerFinal() {
        return seances.removeIf(s -> s instanceof SeanceExamen && Objects.equals(s.getType(), Type.Final));
    }

    /**
     * Modifier un examen final par un autre examen final ayant une autre heure de debut et une autre heure de fin. <br><br>
     *
     *  Cette methode va supprimer l'ancien examen final et va en créer un nouveau avec les nouvelles informations.
     * @param debut Nouvelle heure de début de l'examen final
     * @param fin Nouvelle heure de fin de l'examen final
     * @return vrai si l'examen est modifié avec succès, sinon faux.
     */
    public boolean modifierFinal(LocalTime debut, LocalTime fin) {

        // supprimer l'examen et en ajouter un nouveau
        return supprimerFinal() && ajouterFinal(debut, fin);
    }

    /**
     * @return la matiére du cours (ex.: IFT)
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * @return le numero du cours (ex.: 1025)
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return le nombre de crédits du cours
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @return La date de début du cours
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * @return La date de fin du cours
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * @return la liste de séances du cours
     */
    public List<Seance> getSeances() {
        return Collections.unmodifiableList(seances);
    }

    /**
     * @return affichage formaté des séances théoriques et pratiques
     */
    private String stringifyCours() {
        if (seances.isEmpty()) return "\n";     // si aucun cours afficher ligne vide

        StringBuilder output = new StringBuilder();

        output.append(" [");
        Seance seen = seances.get(0);   // mettre la premiere seance comme vue

        // pour chaque seance du cours
        for (Seance seance: seances) {
            if (!(seance instanceof SeanceExamen) &&    // si ce n'est pas un examen

                    // ET

                    // qu'elle n'est pas du meme type que la seance vue
                    (!Objects.equals(seance.getType(), seen.getType())

                            // ou qu'elle n'est pas du meme jour de semaine que la seance vue
                            || !Objects.equals(seance.getJour().getDayOfWeek(), seen.getJour().getDayOfWeek())

                            // ou qu'elle n'a pas la meme heure de début que la seance vue
                            || !Objects.equals(seance.getDebut(), seen.getDebut()))) {

                // ajouter la seance à l'affichage
                output
                        .append(seance)
                        .append("; ");

                // rendre cette seance comme vue
                seen = seance;
            }
        }

        // Supprimer le dernier ";"
        int semi = output.lastIndexOf("; ");
        if (semi != -1) output.delete(semi, semi + 2);

        output.append("]\n");

        return output.toString();
    }

    /**
     * @return Affichage formaté des séances d'examens (intra et final)
     */
    private String stringifyExamens() {
        if (seances.isEmpty()) return "\n";

        StringBuilder output = new StringBuilder();

        output.append(" [");

        // Ajouter toutes les seances d'examens à l'affichage
        for (Seance seance: seances) {
            if (seance instanceof SeanceExamen) {
                output
                        .append(seance)
                        .append("; ");
            }
        }

        // supprimer le dernier ";"
        int semi = output.lastIndexOf("; ");
        if (semi != -1) output.delete(semi, semi + 2);
        output.append("]");

        return output.toString();
    }

    /**
     * Affiche le Cours avec la matière, le numéro, les credits, les seances formatées et les seances
     * d'examens formatés.
     * @return Affichage formaté du cours.
     */
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
                "\n----------------------------------------------------------\n";
    }
}
