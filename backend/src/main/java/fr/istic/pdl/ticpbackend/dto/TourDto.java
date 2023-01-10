package fr.istic.pdl.ticpbackend.dto;

import fr.istic.pdl.ticpbackend.model.MatchTableau;

import java.util.List;

public class TourDto {
    private String tour;
    private int numTour;
    private List<MatchTableau> matchs;

    public TourDto() {
    }

    public TourDto(String tour, int numTour, List<MatchTableau> matchs) {
        this.tour = tour;
        this.numTour = numTour;
        this.matchs = matchs;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public int getNumTour() {
        return numTour;
    }

    public void setNumTour(int numTour) {
        this.numTour = numTour;
    }

    public List<MatchTableau> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<MatchTableau> matchs) {
        this.matchs = matchs;
    }
    public void addMatch(MatchTableau matchTableau){
        this.matchs.add(matchTableau);
    }
}
