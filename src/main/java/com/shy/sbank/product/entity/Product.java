package com.shy.sbank.product.entity;

import com.shy.sbank.account.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    // 하나의 account는 하나의 product
    // account에서 product_type을 통해

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
