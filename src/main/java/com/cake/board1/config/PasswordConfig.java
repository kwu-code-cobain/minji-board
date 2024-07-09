package com.cake.board1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 이 config 클래스를 통해 어플리케이션에서는 언제든지
// PasswordEncoder 빈을 주입받아서, 비밀번호 암호화 및 검증 수행 가능하다

@Configuration // Bean 수동등록,싱글톤 보장, 의존성 주입을 위해 사용
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        /*
         PasswordEncoder 타입의 빈을 생성하고, 스프링 컨테이너에 등록
         PasswordEncoder는 스프링 시큐리티에서 비밀번호를 암호화하는데 사용되는 인터페이스
         BCryptPasswordEncoder: 인터페이스의 구현체로서, 비밀번호를 암호화하고 복호화 함
         BCrypt 알고리즘은 비밀번호 해싱을 위해 설계된 함수이다.

         */

    }
}