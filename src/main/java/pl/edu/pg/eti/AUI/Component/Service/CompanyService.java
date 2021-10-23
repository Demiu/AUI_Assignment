package pl.edu.pg.eti.AUI.Component.Service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Component.Repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository repository;

    @Autowired
    public CompanyService(@NonNull CompanyRepository repository) {
        this.repository = repository;
    }

    public void save(Company company) {
        repository.save(company);
    }

    public Optional<Company> find(@NonNull Long id) {
        return repository.findById(id);
    }

    public Optional<Company> find(@NonNull String companyName) {
        return repository.findByName(companyName);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company create(@NonNull Company company) {
        return repository.save(company);
    }

    public void update(@NonNull Company company) {
        repository.save(company); // TODO: check if exists?
    }

    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

    public void delete(@NonNull Company company) {
        repository.delete(company); // TODO: check if exists?
    }
}
