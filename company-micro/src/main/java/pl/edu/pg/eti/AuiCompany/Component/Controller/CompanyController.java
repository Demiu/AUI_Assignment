package pl.edu.pg.eti.AuiCompany.Component.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.AuiCompany.Component.Service.CompanyService;
import pl.edu.pg.eti.AuiCompany.Component.Service.PlayerService;
import pl.edu.pg.eti.AuiCompany.DTO.CreateCompanyRequest;
import pl.edu.pg.eti.AuiCompany.DTO.GetCompaniesResponse;
import pl.edu.pg.eti.AuiCompany.DTO.GetCompanyResponse;
import pl.edu.pg.eti.AuiCompany.DTO.UpdateCompanyRequest;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<GetCompaniesResponse> getCompanies() {
        return ResponseEntity.ok(GetCompaniesResponse.from(companyService.findAll()));
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable("id") Long id) {
        return companyService.find(id)
                .map(c -> ResponseEntity.ok(GetCompanyResponse.from(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCompany(@RequestBody CreateCompanyRequest request, UriComponentsBuilder builder) {
        if (companyService.find(request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        if (playerService.find(request.getOwnerPlayerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        var company = companyService.create(request.into(id -> playerService.find(id).get()));
        return ResponseEntity.created(
                builder
                        .pathSegment("api", "companies", "{id}")
                        .buildAndExpand(company.getId())
                        .toUri())
                .build();
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity<Void> updateCompany(@RequestBody UpdateCompanyRequest request, @PathVariable("id") Long id) {
        var company = companyService.find(id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var sameNameCompany = companyService.find(request.getName());
        if (sameNameCompany.isPresent() && !(company.get().getId().equals(sameNameCompany.get().getId()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        request.apply(company.get());
        companyService.update(company.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        var company = companyService.find(id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        companyService.delete(company.get().getId());
        return ResponseEntity.accepted().build();
    }
}
