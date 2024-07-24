package com.cake.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardRequestDto {
    private String name;
    private String title;
    private String contents;
    private String password;

}

