package com.shy.sbank.account.repository;

import com.shy.sbank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByMemberIdAndAccountName(UUID memberId, String accountName);
//    public Account findByMemberIdAndId(UUID memberId, Long accountId);
    public List<Account> findAllByMemberId(UUID memberId);

    public Account findByIdAndMemberId(Long accountId, UUID memberId);

}
