package fr.istic.pdl.ticpbackend.dto;

import fr.istic.pdl.ticpbackend.model.Equipe;

public class EquipeDto {
    private Equipe equipe;
    private int rang;
    private int points;
    private int victoires;

    public EquipeDto() {
    }

    public EquipeDto(Equipe equipe, int rang, int points, int victoires) {
        this.equipe = equipe;
        this.rang = rang;
        this.points = points;
        this.victoires = victoires;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getVictoires() {
        return victoires;
    }

    public void setVictoires(int victoires) {
        this.victoires = victoires;
    }
}
