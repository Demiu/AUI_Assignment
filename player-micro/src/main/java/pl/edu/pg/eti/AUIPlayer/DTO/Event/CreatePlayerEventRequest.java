package pl.edu.pg.eti.AUIPlayer.DTO.Event;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUIPlayer.Player;

@Data
@Builder
public class CreatePlayerEventRequest {
    private Long id;

    public static CreatePlayerEventRequest from(@NonNull Player player) {
        return CreatePlayerEventRequest.builder()
                .id(player.getId())
                .build();
    }
}
