package fr.istic.ticp.model;


import java.util.*;

public class Main {
    /*
    *Créer les phases de groupes du tournoi et leur match.
    * Ceci sert que pour les tests.
    * @param nbEquipes
     */
    public static void tournoiGroupe(int nbEquipes){
        List<Equipe> listEquipes = new ArrayList<>();
        List<Groupe> groupes = new ArrayList<>();
        List<Equipe> tableau = new ArrayList<>();

        int nbGroupes = (int)Math.ceil(((double)nbEquipes/4));
        int nbTableaux = (int)Math.ceil(((double)nbEquipes/16));

        if(nbEquipes % 4 <=2 && nbEquipes%4>=1){
            System.out.println("Tournoi impossible");
        }
        else{
            for (int i=0;i<nbEquipes;i++){
                listEquipes.add(new Equipe( i,"Equipe "+i));
            }
            System.out.println("Il y a "+nbGroupes+" groupes");
            System.out.println("Il y a "+nbTableaux+" tableaux");
            for(int i=0;i<nbGroupes;i++) {
                groupes.add(new Groupe(i, "Groupe " + (i+1), new HashSet<>()));
            }
            for(Groupe groupe: groupes) {
                System.out.println(groupe.getNom());
                for(int j=0;j<4;j++){
                    if(!listEquipes.isEmpty()){
                        Equipe equipe = listEquipes.get(new Random().nextInt(listEquipes.size()));
                        groupe.getEquipes().add(equipe);
                        System.out.println(equipe.getNom());
                        listEquipes.remove(equipe);
                    }
                }
                System.out.println("\n");
            }
            for(Groupe groupe: groupes){
                List<Equipe> equipesGroupe = new ArrayList<>(groupe.getEquipes());
                for(int i=0;i<equipesGroupe.size()-1;i++){
                    for(int j=i+1;j<equipesGroupe.size();j++){
                        Match match = new Match();
                        match.setDomicileEquipe(equipesGroupe.get(i));
                        match.setExterieurEquipe(equipesGroupe.get(j));
                        groupe.getMatchs().add(match);
                    }
                }
            }
            for(Groupe groupe : groupes){
                for(Match match : groupe.getMatchs()){
                System.out.println(groupe.getNom()+" - "+match);
                }
            }
        }
    }
    public static void tournoiTableau(int nombres){
        List<Equipe> huitiemes = new ArrayList<>();
        List<Equipe> quarts = new ArrayList<>();
        List<Equipe> demies = new ArrayList<>();
        if(nombres<=16 && nombres%2!=0){
            System.out.println("Tableau impossible");
        }
        else{
            //Implémentation du tirage au sort des huitièmes de finales
            for (int i=0;i<nombres;i++){
                huitiemes.add(new Equipe( i,"Equipe "+i));
            }
        }
    }
    public static void main(String[]args){
        tournoiGroupe(31);
        //tournoiTableau(4);

    }
}
