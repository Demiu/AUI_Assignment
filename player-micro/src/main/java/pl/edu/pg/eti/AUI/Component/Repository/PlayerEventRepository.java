package pl.edu.pg.eti.AUI.Component.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.AUI.DTO.Event.CreatePlayerEventRequest;
import pl.edu.pg.eti.AUI.Player;

@Repository
public class PlayerEventRepository {

    private RestTemplate companyMicroRest;

    @Autowired PlayerEventRepository(@Value("${internal_api.companies.url}") String companyUrl) {
        companyMicroRest = new RestTemplateBuilder().rootUri(companyUrl).build();
    }

    public void create(@NonNull Player player) {
        companyMicroRest.postForLocation("/players", CreatePlayerEventRequest.from(player));
    }

    public void delete(@NonNull Player player) {
        companyMicroRest.delete("/players/{id}", player.getId());
    }
}
