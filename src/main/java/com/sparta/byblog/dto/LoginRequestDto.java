package com.sparta.byblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String id;
    private String pwd;
}
