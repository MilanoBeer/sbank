package com.shy.sbank.domain.product.dto;

import com.shy.sbank.domain.product.entity.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
public class ProductRegisterRequestDto {

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private Float interest;

    @Builder
    public ProductRegisterRequestDto(String productName, ProductType productType, Float interest) {
        this.productName = productName;
        this.productType = productType;
        this.interest = interest;
    }

}
