package site.yazago.spenging.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBalance {
    Long accountId;
    String accountName;
    String color;
    BigDecimal balance;
    BigDecimal goalCost;
}
