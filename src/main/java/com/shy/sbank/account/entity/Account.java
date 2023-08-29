package com.shy.sbank.account.entity;

import com.shy.sbank.member.entity.Member;
import com.shy.sbank.product.entity.Product;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long id;

    // ManyToOne : -> EAGER이 기본형 / OneToMany : -> LAZY / Many로 가져올 대상이 많아서..
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // Product객체를 지연로딩으로 설정 -> 실제로 Account타고, Product를 조회할때 쿼리날라감
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "status")
    private int status = 1;

    @Column(name = "remain_withdraw")
    private int remainWithdraw = 3;

    @CreatedDate
    @Column(name = "create_ymdt", nullable = false)
    private LocalDateTime createYmdt;

    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireYmdt;

    @Builder
    public Account(Long id, Member member, Product product, Double balance, String accountName, LocalDateTime createYmdt, LocalDateTime expireYmdt) {
        this.id = id;
        this.member = member;
        this.product = product;
        this.balance = balance;
        this.accountName = accountName;
        this.createYmdt = createYmdt;
        this.expireYmdt = expireYmdt;
    }

    public void updateBalance(Double balance){
        this.balance = balance;
    }
}
