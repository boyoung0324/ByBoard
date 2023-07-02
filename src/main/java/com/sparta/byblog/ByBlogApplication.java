package com.sparta.byblog;

import com.sparta.byblog.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ByBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByBlogApplication.class, args);
    }

}
