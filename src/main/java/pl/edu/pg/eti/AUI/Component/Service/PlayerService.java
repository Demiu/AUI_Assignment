package pl.edu.pg.eti.AUI.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.AUI.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUI.Player;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlayerService {
    private PlayerRepository repository;

    public void save(@NonNull Player player) {
        repository.save(player);
    }

    public Optional<Player> find(@NonNull Long id) {
        return repository.findById(id);
    }

    public Optional<Player> find(@NonNull String player_name) {
        return repository.findByName(player_name);
    }

    public List<Player> findAll() {
        return repository.findAll();
    }

    public Player create(@NonNull Player player) {
        return repository.save(player);
    }

    public void update(@NonNull Player player) {
        repository.save(player); // TODO: check if exists?
    }

    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

    public void delete(@NonNull Player player) {
        repository.delete(player); // TODO: check if exists?
    }
}
