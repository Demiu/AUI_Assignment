package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import pl.edu.pg.eti.AUI.Player;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class GetPlayersResponse {

    @Singular
    List<PlayerSummary> players;

    public static GetPlayersResponse from(@NonNull Collection<Player> players) {
        var builder = GetPlayersResponse.builder();
        players.stream()
                .map(PlayerSummary::from)
                .forEach(builder::player);
        return builder.build();
    }
}
