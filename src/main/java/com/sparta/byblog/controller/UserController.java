package com.sparta.byblog.controller;

import com.sparta.byblog.dto.LoginRequestDto;
import com.sparta.byblog.dto.SignupRequestDto;
import com.sparta.byblog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    //회원가입,
    private final UserService service;


    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult result) {

        List<FieldError> error = result.getFieldErrors();

        service.signup(requestDto);

        if(error.size() > 0)
            return new ResponseEntity("회원가입이 실패했습니다.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity("회원가입이 완료되었습니다.", HttpStatus.OK);
    }

    //로그인








}
