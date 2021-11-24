package pl.edu.pg.eti.AUICompany.Component.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AUICompany.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
