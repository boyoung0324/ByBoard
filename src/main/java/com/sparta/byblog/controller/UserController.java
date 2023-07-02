package com.sparta.byblog.controller;

import com.sparta.byblog.dto.ApiResponseDto;
import com.sparta.byblog.dto.LoginRequestDto;
import com.sparta.byblog.dto.SignupRequestDto;
import com.sparta.byblog.jwt.JwtUtil;
import com.sparta.byblog.security.UserDetailsImpl;
import com.sparta.byblog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    //회원가입,
    private final UserService service;
    private final JwtUtil jwtUtil;


    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {

        try{
            service.signup(requestDto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto("중복된 id 입니다.",HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));

    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        try {
            service.login(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getId(), requestDto.getRole()));
        //login()에서 에러가 없으면 실행

        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
    }



}
