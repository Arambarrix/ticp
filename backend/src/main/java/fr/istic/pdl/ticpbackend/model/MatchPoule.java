package fr.istic.pdl.ticpbackend.model;

import javax.persistence.Entity;

@Entity
public class MatchPoule implements Match{
    @Override
    public Equipe getEquipeA() {
        return null;
    }

    @Override
    public Equipe getEquipeB() {
        return null;
    }

    @Override
    public int getScoreA() {
        return 0;
    }

    @Override
    public int getScoreB() {
        return 0;
    }

    @Override
    public String getLieu() {
        return null;
    }

    @Override
    public void setEquipeA(Equipe teamA) {

    }

    @Override
    public void setEquipeB(Equipe teamB) {

    }

    @Override
    public void setScoreA(int scoreA) {

    }

    @Override
    public void setScoreB(int scoreB) {

    }

    @Override
    public void setLieu(String lieu) {

    }
}
