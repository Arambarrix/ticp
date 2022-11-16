package fr.istic.ticp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Joueur extends Utilisateur{
    private final String ROLE="joueur";
    private boolean chef;
    private Equipe equipe;

}
