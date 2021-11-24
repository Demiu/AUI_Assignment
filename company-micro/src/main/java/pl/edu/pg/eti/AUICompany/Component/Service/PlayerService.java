package pl.edu.pg.eti.AUICompany.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUICompany.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUICompany.Player;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {
    private final PlayerRepository repository;

    public Optional<Player> find(@NonNull Long id, Consumer<Player>... subqueries) {
        var found = repository.findById(id);
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
    public void delete(@NonNull Player player) {
        repository.delete(player); // TODO: check if exists?
    }
}
