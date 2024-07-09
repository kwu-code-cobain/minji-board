package com.cake.board1.dto;

import com.cake.board1.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
  게시글 조회시, 클라이언트에게 게시글의 응답 데이터를 전송하기위한 dto를 정의한 것이다.
  dto는 애플리케이션의 여러 레이어 간에 데이터를 전송하기 위해 사용되는 객체이다.
  특히 서버에서 클라이언트로 데이터를 전달할 때 사용된다.
 */

@Getter
public class BoardResponseDto {
    private String username; // 클라이언트에게 반환할 사용자 이름을 저장한다.
    private String title;
    private String contents;
    private LocalDateTime createAt;

    //Board 엔티티 객체를 받아서, 해당 엔티티의 데이터를 'BoardResponseDto' 객체에 설정
    public BoardResponseDto(Board board) {
        this.username= board.getUsername();
        this.title= board.getTitle();
        this.contents= board.getContents();
        this.createAt= board.getCreateAt();
    }
}
