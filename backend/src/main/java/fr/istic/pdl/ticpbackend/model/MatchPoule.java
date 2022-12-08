package fr.istic.pdl.ticpbackend.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MatchPoule extends Match {
    @OneToOne
    @JoinColumn(name="poule_id")
    private Poule poule;


    @Override
    public String toString() {
        return super.toString()+"MatchPoule{" +
                "poule=" + poule +
                '}';
    }
}
