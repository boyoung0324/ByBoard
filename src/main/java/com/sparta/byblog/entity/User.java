package com.sparta.byblog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    public User(String username,String id, String pwd , UserRoleEnum role) {
        this.id = id;
        this.pwd = pwd;
        this.username = username;
        this.role = role;
    }

}
