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
    @Column(unique = true)
    private String name;

    private int money;

    @Singular
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<Company> ownedCompanies;
}
