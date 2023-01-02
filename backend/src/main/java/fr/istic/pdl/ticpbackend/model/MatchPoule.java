package fr.istic.pdl.ticpbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Poule poule;

    public Poule getPoule() {
        return poule;
    }


    @Override
    public String toString() {
        return super.toString()+"MatchPoule{" +
                "poule=" + poule +
                '}';
    }
}
