package com.cake.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor // 생성자
@Getter // Getter 어노테이션 사용시 클래스의 모든 필드에 대한 getter 메소드 자동 생성됨
public class BoardRequestDto {
    private String name;  // 입력한 이름
    private String title;  // 입력한 제목
    private String contents; // 작성 내용
    private String password; // 입력한 비밀번호

}

