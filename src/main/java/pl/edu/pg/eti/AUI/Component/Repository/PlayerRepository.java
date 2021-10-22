package pl.edu.pg.eti.AUI.Component.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AUI.Player;
import pl.edu.pg.eti.AUI.Component.Storage;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PlayerRepository {
    private Storage storage;

    @Autowired
    public PlayerRepository(@NonNull Storage storage) {
        this.storage = storage;
    }

    public void save(@NonNull Player player) {
        if (storage.getPlayers().stream().anyMatch(p -> p.getName().equals(player.getName()))) {
            throw new IllegalArgumentException();
        }
        storage.getPlayers().add(player);
    }

    public Optional<Player> find(String name) {
        return storage.getPlayers().stream().filter(p -> p.getName().equals(name)).findFirst();
    }

    public Stream<Player> findAll() {
        return storage.getPlayers().stream();
    }

    public void delete(@NonNull String name) {
        Optional<Player> p = find(name);
        if (p.isPresent()) {
            storage.getPlayers().remove(p.get());
        } else {
            throw new IllegalArgumentException(String.format("Couldn't find Player with name %s", name));
        }
    }
}
