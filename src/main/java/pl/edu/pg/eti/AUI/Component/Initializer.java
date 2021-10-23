package pl.edu.pg.eti.AUI.Component;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Component.Service.CompanyService;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.Player;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private CompanyService companyService;
    private PlayerService playerService;

    @Autowired
    public Initializer(@NonNull CompanyService companyService, @NonNull PlayerService playerService) {
        this.companyService = companyService;
        this.playerService = playerService;
    }

    @PostConstruct
    private void init() {
        if (companyService.findAll().stream().findAny().isPresent() || playerService.findAll().stream().findAny().isPresent()) {
            return;
        }

        Player jim = Player.builder().name("Jim").money(1000).build();
        Player bob = Player.builder().name("Bob").money(1250).build();

        playerService.save(jim);
        playerService.save(bob);

        Company red = new Company(null, "RED Inc.", jim, 100, 20);
        Company blue = new Company(null, "BLU Ltd.", bob, 200, 20);

        companyService.save(red);
        companyService.save(blue);
    }
}
