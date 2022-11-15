package ma.enset.jpaenset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POULE")
public class Poule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_poule;
    @Column(name = "NOM_POULE")
    @NotEmpty
    @Size(min=10, max=100)
    private String nom_poule;
}
