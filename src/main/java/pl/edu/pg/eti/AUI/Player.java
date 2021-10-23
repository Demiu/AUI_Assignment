package pl.edu.pg.eti.AUI;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private int money;

    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    @Singular
    private List<Company> ownedCompanies;
}
