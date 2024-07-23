package com.cake.board1.domain;

import com.cake.board1.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
  JPA를 사용하여 데이터베이스의 board 테이블과 매핑되는 Board 엔티티를 정의하고 있다
  dto를 사용해서 데이터를 주고 받는다

  JPA 엔티티란 데이터베이스 테이블과 매핑되는 자바 객체이다.

 */

@Entity  // JPA 엔티티임을 나타냄
@Getter @Setter  // 모든 필드에 대한 getter setter 메소드들을 자동으로 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
@Table(name="board") // 데이터베이스의 board 테이블과 매핑됨을 나타냄

public class Board extends Timestamped {
    @Id  // 이 필드가 엔티티의 기본 키임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 값을 자동으로 생성하도록 설정
    private Long id;

    @Column(name="name",nullable = false)  // 이 필드가 데이터베이스의 not null 컬럼임을 나타냄
    private String name;

    @Column(name= "title",nullable = false)
    private String title;

    @Column(name= "contents",nullable = false)
    private String contents;

    @Column(name= "password",nullable = false)
    private String password;


    // dto 객체를 받아서 엔티티를 생성하는 생성자
    public Board(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getTitle();
        this.password = requestDto.getPassword();

    }


    // dto 객체를 받아서 엔티티의 필드를 업데이트하는 메서드
    public void update(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}

