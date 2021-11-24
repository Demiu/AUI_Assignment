package pl.edu.pg.eti.AUI.Component.Repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Player;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(@NonNull String name);
    Optional<Company> findByIdAndOwner(@NonNull Long id, @NonNull Player owner);
}
