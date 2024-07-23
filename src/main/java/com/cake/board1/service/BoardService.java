package com.cake.board1.service;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto requestDto);
    List<BoardResponseDto> getAllBoards();
    BoardResponseDto getBoardById(Long id);
    List<BoardResponseDto> getBoardByName(String name);
    List<BoardResponseDto> getBoardByTitle(String title);
    Long updateBoard(Long id, BoardRequestDto requestDto);
    String deleteBoard(Long id, BoardRequestDto requestDto);

}
