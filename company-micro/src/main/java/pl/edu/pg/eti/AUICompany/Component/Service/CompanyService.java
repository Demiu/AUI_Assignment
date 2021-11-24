package pl.edu.pg.eti.AUICompany.Component.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.AUICompany.Company;
import pl.edu.pg.eti.AUICompany.Component.Repository.CompanyRepository;
import pl.edu.pg.eti.AUICompany.Component.Repository.PlayerRepository;
import pl.edu.pg.eti.AUICompany.Player;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {
    private final CompanyRepository repository;
    private final PlayerRepository playerRepository;

    public Optional<Company> find(@NonNull Long id) {
        return repository.findById(id);
    }

    public Optional<Company> find(@NonNull Long id, @NonNull Long playerId) {
        return playerRepository.findById(playerId).flatMap(p -> find(id, p));
    }

    public Optional<Company> find(@NonNull Long id, @NonNull Player owner) {
        return repository.findByIdAndOwner(id, owner);
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
