package com.sparta.byblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String id;
    @NotBlank
    private String pwd;

//    private boolean admin = false; //User_기본설정
    private String role = "";
    //값을 안 주면 유저, ADMIN값을 주면 관리자

}