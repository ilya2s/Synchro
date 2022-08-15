# Synchro
Un système de gestion d'horaire de cours d'un étudiant implémenté en java

1. **Classes, constructeurs et méthodes![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.001.png)**
2. **Énumération Type** public enum Type

***(1.1) Constantes***

- Final, Intra, Theorie, Pratique

Cet énumération nous permet de différencier les objets « Seance » entre eux (théorie ou pratique) ainsi que les objets « SeanceExamen » entre eux (Final ou intra). Cela nous a semblé plus efficace que de créer une sous classe de « Seance » pour chaque type de séance possible.

2. **Classe Seance**

public class Seance

***(2.1) Constructeur***

- public Séance (Type type, LocalDate jour, LocalTime debut, LocalTime fin)

Prend en paramètre le type (Théorie ou Pratique), le jour de le séance (c.à.d la date de la séance), l’heure de début de la séance ainsi que l’heure de fin de la séance.

***(2.2) Attributs***

- protected Type type # décrit si une séance est théorique ou pratique
- protected LocalDate jour # la date de la séance
- protected LocalTime debut # l’heure de début de la séance
- protected LocalTime fin # l’heure de fin de la séance

Nous avons donné un modificateur d’accès « protected » aux attributs pour permettre à la classe Enfant « SeanceExamen » de manipuler directement ces valeurs.

Pour ce qui est du jour et des heures, nous avons choisi d’utiliser « LocalDate » et « LocalTime » qui sont aujourd’hui le standard java en ce qui concerne la gestion d’éléments temporels.

3. **Classe SeanceExamen**

public class SeanceExamen extends Seance

***(3.1) Constructeur***

- public SeanceExamen(Type type, LocalDate jour, LocalTime debut, LocalTime fin)

Prend en arguments les mêmes paramètres que son parent Seance. C’est la méthode de vérification de conflits et l’affichage qui va différencier un objet « Seance » d’un objet « SeanceExamen ».

Le type quant à lui peu être soit Final ou Intra.

4. **Classe Cours** public class Cours ***(4.1) Constructeur***
- public Cours(String matiere, int numero, int credits, LocalDate dateDebut, LocalDate dateFin)

Prend en paramètre la matière du cours (ex. : IFT), le numéro du cours (ex. : 1025), le nombre de crédits, la date début ainsi que la date de fin du cours qui représente aussi la date de l’examen final.

***(4.2) Attributs***

- private final String matiere # la matière du cours
- private final int numero # le numéro du cours
- private final int credits # le nombre de crédits du cours
- private final LocalDate dateDebut # la date de début du cours
- private final LocalDate dateFin # date de fin du cours (& date du final)
- private final List<Seance> seances # la liste de séances du cours

Nous avons décidé de mettre rendre ces attributs constants vue qu’au point de vue d’une gestion d’horaire, si un cours est créée avec ces informations là, il ne fait pas de sens de les modifier. C’est plutôt le contenue de la liste des séances qui sera modifié.

5. **Classe Horaire** Public class Horaire ***(5.1) Constructeur***
- public Horaire(int creditsMax)

Prend en paramètre le nombre maximum de crédit qu’un horaire peu avoir.

***(5.2) Attributs***

- private final int creditsMax # le nombre maximum de crédits
- private int crédits # le nombre de crédits inscrits
- private final List<Cours> coursDisponibles # cours disponibles à s’inscrire
- private final List<Cours> coursInscrits # cours inscrits à l’horaire

Dans cette classe, l’attribut « coursDisponibles » va stocker les cours crées et les rendre disponible à l’inscription.

2. **Mode d’emploi![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.001.png)**

Une fois le programme lancé, l’utilisateur doit entrer l’une des six options qui s’offrent à lui :

1. Créer un cours
1. Supprimer un cours
1. Modifier un cours
1. Inscrire un cours à l’horaire
1. Désinscrire un cours de l’horaire

   0. Quitter
1. **Créer un cours**

L’utilisateur doit entrer la matière du cours, son numéro, le nombre de crédits, la date de début et la date de fin du cours (qui représente aussi la date de l’examen final). Ensuite, l’utilisateur doit entrer l’heure de début et l’heure de fin de l’examen final. Une fois les données entrées, l’utilisateur doit entrer un des huit choix qui s’offrent à lui en entrant le numéro du choix :

1) Ajouter séances
1) Supprimer séances
1) Modifier séances
1) Ajouter examen intra
1) Supprimer examen intra
1) Modifier examen intra
1) Modifier examen final

(0) Sauvegarder cours

***(1.1) Ajouter séances***

L’utilisateur doit choisir le type de la séance : Théorique : il doit entrer la lettre « T », Pratique : il doit entrer la lettre « P ».

En suite, l’utilisateur doit choisir le jour de semaine de la séance (du lundi au vendredi) puis il doit entrer l’heure de début et l’heure de fin de la séance. Une fois terminé, la séance est créer et il revient au menu de création de cours.

***(1.2) Supprimer séances***

L’utilisateur doit choisir le type , le jour de semaine et l’heure de début de la séance à supprimer. Ces données vont permettre au programme de retrouver la séance et de la supprimer de la liste de séances du cours.

***(1.3) Modifier séances***

L’utilisateur doit choisir le type, le jour de semaine, et l’heure de début de la séance à modifier. Ensuite, il doit entrer le nouveau jour de semaine, une nouvelle heure de début et une une nouvelle heure de fin pour cette séance.

***(1.4) Ajouter un examen intra***

L’utilisateur doit entrer la date, l’heure de début et l’heure de fin de l’examen intra à ajouter. ***(1.5) Supprimer un examen intra***

