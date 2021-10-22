package pl.edu.pg.eti.AUI;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class Company {
    @NonNull private String name;
    @NonNull private Player owner;
    private int share_price;
    private int remaining_shares;
}
