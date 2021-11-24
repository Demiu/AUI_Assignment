package pl.edu.pg.eti.AUICompany.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUICompany.Company;

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
