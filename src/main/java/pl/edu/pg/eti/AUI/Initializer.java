package pl.edu.pg.eti.AUI;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        if (companyService.findAll().findAny().isPresent() || playerService.findAll().findAny().isPresent()) {
            return;
        }

        Player jim = Player.builder().name("Jim").money(1000).build();
        Player bob = Player.builder().name("Bob").money(1250).build();

        playerService.save(jim);
        playerService.save(bob);

        Company red = new Company("RED Inc.", jim, 100, 20);
        Company blue = new Company("BLU Ltd.", bob, 200, 20);

        companyService.save(red);
        companyService.save(blue);
    }
}
