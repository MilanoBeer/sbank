package com.shy.sbank.domain.groupaccount.repository;

import com.shy.sbank.domain.groupaccount.entity.GroupAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface GroupAccountRepository extends MongoRepository<GroupAccount, String> {
}
