package com.shy.sbank.account.controller;

import com.shy.sbank.account.dto.AccountRegisterRequestDto;
import com.shy.sbank.account.dto.DepositAccountRequestDto;
import com.shy.sbank.account.dto.GetAccountListRequestDto;
import com.shy.sbank.account.dto.RemitAccountRequestDto;
import com.shy.sbank.account.repository.AccountRepository;
import com.shy.sbank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated AccountRegisterRequestDto dto) {
        // service layer에 요청하기
        try {
            return new ResponseEntity<>(accountService.register(dto), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccountList(@RequestBody GetAccountListRequestDto dto) {
        try {
            return new ResponseEntity<>(accountService.getAccountList(dto), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 본인 계좌입금
    @PostMapping("/deposit")
    public ResponseEntity<?> depositAccount(@RequestBody DepositAccountRequestDto dto) {
        try {
            return new ResponseEntity<>(accountService.depositAccount(dto), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 타인계좌 송금
    @PostMapping("/remit")
    public ResponseEntity<?> remitMoney(@RequestBody RemitAccountRequestDto dto) {
        try {
            return new ResponseEntity<>(accountService.remitMoney(dto), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
