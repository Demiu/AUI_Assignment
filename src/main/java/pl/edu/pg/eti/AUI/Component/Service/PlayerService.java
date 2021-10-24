package pl.edu.pg.eti.AUI.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUI.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUI.Player;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {
    private PlayerRepository repository;

    public Optional<Player> find(@NonNull Long id) {
        return repository.findById(id);
    }

    public Optional<Player> find(@NonNull String playerName) {
        return repository.findByName(playerName);
    }

    public List<Player> findAll() {
        return repository.findAll();
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
