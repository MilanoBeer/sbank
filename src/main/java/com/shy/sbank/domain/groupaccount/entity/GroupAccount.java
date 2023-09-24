package com.shy.sbank.domain.groupaccount.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.UUID;

//@Entity // Entity가 붙이면 JPA의 관리대상으로 취급되는거
@Document(collection = "group_account")
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Column(name = "group_account_name")
    private String groupAccountName;

    @Column(name = "master_id")
    private UUID masterId;

    @Column(name = "product_id")
    private Long productId;

    @Builder
    GroupAccount(ObjectId id, String groupAccountName, UUID masterId, Long productId) {
        this.id = id;
        this.groupAccountName = groupAccountName;
        this.masterId = masterId;
        this.productId = productId;
    }

}
