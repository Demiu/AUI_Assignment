package pl.edu.pg.eti.AUIPlayer.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUIPlayer.Component.Repository.PlayerEventRepository;
import pl.edu.pg.eti.AUIPlayer.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUIPlayer.Player;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerEventRepository eventRepository;

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
        var created = repository.save(player);
        eventRepository.create(player);
        return created;
    }

    @Transactional
    public void update(@NonNull Player player) {
        repository.save(player); // TODO: check if exists?
    }

    @Transactional
    public void delete(@NonNull Player player) {
        eventRepository.delete(player);
        repository.delete(player); // TODO: check if exists?
    }
}
