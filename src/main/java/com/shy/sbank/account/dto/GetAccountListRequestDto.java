package com.shy.sbank.account.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class GetAccountListRequestDto {
    private UUID memberId;

    @Builder
    public GetAccountListRequestDto(UUID memberId) {
        this.memberId = memberId;
    }
}
