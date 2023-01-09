package fr.istic.pdl.ticpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
public class Information {
    @Id
    @GeneratedValue
    private Long id;
    private String info;
    @ManyToOne
    @JoinColumn(name = "tournoi_id")
    @JsonBackReference
    private Tournoi tournoi;

    public Information() {
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
