package com.shy.sbank.member.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor  // (access = AccessLevel.PROTECTED)
public class MemberRegisterRequestDto {

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "이메일을 작성해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 작성해주세요")
    @Size(min = 8, max = 12, message = "비밀번호 형식에 맞지 않습니다.")
    private String password;

    @Builder
    public MemberRegisterRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
