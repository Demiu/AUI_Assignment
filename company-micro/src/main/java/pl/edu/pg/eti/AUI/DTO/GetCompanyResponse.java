package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUI.Company;

@Data
@Builder
public class GetCompanyResponse {

    private Long id;
    private String name;
    private Long ownerId;
    private int sharePrice;
    private int remainingShares;

    public static GetCompanyResponse from(@NonNull Company company) {
        return GetCompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .ownerId(company.getOwner().getId())
                .sharePrice(company.getSharePrice())
                .remainingShares(company.getRemainingShares())
                .build();
    }
}
