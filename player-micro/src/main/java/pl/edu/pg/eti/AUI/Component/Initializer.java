package pl.edu.pg.eti.AUI.Component;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.Player;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private PlayerService playerService;

    @Autowired
    public Initializer(@NonNull PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostConstruct
    private void init() {
        if (playerService.findAll().stream().findAny().isPresent()) {
            return;
        }

        Player jim = Player.builder().name("Jim").money(1000).build();
        Player bob = Player.builder().name("Bob").money(1250).build();

        playerService.create(jim);
        playerService.create(bob);
    }
}
