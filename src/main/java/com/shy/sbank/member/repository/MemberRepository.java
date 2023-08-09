package com.shy.sbank.member.repository;

import com.shy.sbank.member.entity.Member;
import org.springframework.stereotype.Repository;

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

    public Member findById(UUID id) {
        return em.find(Member.class, id);
    }

    // MemberService : login method
    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}
