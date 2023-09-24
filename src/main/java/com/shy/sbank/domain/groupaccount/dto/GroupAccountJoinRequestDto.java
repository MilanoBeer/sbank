package com.shy.sbank.domain.groupaccount.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class GroupAccountJoinRequestDto {

    // 기존 그룹계좌에 조인하는 사용자 ID
    private UUID memberId;

    // 새로운 가입자는 그룹계좌이름을 통해서 가입할 계좌를 찾을 것
    private String groupAccountName;

    @Builder
    GroupAccountJoinRequestDto(UUID memberId, String groupAccountName) {
        this.memberId = memberId;
        this.groupAccountName = groupAccountName;
    }

}
