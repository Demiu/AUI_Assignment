package pl.edu.pg.eti.AUI;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Player {
    @NonNull private String name;
    private int money;
}
