package pl.edu.pg.eti.AuiPlayer.DTO.Event;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.AuiCommon.DTO.Event.CreatePlayerEventRequestBase;
import pl.edu.pg.eti.AuiPlayer.Player;

@SuperBuilder
public class CreatePlayerEventRequest extends CreatePlayerEventRequestBase {
    public static CreatePlayerEventRequest from(@NonNull Player player) {
        return CreatePlayerEventRequest.builder()
                .id(player.getId())
                .build();
    }
}
