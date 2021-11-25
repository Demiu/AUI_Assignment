package pl.edu.pg.eti.AuiCompany.Component.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AuiCompany.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
