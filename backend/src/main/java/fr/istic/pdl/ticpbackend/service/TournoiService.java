package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
/**
 * Ce service permet de créer les groupes et les tableaux du tournoi
 */
@Service
@AllArgsConstructor
public class TournoiService {
    TournoiRepository repository;
    PouleRepository pouleRepository;
    TableauRepository tableauRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;

    /**
     * Permet de retourner le tournoi en cours
     * @return un tournoi en cours
     */
    public Tournoi getTournoi(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Tournoi inexistant !");
        }
        else{
            return repository.findById(id).get();
        }
    }
    public List<Tournoi> getTournois(){
        if(repository.findAll().size()==0){
            throw new RuntimeException("Aucun tournoi présent");
        }
        else{
            return repository.findAll();
        }
    }



    /**
     * Cette méthode permet de créer un tournoi
     * @param tournoi le tournoi à créer
     * @throws RuntimeException si un tournoi existe déjà, si la date n'est pas mentionnée
     */
    public void saveTournoi(Tournoi tournoi){
        long year = tournoi.getDateDebutTournoi().getYear();
        tournoi.setId(year);
        System.out.println(tournoi.getDateDebutTournoi().getYear());
        if(tournoi.getDateDebutTournoi()==null){
            throw new RuntimeException("Date de début de tournoi obligatoire");
        }
        else if(repository.existsById(year)){
            throw new RuntimeException("Tournoi déjà créé pour l'année "+tournoi.getDateDebutTournoi().getYear());
        }
        else{
            repository.save(tournoi);
        }
    }

    /**
     * Permet de mettre à jour les informations du tournoi en cours
     * @param tournoi le tournoi à mettre à jour
     * @throws RuntimeException si le tournoi n'existe pas
     */
    public void updateTournoi(Tournoi tournoi, Long id){
        if(repository.existsById(tournoi.getId()) & tournoi.getId()==id){
            Tournoi update = repository.getReferenceById(tournoi.getId());
            update.setNom(tournoi.getNom());
            updateDateDebutTournoi(tournoi,update);
            updateDateFinInscription(tournoi,update);
            updateDateDebutPoule(tournoi,update);
            updateDateFinPoule(tournoi,update);
            updateDateDebutTableau(tournoi,update);
            updateDateFinTournoi(tournoi,update);
            repository.save(update);
        }
        else{
            throw new RuntimeException("Tournoi introuvable");
        }
    }
    private void updateDateDebutTournoi(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateDebutTournoi().getYear()!=tournoi.getId()){
            throw new RuntimeException("On ne peut pas commencer le tournoi en dehors de l'année en cours");
        }
        else{
            update.setDateDebutTournoi(tournoi.getDateDebutTournoi());
        }
    }
    private void updateDateFinInscription(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateDebutTournoi()==null || tournoi.getDateFinTournoi()==null || tournoi.getDateDebutTournoi().isBefore(tournoi.getDateFinInscription())){
            update.setDateFinInscription(tournoi.getDateFinInscription());
        }else {
            throw new RuntimeException("Date de fin des inscriptions inadmissible");
        }
    }
    private void updateDateDebutPoule(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateFinInscription()==null|| tournoi.getDateDebutPoule()==null||tournoi.getDateFinInscription().isBefore(tournoi.getDateDebutPoule())){
            update.setDateDebutPoule(tournoi.getDateDebutPoule());
        }
        else {
            throw new RuntimeException("Date de début des poules inadmissible");
        }
    }
    private void updateDateFinPoule(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateDebutPoule()==null || tournoi.getDateFinPoule()==null||tournoi.getDateDebutPoule().isBefore(tournoi.getDateFinPoule())){
            update.setDateFinPoule(tournoi.getDateFinPoule());
        }
        else{
            throw new RuntimeException("Date de fin des poules inadmissible");
        }
    }
    private void updateDateDebutTableau(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateFinPoule()==null || tournoi.getDateDebutTableau()==null||tournoi.getDateFinPoule().isBefore(tournoi.getDateDebutTableau())||tournoi.getDateFinPoule().isEqual(tournoi.getDateDebutTableau())){
            update.setDateDebutTableau(tournoi.getDateDebutTableau());
        }
        else {
            throw new RuntimeException("Date de début de tableau inadmissible");
        }
    }
    private void updateDateFinTournoi(Tournoi tournoi,Tournoi update){
        if(tournoi.getDateDebutTableau()==null|| tournoi.getDateFinTournoi()==null||tournoi.getDateDebutTableau().isBefore(tournoi.getDateFinTournoi())){
            update.setDateFinTournoi(tournoi.getDateFinTournoi());
        }
        else{
            throw new RuntimeException("Date de fin du tournoi inadmissible");
        }
    }
    public List<Equipe> getEquipes(Long id){
        if(repository.findById(id).get().getEquipes().isEmpty()){
            throw new RuntimeException("Aucune équipe");
        }
        else {
            return repository.findById(id).get().getEquipes();
        }
    }
    public List<Poule> getPoules(Long id){
        if(repository.findById(id).get().getPoules().isEmpty()){
            throw new RuntimeException("Aucune poule");
        }else {
            return repository.findById(id).get().getPoules();
        }
    }

    public List<Tableau> getTableaux(Long id){
        if(repository.findById(id).get().getTableaux().isEmpty()){
            throw new RuntimeException("Aucun tableau");
        }
        else {
            return repository.findById(id).get().getTableaux();
        }
    }

    public List<Tableau> getTableauxByRang(Long id, Long rang){
        if(repository.getTableauxByRang(id,rang).isEmpty()){
            throw new RuntimeException("Aucun tableau");
        }
        else{
            return repository.getTableauxByRang(id,rang);
        }
    }

    /**
     * Permet de créer les groupes
     * @throws RuntimeException si on a déjà un groupe dans le processus de création, on ne peut en ajouter d'autres
     */
    public void createGroupes(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Tournoi inexistant");
        }
        else if (!repository.findById(id).get().getPoules().isEmpty()) {
            throw new RuntimeException("On ne peut créer de groupe.");
        }
        else {
            Tournoi update = repository.findById(id).get();//Le tournoi en cours dont on doit récupérer les équipes
            int nbEquipes = update.tailleTournoi();

            //Si le nombre d'équipes est inférieur à 5, on ne peut créer une phase de groupes, le tournoi ne peut être lancé

            if (nbEquipes > 5) {
                List<Equipe> allTeams = update.getEquipes();
                List<Poule> poules = new ArrayList<>();
                int nbGroupes = (int) Math.ceil(((double) nbEquipes / 4));
                int nombreQuatre = 0;
                int nombreTrois = 0;
                List<Poule> groupeQuatre = new ArrayList<>();
                List<Poule> groupeTrois = new ArrayList<>();
                if (nbEquipes == 5) {
                    try {
                        throw new Exception("Tournoi impossible. Veuillez rajouter ou enlever une équipe !");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    //On recherche le nombre de groupes de 4 équipes et celui de 3.
                    if (nbEquipes < 18) {

                        for (int i = 0; i <= (nbEquipes) / 4; i++) {
                            for (int j = 0; j <= (nbEquipes) / 3; j++) {
                                if (4 * i + 3 * j == nbEquipes) {
                                    nombreQuatre = i;
                                    nombreTrois = j;
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i <= (nbEquipes) / 4; i++) {
                            for (int j = 0; j <= i; j++) {
                                if (4 * i + 3 * j == nbEquipes) {
                                    nombreQuatre = i;
                                    nombreTrois = j;
                                }
                            }
                        }
                    }
                }
                //On crée des groupes en fonction du nombre des groupes de 4 + celui des groupes de 3 trouvés
                for (int i = 0; i < nbGroupes; i++) {
                    Poule poule = new Poule();
                    poule.setId((long) i);
                    poule.setNom("Groupe " + (i + 1));
                    poule.setTournoi(update);
                    poules.add(poule);
                }
                //On associe chaque poule à une liste d'équipes
                HashMap<Poule, List<Equipe>> listHashMap = new HashMap<>();
                if (nombreQuatre > nombreTrois) {
                    //Si le nombre de groupes de 4 est supérieur à celui des groupes de 3, on applique cet algorithme
                    for (Poule groupe : poules) {
                        listHashMap.put(groupe, new ArrayList<>());
                        for (int j = 0; j < 4; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupe).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupe);
                    }
                } else if (nombreQuatre <= nombreTrois) {
                    //Sinon si le nombre de groupes de 4 est inférieur à celui des 3, on recense les groupes de 4 et ceux des groupes de 3
                    for (int i = 0; i < nombreQuatre; i++) {
                        Poule poule = new Poule();
                        poule.setId((long) i + 1);
                        poule.setNom("Groupe " + (i + 1));
                        poule.setTournoi(update);
                        groupeQuatre.add(poule);
                    }
                    for (int i = 0; i < nombreTrois; i++) {
                        Poule poule = new Poule();
                        poule.setId((long) i + nombreQuatre + 1);
                        poule.setNom("Groupe " + (i + nombreQuatre + 1));
                        poule.setTournoi(update);
                        groupeTrois.add(poule);
                    }
                    for (Poule groupeQ : groupeQuatre) {
                        listHashMap.put(groupeQ, new ArrayList<>());
                        for (int j = 0; j < 4; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupeQ).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupeQ);
                    }
                    for (Poule groupeT : groupeTrois) {
                        listHashMap.put(groupeT, new ArrayList<>());
                        for (int j = 0; j < 3; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupeT).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupeT);
                    }
                    poules = groupeQuatre;
                    poules.addAll(groupeTrois);
                }

                //On crée les matchs de chaque poule

                for (Poule groupe : poules) {
                    for (int i = 0; i < listHashMap.get(groupe).size() - 1; i++) {
                        for (int j = i + 1; j < listHashMap.get(groupe).size(); j++) {
                            MatchPoule match = new MatchPoule(groupe);
                            match.setEquipeA(listHashMap.get(groupe).get(i));
                            match.setEquipeB(listHashMap.get(groupe).get(j));
                            match.setLieu("Inconnu");
                            groupe.getListMatchs().add(match);
                            matchPouleRepository.save(match);
                        }
                    }
                }
            }
        }
    }

    /**
     * Permet de supprimer entièrement un tournoi
     */
    public void deleteTournoi(Long id){
        repository.deleteById(id);
    }

    /**
     * Permet de créer tous les tableaux après la phase de groupes
     * @throws RuntimeException S'il existe déjà un tableau. On ne peut pas créer de tableaux supplémentaires quand tous les tableaux ont été créés.
     */
    public void createTableaux(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Tournoi inexistant");
        }
        else if (repository.findById(id).get().getTableaux().size() > 0) {
            throw new RuntimeException("On ne peut créer de nouveaux tableaux.");
        } else if (repository.findById(id).get().getPoules().isEmpty()){
            throw new RuntimeException("Impossible de lancer générer la phase des tableaux sans les poules");
        }

        else{

            List<Integer> puissances = new ArrayList<>();
            List<Tableau> tableaux = new ArrayList<>();
            List<Equipe> vainqueurs = new ArrayList<>();//Tous les premiers
            List<Equipe> seconds = new ArrayList<>();//Tous les deuxièmes
            List<Equipe> troisiemes = new ArrayList<>();//Tous les troisièmes
            List<Equipe> quatriemes = new ArrayList<>();//Tous les quatrièmes potentiels
            //Les dix premières puissances de 2 pour les parités parfaites
            for (int i = 0; i < 10; i++) {
                puissances.add((int) Math.pow(2, i));
            }
            //On récupère toutes les équipes déjà classées.
            List<Poule> poules = repository.findById(id).get().getPoules();
            PouleService service = new PouleService(pouleRepository);
            poules.forEach(groupe -> {
                List<Equipe> equipes = service.getRanking(groupe.getId());
                vainqueurs.add(equipes.get(0));
                seconds.add(equipes.get(1));
                troisiemes.add(equipes.get(2));
                if (equipes.size() >= 4) {
                    quatriemes.add(equipes.get(3));
                }
            });
            int nbTableauxAutres = (int) Math.ceil(((double) (troisiemes.size() + quatriemes.size()) / 16));//Nombre d'autres tableaux
            int totalPrincipal = vainqueurs.size() + seconds.size();//Total des équipes du tableau principal.
            for (int i = 0; i <= nbTableauxAutres; i++) {
                Tableau tableau = new Tableau();
                tableau.setId((long) i + 1);
                tableau.setTournoi(repository.findById(id).get());
                tableau.setRang(i);
                tableaux.add(tableau);
            }
            tableauRepository.saveAll(tableaux);

            //Implémentation des tableaux en deux étapes :
            //1- Implémentation du tableau principal, car il a une contrainte de premier vs deuxième
            //2- Implémentation des autres tableaux sans contrainte particulière


            //1- Implémentation du tableau principal
            int toursPrincipal = (int) Math.ceil(((Math.log(vainqueurs.size() + seconds.size()) / Math.log(2))));//Nombre de tours du tableau principal
            List<MatchTableau> matchTableaux = new ArrayList<>();//Liste des matchs du tableau principal

            if (puissances.contains(totalPrincipal)) {
                //Si le nombre d'équipes du tableau principal est parfaitement pair, l'implémentation est directe
                for (int i = 0; i < toursPrincipal; i++) {
                    //Pour chaque tour, on récupère le nombre de matchs par puissance de 2 et on les crée.
                    for (int j = 0; j < totalPrincipal / (int) Math.pow(2, i + 1); j++) {
                        MatchTableau match = new MatchTableau(tableaux.get(0), i);
                        match.setTour(i);
                        match.setLieu("Inconnu");
                        matchTableaux.add(match);
                        tableaux.get(0).setListMatchs(matchTableaux);

                    }
                }
                for (MatchTableau matchTableau : matchTableaux) {
                    //On est obligé d'imposer le premier tour.
                    if (!vainqueurs.isEmpty() & matchTableau.getTour() == 0) {
                        Equipe premier = vainqueurs.get(new Random().nextInt(vainqueurs.size()));
                        Equipe deuxieme = seconds.get(new Random().nextInt(seconds.size()));
                        matchTableau.setEquipeA(premier);
                        matchTableau.setEquipeB(deuxieme);
                        tableaux.get(0).getListMatchs().set(tableaux.get(0).getListMatchs().indexOf(matchTableau), matchTableau);
                        vainqueurs.remove(premier);
                        seconds.remove(deuxieme);
                    }
                }
            } else {
                if (totalPrincipal % 2 == 0) {
                    //Si le total d'équipes est pair, mais pas parfaitement, on choisit le nombre de matchs du premier tour à déplacer dans le prochain.
                    int nombreDeMatchsADeplacer = 0;
                    for (int i = 0; i < totalPrincipal; i++) {
                        if (puissances.contains((totalPrincipal / 2) + i)) {
                            nombreDeMatchsADeplacer = i;//On prend la plus petite valeur pour éviter de déplacer tout le tour
                            break;
                        }
                    }

                    //On crée les matchs des deux premiers tours
                    Map<Integer, Integer> relation2 = new HashMap<>();
                    int tours = (int) Math.ceil(((Math.log(totalPrincipal) / Math.log(2))));
                    if (tours >= 2) {
                        for (int i = 0; i < 2; i++) {
                            relation2.put(i, (totalPrincipal / (int) Math.pow(2, i + 1)));
                        }
                    }

                    //On met à jour le nombre de matchs de chaque tour
                    for (Map.Entry<Integer, Integer> val : relation2.entrySet()) {
                        int ancien0 = 0;
                        System.out.println();
                        if (val.getKey() == 0) {
                            val.setValue(val.getValue() - nombreDeMatchsADeplacer);
                            ancien0 = val.getValue();
                            relation2.replace(1, nombreDeMatchsADeplacer + (ancien0 / 2));
                        }
                    }

                    //On crée les matchs des autres tours en divisant progressivement ceux du deuxième tour.
                    for (int i = 2; i < tours; i++) {
                        relation2.put(i, relation2.get(1) / (int) Math.pow(2, i - 1));
                    }
                    for (Map.Entry<Integer, Integer> val : relation2.entrySet()) {
                        for (int j = 0; j < val.getValue(); j++) {
                            MatchTableau match = new MatchTableau(tableaux.get(0), val.getKey());
                            match.setTour(val.getKey());
                            match.setLieu("Inconnu");
                            matchTableaux.add(match);
                            tableaux.get(0).setListMatchs(matchTableaux);
                        }
                    }

                    //Après la création des matchs, on récupère toutes les équipes qui doivent jouer et on les associe à un match
                    for (MatchTableau matchTableau : matchTableaux) {
                        if (!vainqueurs.isEmpty() & (matchTableau.getTour() == 0 || matchTableau.getTour() == 1)) {
                            Equipe premier = vainqueurs.get(new Random().nextInt(vainqueurs.size()));
                            Equipe deuxieme = seconds.get(new Random().nextInt(seconds.size()));
                            matchTableau.setEquipeA(premier);
                            matchTableau.setEquipeB(deuxieme);
                            tableaux.get(0).getListMatchs().set(tableaux.get(0).getListMatchs().indexOf(matchTableau), matchTableau);
                            vainqueurs.remove(premier);
                            seconds.remove(deuxieme);
                        }
                    }
                }
            }
            matchTableauRepository.saveAll(matchTableaux);

            //2- Implémentation des autres tableaux
            List<Equipe> equipeList = troisiemes;
            equipeList.addAll(quatriemes);
            List<MatchTableau> matchs = new ArrayList<>();//Liste des matchs du tableau courant
            for (int i = 1; i < tableaux.size(); i++) {
                int nombreEquipes = equipeList.size();
                int tours = (int) Math.ceil(((Math.log(nombreEquipes) / Math.log(2))));//Nombre de tours du tableau courant
                if (nombreEquipes % 2 == 0) {
                    //Si le total d'équipes est pair, mais pas parfaitement, on choisit le nombre de matchs du premier tour à déplacer dans le prochain.
                    int adeplacer = 0;
                    for (int j = 0; j < nombreEquipes; j++) {
                        if (puissances.contains((nombreEquipes / 2) + j)) {
                            adeplacer = j;//On prend la plus petite valeur pour éviter de déplacer tout le tour
                            break;
                        }
                    }

                    //On crée les matchs des deux premiers tours
                    Map<Integer, Integer> relation = new HashMap<>();
                    if (tours >= 2) {
                        for (int j = 0; j < 2; j++) {
                            relation.put(j, (nombreEquipes / (int) Math.pow(2, j + 1)));
                        }
                    }


                    //On met à jour le nombre de matchs de chaque tour
                    for (Map.Entry<Integer, Integer> val : relation.entrySet()) {
                        int ancien = 0;
                        System.out.println();
                        if (val.getKey() == 0) {
                            val.setValue(val.getValue() - adeplacer);
                            ancien = val.getValue();
                            relation.replace(1, adeplacer + (ancien / 2));
                        }
                    }

                    //On crée les matchs des autres tours en divisant progressivement ceux du deuxième tour.
                    for (int j = 2; j < tours; j++) {
                        relation.put(j, relation.get(1) / (int) Math.pow(2, j - 1));
                    }
                    for (Map.Entry<Integer, Integer> val : relation.entrySet()) {
                        for (int j = 0; j < val.getValue(); j++) {
                            MatchTableau match = new MatchTableau(tableaux.get(i), val.getKey());
                            match.setTour(val.getKey());
                            match.setLieu("Inconnu");
                            matchs.add(match);
                            tableaux.get(i).setListMatchs(matchs);
                        }
                    }
                    //Pour l'attribution des places dans les matchs du premier et deuxième tour, tous les troisièmes et quatrièmes sont dans la même liste.
                    //Il faut s'assurer que l'équipe en position A ne soit pas sélectionnée en position B.
                    for (MatchTableau matchTableau : matchs) {
                        if (!equipeList.isEmpty() & (matchTableau.getTour() == 0 || matchTableau.getTour() == 1)) {
                            Equipe equipeA = equipeList.get(new Random().nextInt(equipeList.size()));
                            Equipe equipeB = equipeList.get(new Random().nextInt(equipeList.size()));
                            while (equipeB == equipeA) {
                                equipeB = equipeList.get(new Random().nextInt(equipeList.size()));
                            }
                            matchTableau.setEquipeA(equipeA);
                            matchTableau.setEquipeB(equipeB);
                            tableaux.get(i).getListMatchs().set(tableaux.get(i).getListMatchs().indexOf(matchTableau), matchTableau);
                            equipeList.remove(equipeA);
                            equipeList.remove(equipeB);
                        }
                    }
                    matchTableauRepository.saveAll(matchs);
                } else {
                    //On peut déplacer une équipe dans le tour suivant ou lui attribuer une équipe fictive, mais il faudra s'assurer que l'équipe fictive n'ait aucun groupe.
                    //Solution : on déplace une équipe au tour suivant
                    Equipe deplacee = equipeList.get(new Random().nextInt(equipeList.size()));
                    int adeplacer = 0;
                    for (int j = 0; j < nombreEquipes + 1; j++) {
                        if (puissances.contains(((nombreEquipes + 1) / 2) + j)) {
                            adeplacer = j + 1;//On prend la plus petite valeur pour éviter de déplacer tout le tour
                            break;
                        }
                    }
                    //On crée les matchs des deux premiers tours
                    Map<Integer, Integer> relation = new HashMap<>();
                    if (tours >= 2) {
                        for (int j = 0; j < 2; j++) {
                            relation.put(j, ((nombreEquipes + 1) / (int) Math.pow(2, j + 1)));
                            System.out.println("Pour le tour " + j + " il y a " + ((nombreEquipes + 1) / (int) Math.pow(2, j + 1)) + "matchs");
                        }
                    }


                    //On met à jour le nombre de matchs de chaque tour
                    for (Map.Entry<Integer, Integer> val : relation.entrySet()) {
                        int ancien = 0;
                        if (val.getKey() == 0) {
                            val.setValue(val.getValue() - adeplacer);
                            ancien = val.getValue();
                            relation.replace(1, adeplacer + (ancien / 2));
                        }
                    }
                    //On crée les matchs des autres tours en divisant progressivement ceux du deuxième tour.
                    for (int j = 2; j < tours; j++) {
                        relation.put(j, relation.get(1) / (int) Math.pow(2, j - 1));
                    }
                    for (Map.Entry<Integer, Integer> val : relation.entrySet()) {
                        for (int j = 0; j < val.getValue(); j++) {
                            MatchTableau match = new MatchTableau(tableaux.get(i), val.getKey());
                            match.setTour(val.getKey());
                            match.setLieu("Inconnu");
                            matchs.add(match);
                            tableaux.get(i).setListMatchs(matchs);
                        }
                    }
                    //Pour l'attribution des places dans les matchs du premier et deuxième tour, tous les troisièmes et quatrièmes sont dans la même liste.
                    //Il faut s'assurer que l'équipe en position A ne soit pas sélectionnée en position B.
                    equipeList.remove(deplacee);
                    for (MatchTableau matchTableau : matchs) {
                        if (!equipeList.isEmpty() & (matchTableau.getTour() == 0 || matchTableau.getTour() == 1)) {
                            Equipe equipeA = equipeList.get(new Random().nextInt(equipeList.size()));
                            while (equipeA == deplacee) {
                                equipeA = equipeList.get(new Random().nextInt(equipeList.size()));
                            }
                            Equipe equipeB = equipeList.get(new Random().nextInt(equipeList.size()));
                            while (equipeB == equipeA || equipeB == deplacee) {
                                equipeB = equipeList.get(new Random().nextInt(equipeList.size()));
                            }
                            matchTableau.setEquipeA(equipeA);
                            matchTableau.setEquipeB(equipeB);
                            //tableaux.get(i).getListMatchs().set(tableaux.get(i).getListMatchs().indexOf(matchTableau),matchTableau);
                            equipeList.remove(equipeA);
                            equipeList.remove(equipeB);
                        }
                    }
                    //après la création des matchs de base, on crée un nouveau match sur tapis vert : l'équipe à déplacer est qualifiée
                    MatchTableau matchTableau = new MatchTableau(tableaux.get(i), 0);
                    matchTableau.setEquipeA(deplacee);
                    matchTableau.setScoreA(3);
                    matchs.add(matchTableau);
                    matchTableauRepository.saveAll(matchs);
                }
            }
        }
    }
}
