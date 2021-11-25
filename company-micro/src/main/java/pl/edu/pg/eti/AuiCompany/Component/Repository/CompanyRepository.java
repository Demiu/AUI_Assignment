package pl.edu.pg.eti.AuiCompany.Component.Repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AuiCompany.Company;
import pl.edu.pg.eti.AuiCompany.Player;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(@NonNull String name);
    Optional<Company> findByIdAndOwner(@NonNull Long id, @NonNull Player owner);
}
