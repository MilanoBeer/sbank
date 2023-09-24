package com.shy.sbank.domain.groupaccount.entity;

// 다대다 연결테이블 역할

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shy.sbank.domain.account.entity.Account;
import com.shy.sbank.domain.groupaccount.dto.GroupAccountJoinRequestDto;
import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGroupAccount {

    @Id
    @GeneratedValue
    @Column(name = "member_group_account_id")
    private Long id;

    private String groupAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "memberGroupAccount", fetch = FetchType.LAZY)
    private List<Member> memberList = new ArrayList<>();

    @Builder
    MemberGroupAccount(String groupAccountId, Product product, List<Member> memberList){
        this.groupAccountId = groupAccountId;
        this.product = product;
        this.memberList = memberList;
    }

    // 단체계좌 join method
    public void addGroupAccountMember(List<Member> memberList) {
        this.memberList = memberList;
    }

}
