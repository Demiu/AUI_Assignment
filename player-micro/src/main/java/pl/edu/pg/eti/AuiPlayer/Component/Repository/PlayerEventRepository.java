package pl.edu.pg.eti.AuiPlayer.Component.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.AuiPlayer.DTO.Event.CreatePlayerEventRequest;
import pl.edu.pg.eti.AuiPlayer.Player;

@Repository
public class PlayerEventRepository {

    private final RestTemplate companyMicroRest;

    @Autowired
    PlayerEventRepository(@Value("${api.companies.url}") String companyUrl) {
        companyMicroRest = new RestTemplateBuilder().rootUri(companyUrl).build();
    }

    public void create(@NonNull Player player) {
        companyMicroRest.postForLocation("/api/players", CreatePlayerEventRequest.from(player));
    }

    public void delete(@NonNull Player player) {
        companyMicroRest.delete("/api/players/{id}", player.getId());
    }
}
