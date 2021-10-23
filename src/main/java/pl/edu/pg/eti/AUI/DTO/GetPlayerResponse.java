package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUI.Player;

@Data
@Builder
public class GetPlayerResponse {

    private Long id;
    private String name;
    private int money;

    public static GetPlayerResponse from(@NonNull Player player) {
        return GetPlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .money(player.getMoney())
                .build();
    }
}
