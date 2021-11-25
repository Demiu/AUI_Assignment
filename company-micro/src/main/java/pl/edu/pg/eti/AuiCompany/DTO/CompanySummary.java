package pl.edu.pg.eti.AuiCompany.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AuiCompany.Company;

@Data
@Builder
public class CompanySummary {

    private Long id;
    private String name;

    public static CompanySummary from(@NonNull Company company) {
        return CompanySummary.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
