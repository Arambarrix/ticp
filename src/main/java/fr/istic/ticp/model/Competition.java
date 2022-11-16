package fr.istic.ticp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Competition {
    private Long ID;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Equipe vainqueur;

}
