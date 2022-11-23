package fr.istic.pdl.ticpbackend.model;

import javafx.util.Pair;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Match {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name="equipeA_id")
    private Equipe equipeA;

    @OneToOne
    @JoinColumn(name="equipeB_id")
    private Equipe equipeB;

    private Pair<Equipe,Integer> resultA;

    private Pair<Equipe,Integer> resultB;

    private String lieu;

    public Match() {
    }

    Equipe getEquipeA() {
        return equipeA;
    }

    Equipe getEquipeB(){
        return equipeB;
    }

    int getScoreA(){
        return resultA.getValue();
    };

    int getScoreB(){
        return resultB.getValue();
    };

    String getLieu(){
        return lieu;
    };

    void setEquipeA(Equipe teamA){
        equipeA=teamA;
        resultA = new Pair<>(teamA,0);
    };
    void setEquipeB(Equipe teamB){
        equipeB=teamB;
        resultB = new Pair<>(teamB,0);
    };
    void setScoreA(int scoreA){
        resultA=new Pair<>(equipeA,scoreA);
    };

    void setScoreB(int scoreB){
        resultB=new Pair<>(equipeB,scoreB);
    };

    void setLieu(String lieu){
        this.lieu=lieu;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) && Objects.equals(getEquipeA(), match.getEquipeA()) && Objects.equals(getEquipeB(), match.getEquipeB()) && Objects.equals(getLieu(), match.getLieu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getEquipeA(), getEquipeB(), getLieu());
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", equipeA=" + equipeA +
                ", equipeB=" + equipeB +
                ", lieu='" + lieu + '\'' +
                '}';
    }
}
