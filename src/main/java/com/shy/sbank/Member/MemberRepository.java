package com.shy.sbank.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public UUID save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(UUID id) {
        return em.find(Member.class, id);
    }

}
