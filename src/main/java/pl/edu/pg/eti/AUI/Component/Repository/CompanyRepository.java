package pl.edu.pg.eti.AUI.Component.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Component.Storage;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CompanyRepository {
    private Storage storage;

    @Autowired
    public CompanyRepository(@NonNull Storage storage) {
        this.storage = storage;
    }

    public void save(@NonNull Company company) {
        if (storage.getCompanies().stream().anyMatch(c -> c.getName().equals(company.getName()))) {
            throw new IllegalArgumentException();
        }
        storage.getCompanies().add(company);
    }

    public Optional<Company> find(String name) {
        return storage.getCompanies().stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    public Stream<Company> findAll() {
        return storage.getCompanies().stream();
    }

    public void delete(@NonNull String name) {
        Optional<Company> company = find(name);
        if (company.isPresent()) {
            storage.getCompanies().remove(company.get());
        } else {
            throw new IllegalArgumentException(String.format("Couldn't find Company with name %s", name));
        }
    }
}
