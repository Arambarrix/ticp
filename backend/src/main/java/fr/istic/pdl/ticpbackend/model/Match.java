package fr.istic.pdl.ticpbackend.model;

import javafx.util.Pair;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@DynamicUpdate
public abstract class Match implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name="equipeA_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Equipe equipeA;

    @OneToOne
    @JoinColumn(name="equipeB_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Equipe equipeB;

    private Integer resultA;

    private Integer resultB;

    private String lieu;

    public Match() {
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public Equipe getEquipeB(){
        return equipeB;
    }

    public int getScoreA(){
        return resultA;
    };

    public int getScoreB(){
        return resultB;
    };

    public String getLieu(){
        return lieu;
    };

    public void setEquipeA(Equipe teamA){
        equipeA=teamA;
        resultA = 0;
    };
    public void setEquipeB(Equipe teamB){
        equipeB=teamB;
        resultB = 0;
    };
    public void setScoreA(int scoreA){
        resultA=scoreA;
    };

    public void setScoreB(int scoreB){
        resultB=scoreB;
    };

    public void setLieu(String lieu){
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
