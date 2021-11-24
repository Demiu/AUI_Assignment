package pl.edu.pg.eti.AUI.DTO;

import lombok.*;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlayerRequest {

    private String name;

    public void apply(@NonNull Player player) {
        player.setName(getName());
    }
}
