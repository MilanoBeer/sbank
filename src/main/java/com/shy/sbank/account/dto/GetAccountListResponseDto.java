package com.shy.sbank.account.dto;

import com.shy.sbank.product.entity.Product;
import com.shy.sbank.product.entity.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetAccountListResponseDto {
    // 계좌정보 조회해오는데 필요한 정보들 선언
    private Long id;
//    private ProductType productType;
    private Product product;
    private LocalDateTime createYmdt;
    private LocalDateTime expireYmdt;
    private int status;
    private Double balance;
    private int remainWithdraw;

    @Builder
    public GetAccountListResponseDto(Long id, Product product, LocalDateTime createYmdt, LocalDateTime expireYmdt, int status, Double balance, int remainWithdraw) {
        this.id = id;
        this.product = product;
        this.createYmdt = createYmdt;
        this.expireYmdt = expireYmdt;
        this.status = status;
        this.balance = balance;
        this.remainWithdraw = remainWithdraw;
    }

}
