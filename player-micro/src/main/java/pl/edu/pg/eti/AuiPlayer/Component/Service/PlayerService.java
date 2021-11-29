package pl.edu.pg.eti.AuiPlayer.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AuiPlayer.Component.Repository.PlayerEventRepository;
import pl.edu.pg.eti.AuiPlayer.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AuiPlayer.Player;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerEventRepository eventRepository;

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
