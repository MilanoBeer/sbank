package com.shy.sbank.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AccountRegisterRequestDto {

    private UUID memberId;
    private Long productId;

    @Size(min=2, max= 20, message = "계좌이름은 2글자이상, 20글자 이하로 작성해주세요.")
    private String accountName;

    @DateTimeFormat
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String expireYmdt;

    // 계좌생성
    @Builder
    public AccountRegisterRequestDto(UUID memberId, Long productId, String accountName, String expireYmdt) {
        this.memberId = memberId;
        this.productId = productId;
        this.accountName = accountName;
        this.expireYmdt = expireYmdt;
    }
}
