package com.shy.sbank.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shy.sbank.account.entity.Account;
import com.shy.sbank.account.entity.GroupAccount;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "member_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Account> account;

    // GroupAccount - Member
    // 1 : N
    //
    // 하나의 GroupAccount가 여러 명의 Member를 가질 수 있음
    // 하나의 Member가 여러 개의 GroupAccount를 가질 수 있음
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<GroupAccount> groupAccount;

    @Builder
    public Member(String name, String email, String password, List<Account> account, List<GroupAccount> groupAccount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
        this.groupAccount = groupAccount;
    }

}
