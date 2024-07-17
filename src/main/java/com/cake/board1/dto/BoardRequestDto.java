package com.cake.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* 이 클래스는 게시글 생성 또는 수정시 클라이언트로부터 데이터를 전달받기 위한
   데이터 전송 객체(DTO)이다.
   이 데이터를 서비스 레이어나 컨트롤러로 전달해서 비즈니스 로직을 처리하게 된다
 */
@AllArgsConstructor // 생성자
@Getter // Getter 어노테이션 사용시 클래스의 모든 필드에 대한 getter 메소드 자동 생성됨
public class BoardRequestDto {
    private String username;  // 입력한 이름
    private String title;  // 입력한 제목
    private String contents; // 작성 내용
    private String password; // 입력한 비밀번호
}

//