package com.sparta.byblog.dto;

import com.sparta.byblog.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @NotBlank
    private String id;
    @NotBlank
    private String pwd;
    @NotBlank
    private UserRoleEnum role;
}
