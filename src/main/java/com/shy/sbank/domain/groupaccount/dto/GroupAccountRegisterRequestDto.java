package com.shy.sbank.domain.groupaccount.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class GroupAccountRegisterRequestDto {

    private UUID memberId;
    private Long productId;

    @Size(min=2, max=20, message = "그룹계좌 이름은 2글자 이상, 20글자 이하로 작성해주세요.")
    private String groupAccountName;

    @Builder
    public GroupAccountRegisterRequestDto(UUID memberId, Long productId, String groupAccountName) {
        this.memberId = memberId;
        this.productId = productId;
        this.groupAccountName = groupAccountName;
    }
}
