package com.shy.sbank.domain.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class DepositAccountRequestDto {

    private UUID memberId;

    private Long accountId;

    // TODO :  money : "Big Decimal" / 2진수, 1 + 1 = 3 // 10 -> 2 금액오차 /
    private Double money;

    @Builder
    public DepositAccountRequestDto(UUID memberId, Long accountId, Double money) {
        this.memberId = memberId;
        this.accountId = accountId;
        this.money = money;
    }

}
