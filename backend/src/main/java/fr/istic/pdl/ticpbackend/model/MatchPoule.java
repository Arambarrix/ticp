package fr.istic.pdl.ticpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MatchPoule extends Match {
    @OneToOne
    @JoinColumn(name="poule_id")
    @JsonBackReference
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
