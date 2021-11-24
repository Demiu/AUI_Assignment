package pl.edu.pg.eti.AUIPlayer.DTO;

import lombok.*;
import pl.edu.pg.eti.AUIPlayer.Player;

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
