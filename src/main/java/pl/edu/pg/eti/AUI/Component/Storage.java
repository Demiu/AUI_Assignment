package pl.edu.pg.eti.AUI.Component;

import org.springframework.stereotype.Component;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Player;

import java.util.HashSet;
import java.util.Set;

@Component
public class Storage {
    private Set<Company> companies = new HashSet<>();
    private Set<Player> players = new HashSet<>();

    public Set<Company> getCompanies() {
        return companies;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
