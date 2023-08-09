package com.shy.sbank.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_type")
    private short productType; // 래퍼타입, 원시타입 / 대문자 Short가 null허용, 소문자가 안되고, 그

    private float interest;

    @Builder
    public Product(String productName, short productType, float interest) {
        this.productName = productName;
        this.productType = productType;
        this.interest = interest;
    }
}
