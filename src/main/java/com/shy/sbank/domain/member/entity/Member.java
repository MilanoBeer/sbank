package com.shy.sbank.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shy.sbank.domain.account.entity.Account;
import com.shy.sbank.domain.groupaccount.entity.MemberGroupAccount;
import lombok.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_account_id")
    private MemberGroupAccount memberGroupAccount;

    @Builder
    public Member(String name, String email, String password, List<Account> account) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
    }

}
