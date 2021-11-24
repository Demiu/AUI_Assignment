package pl.edu.pg.eti.AUI.DTO.Event;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUI.Player;

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
