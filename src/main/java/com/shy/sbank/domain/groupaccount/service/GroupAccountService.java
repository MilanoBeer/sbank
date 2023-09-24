package com.shy.sbank.domain.groupaccount.service;

import com.shy.sbank.domain.groupaccount.dto.GroupAccountRegisterRequestDto;
import com.shy.sbank.domain.groupaccount.entity.GroupAccount;
import com.shy.sbank.domain.groupaccount.entity.MemberGroupAccount;
import com.shy.sbank.domain.groupaccount.repository.GroupAccountRepository;
import com.shy.sbank.domain.groupaccount.repository.MemberGroupAccountRepository;
import com.shy.sbank.domain.member.dto.MemberRegisterDto;
import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.member.repository.MemberRepository;
import com.shy.sbank.domain.product.entity.Product;
import com.shy.sbank.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupAccountService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final MemberGroupAccountRepository memberGroupAccountRepository;

    // 그룹계좌 생성 및 가입
    public boolean register(GroupAccountRegisterRequestDto dto) {
        // Find Member
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 사용자입니다."));

        // Find Product
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalStateException("해당 상품 정보가 존재하지 않습니다."));

        // 그룹계좌 생성
        GroupAccount groupAccount = GroupAccount.builder()
                .groupAccountName(dto.getGroupAccountName())
                .masterId(dto.getMemberId())
                .productId(dto.getProductId())
                .build();

        GroupAccount savedGroupAccount = groupAccountRepository.save(groupAccount);

        MemberGroupAccount memberGroupAccount = MemberGroupAccount.builder()
                .groupAccountId(savedGroupAccount.getId().toString())
                .memberList(new ArrayList<>())
                .product(product)
                .build();

        List<Member> memberList = memberGroupAccount.getMemberList();
        memberList.add(member);

        memberGroupAccount.addGroupAccountMember(memberList);

        memberGroupAccountRepository.save(memberGroupAccount);
        return true;
    }

}
