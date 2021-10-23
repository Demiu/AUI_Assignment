package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
public class UpdatePlayerRequest {

    private String name;

    public void apply(@NonNull Player player) {
        player.setName(getName());
    }
}