L’utilisateur doit entrer la date et l’heure de début de l’examen intra à supprimer.

***(1.6) Modifier un examen intra***

L’utilisateur doit entrer la date et l’heure de début de l’examen intra à modifier. Ensuite, il doit entrer la nouvelle date, la nouvelle heure de début et la nouvelle heure de fin de cet examen.

***(1.7) Modifier l’examen final***

L’utilisateur doit entrer la nouvelle heure de début et la nouvelle heure de fin de l’examen final. ***(1.8) Sauvegarder cours***

L’utilisateur peu sauvegarder ses modifications et revenir au menu principal.

2. **Supprimer un cours**

Cela supprime un cours de la liste de cours disponible et le désinscrit s’il est inscrit à l’horaire si il existe. L’utilisateur doit entrer la matière et le numéro du cours à supprimer.

3. **Modifier un cours**

L’utilisateur doit entrer la matière et le numéro du cours à modifier, si le cours existe, le menu de modification de cours expliqué dans la section 1 (Créer cours).

4. **Inscrire cours à l’horaire**

L’utilisateur doit entrer la matière et le numéro du cours à inscrire. S’il existe, il sera ajouté à la lise des cours inscrits.

5. **Désinscrire cours de l’horaire**

L’utilisateur doit entrer la matière et le numéro du cours à désinscrire. Si celui-ci est présent dans la liste de cours inscrits, il en sera retiré.

6. **Quitter**

Une fois que l’utilisateur à inscrit les cours qu’il a choisi il peut quitter le programme.

3. **Difficultés rencontrées, critiques et suggestions![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.002.png)**

En ce qui concerne les difficultés rencontrées, le plus dur été de s’assurer que toutes les exceptions possibles, causées par les entrées de l’utilisateur, soient manipulées adéquatement.

Le fait que l’énoncé du TP soit plutôt vague ne nous as pas plu. Nous suggérons pour le futur un peu plus de précision sur ce qui est demandé. Vue qu’un énoncé vague peu mener à des ambiguïté qui pourraient nuire au travail.

4. **Diagramme UML![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.003.png)**



|**Horaire**|
| - |
|-creditsMax : int -coursDisponibles : List<Cours> -coursInscrits : List<Cours> -credits : int|
|<p>+Horaire(creditsMax : int)</p><p>+creerCours() : boolean</p><p>+supprimerCours(matiere : String, int numero) : boolean +modifierCours(matiere : String, numero : int) : boolean +InscrireCours(matiere : String, numero : int) : boolean +desinscrireCours(matiere : String, numero : int) : boolean -stringifyCoursDisponibles() : String -fetchCoursDisponible(matiere : String, numero : int) : Cours -fetchCoursInscrit(matiere : String, numero : int) : Cours +stringifyCoursDisponibles() : String -stringifyCoursInscrits() : String</p><p>+toString() : String</p>|
Visual Paradigm Online Free Edition



|**Synchro**|
| - |
|+main(args : String[]) : void -isNumeric(String s) : boolean|
![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.004.png)

**<<enumeration>> Type![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.005.png)![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.006.png)**

Theorie Pratique Intra Final![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.007.png)

|**Cours**|
| - |
|<p>-matiere : String</p><p>-numero : int</p><p>-credits : int</p><p>-dateDebut : LocalDate -dateFin : LocalDate</p><p>-seances : List<Seance></p>|
|<p>+Cours(matiere : String, numero : int, credits : int, dateDebut : LocalDate, dateFin : LocalDate)</p><p>+ajouterSeances(type: Type, jour: DayOfWeek, debut : LocalTime, fin : LocalTime) : boolean</p><p>+supprimerSeances(type : Type, jour: DayOfWeek, debut: LocalTime) : boolean</p><p>+modifierSeances(type: Type, jour: DayOfWeek, debut: LocalTime, autreJour : LocalDate, autreDebut: LocalTime, autreFin: LocalTime) : boolean +ajouterIntra(jour : LocalDate, debut: LocalTime, fin: LocalTime) : boolean</p><p>+supprimerIntra(jour: LocalDate, debut : LocalTime) : boolean</p><p>+modifierIntra(jour: LocalDate, debut: LocalTime, autreJour: LocalDate, autreDebut: LocalTime, autreFin: LocalTime) : boolean +ajouterFinal(debut: LocalTime, fin: LocalTime) : boolean</p><p>-supprimerFinal() : boolean</p><p>+modifierFinal(debut: LocalTime, fin: LocalTime) : boolean</p><p>+getMatiere() : String</p><p>+getNumero() : int</p><p>+getCredits() : int</p><p>+getDateDebut() : LocalDate</p><p>+getDateFin() : LocalDate</p><p>+getSeances() : List<Seance></p><p>-stringifyCours() : String</p><p>-stringifyExamens() : String</p><p>+toString() : String</p>|

|**Seance**|
| - |
|<p>-type : Type</p><p>-jour : LocalDate -debut : LocalTime -fin : LocalTime</p>|
|<p>+Seance(type: Type, jour: LocalDate, debut: LocalTime, fin: LocalTime) +getType() : Type</p><p>+getJour() : LocalDate</p><p>+getDebut() : LocalTime</p><p>+isConflict(seance : Seance) : boolean</p><p>+toString() : String</p>|
![](Aspose.Words.a8c1b032-a076-4285-98aa-c518b57a5a54.008.png)



|**SeanceExamen**|
| - |
|<p>+SeanceExamen(jour : LocalDate, debut : LocalTime, fin : LocalTime) +toString(): String</p><p>+isConflict(seance : Seance) : bool Ve ia sn ual Paradigm Online Free Editio</p>|

6
