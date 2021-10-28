package pl.edu.pg.eti.AUI.Component.Controller;

import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.AUI.Component.Service.CompanyService;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.DTO.*;
import pl.edu.pg.eti.AUI.Player;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players/{player_id:[0-9]+}/companies")
public class PlayerCompanyController {

    private PlayerService playerService;
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<GetCompaniesResponse> getCompanies(@PathVariable("player_id") Long p_id) {
        return playerService.find(p_id, Player::fetchOwnedCompanies)
                .map(p -> ResponseEntity.ok(GetCompaniesResponse.from(p.getOwnedCompanies())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable("player_id") Long p_id,
                                                         @PathVariable("id") Long id) {
        return companyService.find(id, p_id)
                .map(c -> ResponseEntity.ok(GetCompanyResponse.from(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCompany(@PathVariable("player_id") Long p_id,
                                              @RequestBody CreateCompanyRequest request,
                                              UriComponentsBuilder builder) {
        if (companyService.find(request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        if (playerService.find(request.getOwnerPlayerId()).isEmpty() && playerService.find(p_id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        if (request.getOwnerPlayerId() != p_id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var company = companyService.create(request.into(id -> playerService.find(id).get()));
        return ResponseEntity.created(
                builder
                        .pathSegment("api", "players", "{p_id}", "companies", "{id}")
                        .buildAndExpand(p_id, company.getId())
                        .toUri())
                .build();
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity<Void> updateCompany(@PathVariable("player_id") Long p_id,
                                              @RequestBody UpdateCompanyRequest request,
                                              @PathVariable("id") Long id) {
        var company = companyService.find(id, p_id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var sameNameCompany = companyService.find(request.getName());
        if (sameNameCompany.isPresent() && company.get().getId() != sameNameCompany.get().getId()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        request.apply(company.get());
        companyService.update(company.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("player_id") Long p_id,
                                              @PathVariable("id") Long id) {
        var company = companyService.find(id, p_id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        companyService.delete(company.get().getId());
        return ResponseEntity.accepted().build();
    }
}
