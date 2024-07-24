package com.cake.board1.dto;

import com.cake.board1.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


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

    public BoardResponseDto(List<Board> board) {
        this.id = board.get(0).getId();
        this.title = board.get(0).getTitle();
        this.name = board.get(0).getName();
        this.contents = board.get(0).getContents();
        this.createAt = board.get(0).getCreatedAt();
        this.modifiedAt = board.get(0).getModifiedAt();
    }


}
