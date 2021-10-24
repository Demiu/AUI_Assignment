package pl.edu.pg.eti.AUI.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUI.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUI.Player;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {
    private PlayerRepository repository;

    public Optional<Player> find(@NonNull Long id, Consumer<Player>... subqueries) {
        var found = repository.findById(id);
        for (var query : subqueries) {
            found.ifPresent(query);
        }
        return found;
    }

    public Optional<Player> find(@NonNull String playerName, Consumer<Player>... subqueries) {
        var found = repository.findByName(playerName);
        for (var query : subqueries) {
            found.ifPresent(query);
        }
        return found;
    }

    public List<Player> findAll(Consumer<Player>... subqueries) {
        var found = repository.findAll();
        for(var query : subqueries) {
            found.forEach(query);
        }
        return found;
    }

    @Transactional
    public Player create(@NonNull Player player) {
        return repository.save(player);
    }

    @Transactional
    public void update(@NonNull Player player) {
        repository.save(player); // TODO: check if exists?
    }

    @Transactional
    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void delete(@NonNull Player player) {
        repository.delete(player); // TODO: check if exists?
    }
}
