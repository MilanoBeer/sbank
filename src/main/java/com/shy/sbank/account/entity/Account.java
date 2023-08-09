package com.shy.sbank.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private UUID memberId;

    @Column(name = "product_id")
    private Long productId;

    private Double balance;

    @Column(name = "account_name")
    private String accountName;

    @CreatedDate
    @Column(name = "create_ymdt", nullable = false)
    private LocalDateTime createYmdt;


    @Column(name = "expire_date")
    private LocalDateTime expireYmdt;

}
