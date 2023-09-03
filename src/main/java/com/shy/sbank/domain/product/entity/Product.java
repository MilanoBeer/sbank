package com.shy.sbank.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    private float interest;

    @Builder
    public Product(Long id,String productName, ProductType productType, float interest) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.interest = interest;
    }
}
