package fr.istic.ticp.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Match {
    private int ID;
    private Map<Equipe,Integer> domicile = new HashMap<>();
    private Map<Equipe,Integer> exterieur= new HashMap<>();
    private String lieu;
    private LocalDate date;

    public Match() {
    }

    public Match(int ID, Map<Equipe, Integer> domicile, Map<Equipe, Integer> exterieur, String lieu) {
        this.ID = ID;
        this.domicile = domicile;
        this.exterieur = exterieur;
        this.lieu = lieu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Equipe getDomicileEquipe(){
        Equipe equipe = new Equipe();
        for (Map.Entry<Equipe, Integer> pair : domicile.entrySet()) {
            equipe = pair.getKey();
        }
        return equipe;
    }
    public int getDomicileScore(){
        return domicile.get(getDomicileEquipe());
    }
    public void setDomicileEquipe(Equipe equipe){
        domicile.put(equipe,0);
    }
    public void setDomicileScore(Equipe equipe,int score){
        if(domicile.containsKey(equipe)){
            domicile.replace(equipe,domicile.get(equipe),score);
        }
    }

    public Equipe getExterieurEquipe(){
        Equipe equipe = new Equipe();
        for (Map.Entry<Equipe, Integer> pair : exterieur.entrySet()) {
            equipe = pair.getKey();
        }
        return equipe;
    }
    public int getExterieurScore(){
        return exterieur.get(getExterieurEquipe());
    }
    public void setExterieurEquipe(Equipe equipe){
        exterieur.put(equipe,0);
    }
    public void setExterieurScore(Equipe equipe,int score){
        if(exterieur.containsKey(equipe)){
            exterieur.replace(equipe,exterieur.get(equipe),score);
        }
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Match "+ID+": "+getDomicileEquipe().getNom()+" "+getDomicileScore()+"-"+getExterieurScore()+" "+getExterieurEquipe().getNom();
    }
}
