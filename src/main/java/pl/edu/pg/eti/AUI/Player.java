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
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Company> ownedCompanies;

    @PreRemove
    private void preRemove() {
        for(var company : ownedCompanies) {
            company.setOwner(null);
        }
    }

    // this is a function to be send as a delegate to services when fetching a player we also want owned companies
    public void fetchOwnedCompanies() {
        ownedCompanies.size();
    }
}
