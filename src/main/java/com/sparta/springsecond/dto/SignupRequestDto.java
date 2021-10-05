package com.sparta.springsecond.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{3,}",
            message = "이름은 3자 이상이어야 합니다."
    )
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다")
    @Pattern(
            regexp = "^(?=.{4,}$).*",
            message = "비밀번호는 4자리 이상이어야 합니다"
    )
    private String password;

    private String password2;


    @NotBlank(message = "이메일은 필수 입력 값입니다")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    private boolean admin = false;
    private String adminToken = "";
}