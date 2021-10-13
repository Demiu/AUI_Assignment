package pl.edu.pg.eti.AUI;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

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

    public Optional<Company> find(String name) {
        return repository.find(name);
    }

    public Stream<Company> findAll() {
        return repository.findAll();
    }

    public void delete(@NonNull String name) {
        repository.delete(name);
    }
}
