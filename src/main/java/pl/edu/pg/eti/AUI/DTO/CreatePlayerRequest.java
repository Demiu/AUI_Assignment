package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
public class CreatePlayerRequest {

    private String name;
    private int money; // TODO: remove and add a default value?

    public Player into() {
        return Player.builder()
                .name(getName())
                .money(getMoney())
                .build();
    }
}
