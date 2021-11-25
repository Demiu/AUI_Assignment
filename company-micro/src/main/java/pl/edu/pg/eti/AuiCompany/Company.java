package pl.edu.pg.eti.AuiCompany;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Player owner;

    private int sharePrice;

    private int remainingShares;
}
