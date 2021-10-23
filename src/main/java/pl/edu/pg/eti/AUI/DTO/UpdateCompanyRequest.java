package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Player;

import java.util.function.Function;

@Data
@Builder
public class UpdateCompanyRequest {

    private String name;
    private int sharePrice;

    public void apply(@NonNull Company company) {
        company.setName(getName());
        company.setSharePrice(getSharePrice());
    }
}
