package com.cake.board1.dto;

import com.cake.board1.domain.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/*
  게시글 조회시, 클라이언트에게 게시글의 응답 데이터를 전송하기위한 dto를 정의한 것이다.
  dto는 애플리케이션의 여러 레이어 간에 데이터를 전송하기 위해 사용되는 객체이다.
  특히 서버에서 클라이언트로 데이터를 전달할 때 사용된다.
 */

@Getter
public class BoardResponseDto {
    private Long id;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    //Board 엔티티 객체를 받아서, 해당 엔티티의 데이터를 'BoardResponseDto' 객체에 설정
    public BoardResponseDto(Board board) {
        this.id= board.getId();
        this.name= board.getName();
        this.title= board.getTitle();
        this.contents= board.getContents();
        this.createAt= board.getCreatedAt();
        this.modifiedAt= board.getModifiedAt();
    }

    public BoardResponseDto(List<Board> boards) {
        this.id = boards.get(0).getId();
        this.title = boards.get(0).getTitle();
        this.name = boards.get(0).getName();
        this.contents = boards.get(0).getContents();
        this.createAt = boards.get(0).getCreatedAt();
        this.modifiedAt = boards.get(0).getModifiedAt();
    }
}
