package pl.edu.pg.eti.AUI.Component.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AUI.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
