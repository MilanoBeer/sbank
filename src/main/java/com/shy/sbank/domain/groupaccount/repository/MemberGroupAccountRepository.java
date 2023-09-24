package com.shy.sbank.domain.groupaccount.repository;

import com.shy.sbank.domain.groupaccount.entity.MemberGroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberGroupAccountRepository extends JpaRepository<MemberGroupAccount, Long> {
}
