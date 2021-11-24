package pl.edu.pg.eti.AUIPlayer.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUIPlayer.Player;

@Data
@Builder
public class PlayerSummary {
    private Long id;
    private String name;

    public static PlayerSummary from(@NonNull Player player) {
        return PlayerSummary.builder()
                .id(player.getId())
                .name(player.getName())
                .build();
    }
}
