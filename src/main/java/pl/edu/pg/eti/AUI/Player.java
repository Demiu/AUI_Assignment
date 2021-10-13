package pl.edu.pg.eti.AUI;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Player {
    @NonNull private String name;
    private int money;
    @NonNull @Builder.Default private Map<Company, Integer> held_shares = new HashMap<>();
}
