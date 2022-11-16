package fr.istic.ticp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Groupe {
    private int ID;
    private String nom;
    private Set<Equipe> equipes;
    private List<Equipe> meilleurs;
    private List<Equipe> repeches;
    private List<Match> matchs=new ArrayList<>();

    public Groupe() {
    }

    public Groupe(int ID, String nom, Set<Equipe> equipes) {
        this.ID = ID;
        this.nom = nom;
        this.equipes = equipes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(Set<Equipe> equipes) {
        this.equipes = equipes;
    }

    public List<Equipe> getMeilleurs() {
        return meilleurs;
    }

    public void setMeilleurs(List<Equipe> meilleurs) {
        this.meilleurs = meilleurs;
    }

    public List<Equipe> getRepeches() {
        return repeches;
    }

    public void setRepeches(List<Equipe> repeches) {
        this.repeches = repeches;
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groupe)) return false;
        Groupe groupe = (Groupe) o;
        return Objects.equals(getID(), groupe.getID()) && Objects.equals(getNom(), groupe.getNom()) && Objects.equals(getEquipes(), groupe.getEquipes()) && Objects.equals(getMeilleurs(), groupe.getMeilleurs()) && Objects.equals(getRepeches(), groupe.getRepeches());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNom(), getEquipes(), getMeilleurs(), getRepeches());
    }

}
