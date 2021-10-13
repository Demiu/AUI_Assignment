package pl.edu.pg.eti.AUI;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerService(@NonNull PlayerRepository repository) {
        this.repository = repository;
    }

    public void save(@NonNull Player player) {
        repository.save(player);
    }

    public Optional<Player> find(String name) {
        return repository.find(name);
    }

    public Stream<Player> findAll() {
        return repository.findAll();
    }

    public void delete(@NonNull String name) {
        repository.delete(name);
    }
}
