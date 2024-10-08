package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.dto.TableauDto;
import fr.istic.pdl.ticpbackend.dto.TourDto;
import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import fr.istic.pdl.ticpbackend.dto.EquipeDto;

import java.sql.SQLException;
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
    InformationRepository informationRepository;
    PhotoRepository photoRepository;
    PouleService pouleService;


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
        return repository.findAll();
    }



    /**
     * Cette méthode permet de créer un tournoi
     * @param tournoi le tournoi à créer
     * @throws RuntimeException si un tournoi existe déjà, si la date n'est pas mentionnée
     */
    public void saveTournoi(Tournoi tournoi){

        if(tournoi.getDateDebutTournoi()==null){
            throw new RuntimeException("Date de début de tournoi obligatoire");
        }
        else if(repository.existsById((long) tournoi.getDateDebutTournoi().getYear())){
            throw new RuntimeException("Tournoi déjà créé pour l'année "+tournoi.getDateDebutTournoi().getYear());
        }
        else{
            long year = tournoi.getDateDebutTournoi().getYear();
            tournoi.setId(year);
            repository.save(tournoi);
        }
    }

    /**
     * Permet de mettre à jour les informations du tournoi en cours
     * @param tournoi le tournoi à mettre à jour
     * @throws RuntimeException si le tournoi n'existe pas
     */
    public void updateTournoi(Tournoi tournoi, Long id){
        if( repository.existsById(tournoi.getId()) ){
            Tournoi update = repository.getReferenceById(tournoi.getId());
            update.setNom(tournoi.getNom());
            if(tournoi.getDateDebutTournoi()!=null){
                updateDateDebutTournoi(tournoi,update);
            }
            if(tournoi.getDateFinInscription()!=null){
                updateDateFinInscription(tournoi,update);
            }

            if(tournoi.getDateDebutPoule()!=null){
                updateDateDebutPoule(tournoi,update);
            }
            if(tournoi.getDateFinPoule()!=null){
                updateDateFinPoule(tournoi,update);
            }
            if(tournoi.getDateDebutTableau()!=null){
                updateDateDebutTableau(tournoi,update);
            }
            if(tournoi.getDateFinTournoi()!=null){
                updateDateFinTournoi(tournoi,update);
            }
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
    public void addPhoto(String url,Tournoi tournoi){
        if(!repository.existsById(tournoi.getId())){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(url.isEmpty()){
            throw new RuntimeException("Aucune photo à enregistrer");
        }
        else{
            Photo photo = new Photo();
            photo.setUrl(url);
            photo.setTournoi(tournoi);
            photoRepository.save(photo);
        }
    }
    public void updatePhoto(Long idPhoto, Long idTournoi, Photo photo){
        if(photo.getTournoi().getId()!=idTournoi){
            throw new RuntimeException("Erreur identifiant de tournoi");
        }
        else if(!photoRepository.existsById(idPhoto)){
            throw new RuntimeException("Erreur identifiant de la photo");
        }
        else if(photo.getUrl().isEmpty()){
            throw new RuntimeException("Url obligatoire");
        }
        else {
            Photo update = photoRepository.findById(idPhoto).get();
            update.setUrl(photo.getUrl());
            photoRepository.save(update);
        }
    }
    public List<Photo> getPhotos(Long id){
        return repository.findById(id).get().getPhotos();
    }
    public Photo getPhoto(Long idTournoi, Long idPhoto){
        if(!photoRepository.existsById(idPhoto)){
            throw new RuntimeException("Photo introuvable");
        }
        else if(!repository.existsById(idTournoi)){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(photoRepository.findById(idPhoto).get().getTournoi()!=repository.findById(idTournoi).get()){
            throw new RuntimeException("Tournoi incompatible avec la photo recherchée");
        }
        else {
            return photoRepository.findById(idPhoto).get();
        }
    }
    public void removePhoto(Long idPhoto, Long id){
        if(!photoRepository.existsById(idPhoto)){
            throw new RuntimeException("Photo introuvable");
        }
        else if(!repository.existsById(id)){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(photoRepository.findById(idPhoto).get().getTournoi().getId()!=id){
            throw new RuntimeException("La photo ne correspond pas au tournoi");
        }
        else{
            photoRepository.deleteById(idPhoto);
        }
    }
    public void addInformation(String info, Tournoi tournoi){
        if(!repository.existsById(tournoi.getId())){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(info.isEmpty()){
            throw new RuntimeException("Aucune information à enregistrer");
        }
        else {
            Information information= new Information();
            information.setInfo(info);
            information.setTournoi(tournoi);
            informationRepository.save(information);
        }
    }
    public List<Information> getInfos(Long id){
        return repository.findById(id).get().getInformations();
    }
    public Information getInfo(Long idInfo, Long idTournoi){
        if(!informationRepository.existsById(idInfo)){
            throw new RuntimeException("Info introuvable");
        }
        else if(!repository.existsById(idTournoi)){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(informationRepository.findById(idInfo).get().getTournoi()!=repository.findById(idTournoi).get()){
            throw new RuntimeException("Tournoi incompatible avec l'info recherchée");
        }
        else {
            return informationRepository.findById(idInfo).get();
        }
    }
    public void updateInformation(Long idInfo, Long idTournoi, Information information){
        if(information.getTournoi().getId()!=idTournoi){
            throw new RuntimeException("Erreur identifiant de tournoi");
        }
        else if(!informationRepository.existsById(idInfo)){
            throw new RuntimeException("Erreur identifiant information");
        }
        else if(information.getInfo().isEmpty()){
            throw new RuntimeException("Url obligatoire");
        }
        else {
            Information update = informationRepository.findById(idInfo).get();
            update.setInfo(information.getInfo());
            informationRepository.save(update);
        }
    }
    public void removeInformation(Long idInfo, Long id){
        if(!informationRepository.existsById(idInfo)){
            throw new RuntimeException("Info introuvable");
        }
        else if(!repository.existsById(id)){
            throw new RuntimeException("Tournoi introuvable");
        }
        else if(informationRepository.findById(idInfo).get().getTournoi().getId()!=id){
            throw new RuntimeException("L'info ne correspond pas au tournoi");
        }
        else{
            informationRepository.deleteById(idInfo);
        }
    }
    public List<Equipe> getEquipes(Long id){
        return repository.findById(id).get().getEquipes();
    }
    public List<Equipe> getVainqueurs(Long id){
        List<Equipe> vainqueurs = new ArrayList<>();
        List<Tableau> tableaux = repository.findById(id).get().getTableaux();
        Map<Tableau,MatchTableau> finalesTableaux = new HashMap<>();

        for(Tableau tableau : tableaux){
            List<Integer> rounds = new ArrayList<>();
            for (MatchTableau matchTableau:tableau.getListMatchs()){
                rounds.add(matchTableau.getTour());
            }
            for(int i=0;i< rounds.size();i++){
                while(Collections.frequency(rounds,i)>1){
                    rounds.remove(i);
                }
            }
            for(MatchTableau matchTableau: tableau.getListMatchs()){
                if(matchTableau.getTour()==(Collections.max(rounds))){
                    finalesTableaux.put(tableau,matchTableau);
                }
            }
        }
        for(Map.Entry<Tableau,MatchTableau> val: finalesTableaux.entrySet()){
            if(val.getValue().getScoreA()>val.getValue().getScoreB()){
                vainqueurs.add(val.getValue().getEquipeA());
            }
            else if(val.getValue().getScoreA()<val.getValue().getScoreB()){
                vainqueurs.add(val.getValue().getEquipeB());
            }
        }
 
        return vainqueurs;
        
    }
    public List<Object> getPoules(Long id){

        List<Poule> poules = repository.findById(id).get().getPoules();
        List<Object> data = new ArrayList<>();
        for (Poule poule : poules) {
            JSONObject obj = new JSONObject();
            List<EquipeDto> classements =pouleService.getClassement((long) poule.getId());
            obj.put("info", poule);
            obj.put("classements", classements);
            data.add(obj);
        }
        return data;
    }

    public List<Tableau> getTableaux(Long id){        
        return repository.findById(id).get().getTableaux();
    }

    public List<Tableau> getTableauxByRang(Long id, Long rang){
        List<Tableau> tableaux = new ArrayList<>();
        for(Tableau tableau :repository.findById(id).get().getTableaux()){
            if(tableau.getRang()==rang){
                tableaux.add(tableau);
            }
        };
        if(tableaux.isEmpty()){
            throw new RuntimeException("Aucun tableau de rang "+rang);
        }
        else{
            return tableaux;
        }

    }
    public TableauDto getMatchsByTour(Long id, Long rang){
        Tableau tableau=new Tableau();
        for (Tableau tableau1:repository.findById(id).get().getTableaux()){
            if(tableau1.getRang()==rang.intValue()){
                tableau=tableau1;
            }
        }
        List<MatchTableau> matchsTableau = tableau.getListMatchs();//Tous les matchs d'un tableau
        List<TourDto> tours = new ArrayList<>();//
        List<Integer> rounds = new ArrayList<>();//Tous les rounds d'un tableau
        for(MatchTableau matchTableau:matchsTableau){
            rounds.add(matchTableau.getTour());
        }
        for(int i=0;i< rounds.size();i++){
            while(Collections.frequency(rounds,i)>1){
                rounds.remove(i);
            }
        }
        for(int i=0;i<rounds.size();i++){
            List<MatchTableau> matchsByTour = new ArrayList<>();
            for (MatchTableau matchTableau:matchsTableau){
                if(matchTableau.getTour()==i){
                    matchsByTour.add(matchTableau);
                }
            }
            tours.add(new TourDto("Tour " + i, i, matchsByTour));

        }
        return new TableauDto(Math.toIntExact(tableau.getId()),tableau.getRang(),tours);
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
        else if(repository.findById(id).get().getEquipes().size()<=5){
            throw new RuntimeException("Tournoi impossible. Veuillez rajouter ou enlever une équipe !");
        }
        else {
            Tournoi update = repository.findById(id).get();//Le tournoi en cours dont on doit récupérer les équipes
            int nbEquipes = update.tailleTournoi();
            //Si le nombre d'équipes est inférieur à 5, on ne peut créer une phase de groupes, le tournoi ne peut être lancé
            List<Equipe> allTeams = update.getEquipes();
            List<Poule> poules = new ArrayList<>();
            int nbGroupes = (int) Math.ceil(((double) nbEquipes / 4));
            int nombreQuatre = 0;
            int nombreTrois = 0;
            List<Poule> groupeQuatre = new ArrayList<>();
            List<Poule> groupeTrois = new ArrayList<>();
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

            //On crée des groupes en fonction du nombre des groupes de 4 + celui des groupes de 3 trouvés
            for (int i = 0; i < nbGroupes; i++) {
                Poule poule = new Poule();
                poule.setNom("Groupe " + (i + 1));
                poule.setTournoi(update);
                poules.add(poule);
            }
            Map<Poule, List<Equipe>> listHashMap = new HashMap<>();
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
            }
            else if (nombreQuatre <= nombreTrois) {
                //Sinon si le nombre de groupes de 4 est inférieur à celui des 3, on recense les groupes de 4 et ceux des groupes de 3
                for (int i = 0; i < nombreQuatre; i++) {
                    Poule poule = new Poule();
                    poule.setNom("Groupe " + (i + 1));
                    poule.setTournoi(update);
                    groupeQuatre.add(poule);
                }
                for (int i = 0; i < nombreTrois; i++) {
                    Poule poule = new Poule();
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
            for(Map.Entry<Poule, List<Equipe>> val : listHashMap.entrySet()){
                System.out.println(val.getValue().size());
                for(int i=0;i<val.getValue().size()-1;i++){
                    for(int j=i+1;j<val.getValue().size();j++){
                        MatchPoule match = new MatchPoule(val.getKey());
                        match.setEquipeA(val.getValue().get(i));
                        match.setEquipeB(val.getValue().get(j));
                        match.setLieu("Inconnu");
                        matchPouleRepository.save(match);
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
            if(!quatriemes.isEmpty()) {
                equipeList.addAll(quatriemes);
            }
            List<MatchTableau> matchs = new ArrayList<>();//Liste des matchs du tableau courant
            for (int i = 1; i < tableaux.size(); i++) {
                int nombreEquipes = equipeList.size();
                int tours = (int) Math.ceil(((Math.log(nombreEquipes) / Math.log(2))));//Nombre de tours du tableau courant
                if(puissances.contains(nombreEquipes)){
                    for (int j = 0; j < nombreEquipes; j++) {
                        //Pour chaque tour, on récupère le nombre de matchs par puissance de 2 et on les crée.
                        for (int k = 0; k < nombreEquipes / (int) Math.pow(2, j + 1); k++) {
                            MatchTableau match = new MatchTableau(tableaux.get(i), j);
                            match.setTour(j);
                            match.setLieu("Inconnu");
                            matchs.add(match);
                            tableaux.get(i).setListMatchs(matchs);
                        }
                    }
                    for (MatchTableau matchTableau : matchs) {
                        //On est obligé d'imposer le premier tour.
                        if (!equipeList.isEmpty() & matchTableau.getTour() == 0) {
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
                    //matchTableauRepository.saveAll(matchs);
                }
                else if (nombreEquipes % 2 == 0) {
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
                    //matchTableauRepository.saveAll(matchs);
                }
                else {
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
                    //matchTableauRepository.saveAll(matchs);
                }
            }
            matchTableauRepository.saveAll(matchs);
        }
    }
    /*private void refreshTournois(){
        for(Tournoi update:repository.findAll()){
            try {
                if(LocalDate.now().isEqual(update.getDateFinInscription())){
                    createGroupes(update.getId());
                }
                if(LocalDate.now().isEqual(update.getDateFinPoule())){
                    createTableaux(update.getId());
                }
            }
            catch (RuntimeException e){
                e.printStackTrace();
            }
        }


    }
    private synchronized void periodicRefresh(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                refreshTournois();
            }
        }, 0, 5000);
    }

     */
}
