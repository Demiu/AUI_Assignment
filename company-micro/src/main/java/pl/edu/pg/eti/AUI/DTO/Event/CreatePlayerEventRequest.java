package pl.edu.pg.eti.AUI.DTO.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerEventRequest {
    private Long id;

    public Player into() {
        return Player.builder()
                .id(getId())
                .build();
    }
}
