package pl.edu.pg.eti.AUICompany.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import pl.edu.pg.eti.AUICompany.Company;

import java.util.List;

@Data
@Builder
public class GetCompaniesResponse {

    @Singular
    private List<CompanySummary> companies;

    public static GetCompaniesResponse from(@NonNull List<Company> companies) {
        var builder = GetCompaniesResponse.builder();
        companies.stream()
                .map(CompanySummary::from)
                .forEach(builder::company);
        return builder.build();
    }
}
