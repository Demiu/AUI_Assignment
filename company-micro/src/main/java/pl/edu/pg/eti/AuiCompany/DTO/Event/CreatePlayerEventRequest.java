package pl.edu.pg.eti.AuiCompany.DTO.Event;

import pl.edu.pg.eti.AuiCommon.DTO.Event.CreatePlayerEventRequestBase;
import pl.edu.pg.eti.AuiCompany.Player;

public class CreatePlayerEventRequest extends CreatePlayerEventRequestBase {
    public Player into() {
        return Player.builder()
                .id(getId())
                .build();
    }
}
