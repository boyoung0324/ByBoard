package com.sparta.byblog.service;

import com.sparta.byblog.dto.LoginRequestDto;
import com.sparta.byblog.dto.SignupRequestDto;
import com.sparta.byblog.entity.User;
import com.sparta.byblog.entity.UserRoleEnum;
import com.sparta.byblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    private final String ADMIN_TOKEN = "ADMIN";


    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String id = requestDto.getId();
        String pwd = passwordEncoder.encode(requestDto.getPwd());

        //아이디 중복
        Optional<User> checkUsername = repository.findById(id);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }


        UserRoleEnum role = UserRoleEnum.USER;


        if (ADMIN_TOKEN.equals(requestDto.getRole())) {
            role = UserRoleEnum.ADMIN;
        }


        User user = new User(username, id, pwd, role);
        repository.save(user);


    }


    public void login(LoginRequestDto requestDto) {
        String id = requestDto.getId();
        String pwd = requestDto.getPwd();

        User user = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if (!passwordEncoder.matches(pwd, user.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }
}
