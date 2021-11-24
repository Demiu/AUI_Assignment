package pl.edu.pg.eti.AUI.DTO.Event;

import lombok.Builder;
import lombok.Data;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
public class CreatePlayerEventRequest {
    private Long id;

    public Player into() {
        return Player.builder()
                .id(getId())
                .build();
    }
}
