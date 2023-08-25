package com.shy.sbank.account.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RemitAccountRequestDto {

    private UUID senderId;
    private Long senderAccountId;
    private Double money;

    private UUID receiverId;
    private Long receiverAccountId;

    @Builder
    public RemitAccountRequestDto(UUID senderId, Long senderAccountId, Double money, UUID receiverId, Long receiverAccountId) {
        this.senderId = senderId;
        this.senderAccountId = senderAccountId;
        this.money = money;
        this.receiverId = receiverId;
        this.receiverAccountId = receiverAccountId;
    }
}
