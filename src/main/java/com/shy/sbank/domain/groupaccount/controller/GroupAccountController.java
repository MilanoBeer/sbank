package com.shy.sbank.domain.groupaccount.controller;


import com.shy.sbank.domain.groupaccount.dto.GroupAccountRegisterRequestDto;
import com.shy.sbank.domain.groupaccount.service.GroupAccountService;
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
@RequestMapping("/groupaccount")
@RequiredArgsConstructor
@Slf4j
public class GroupAccountController {

    private final GroupAccountService groupAccountService;

    @GetMapping("/mongo")
    public String mongoTest(){
        return "ok";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated GroupAccountRegisterRequestDto dto) {
        try {
            log.info("controller : dto = {}, {}, {}", dto.getMemberId(), dto.getProductId(), dto.getGroupAccountName());
            return new ResponseEntity<>(groupAccountService.register(dto), HttpStatus.OK);
        } catch( IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
