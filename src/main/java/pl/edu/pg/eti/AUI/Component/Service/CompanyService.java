package pl.edu.pg.eti.AUI.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Component.Repository.CompanyRepository;
import pl.edu.pg.eti.AUI.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUI.Player;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {
    private CompanyRepository repository;
    private PlayerRepository playerRepository;

    public Optional<Company> find(@NonNull Long id) {
        return repository.findById(id);
    }

    public Optional<Company> find(@NonNull Long id, @NonNull Long playerId) {
        return playerRepository.findById(playerId).flatMap(p -> find(id, p));
    }

    public Optional<Company> find(@NonNull Long id, @NonNull Player owner) {
        return repository.findByIdAndOwner(id, owner);
    }

    public Optional<Company> find(@NonNull Long id, @NonNull String ownerName) {
        return playerRepository.findByName(ownerName).<Company>flatMap(p -> repository.findByIdAndOwner(id, p));
    }

    public Optional<Company> find(@NonNull String companyName) {
        return repository.findByName(companyName);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Company create(@NonNull Company company) {
        return repository.save(company);
    }

    @Transactional
    public void update(@NonNull Company company) {
        repository.save(company); // TODO: check if exists?
    }

    @Transactional
    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void delete(@NonNull Company company) {
        repository.delete(company); // TODO: check if exists?
    }
}
