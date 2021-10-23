package pl.edu.pg.eti.AUI.DTO;

import lombok.Builder;
import lombok.Data;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Player;

import java.util.function.Function;

@Data
@Builder
public class CreateCompanyRequest {

    private String name;
    private Long ownerPlayerId;
    private int sharePrice;
    private int remainingShares;

    public Company into(Function<Long, Player> playerFinder) {
        return Company.builder()
                .name(getName())
                .owner(playerFinder.apply(ownerPlayerId))
                .sharePrice(getSharePrice())
                .remainingShares(getRemainingShares())
                .build();
    }
}
