package com.cake.board1.controller;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;
import com.cake.board1.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*

 주요기능
 1. 게시글 생성
 2. 게시글 조회
 3. 게시글 수정
 4. 게시글 삭제
*/

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceImpl boardService;

    // 게시글 생성
    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto) {
        BoardResponseDto responseDto = boardService.createBoard(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 게시글 조회 - 전체
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> responseDtoList = boardService.getAllBoards();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // 게시글 조회 - 특정 id
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
        BoardResponseDto responseDto = boardService.getBoardById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 조회 - 특정 이름
    @GetMapping(value ="/boards", params = "name")
    public ResponseEntity<List<BoardResponseDto>> getBoardByName(@PathVariable String name) {
        List<BoardResponseDto> responseDto = boardService.getBoardByName(name);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 조회 - 특정 제목
    @GetMapping(value ="/boards", params = "title")
    public ResponseEntity<List<BoardResponseDto>> getBoardByTitle(@PathVariable String title) {
        List<BoardResponseDto> responseDto = boardService.getBoardByName(title);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<Long> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        Long updateId = boardService.updateBoard(id, requestDto);
        return new ResponseEntity<>(updateId, HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        String result = boardService.deleteBoard(id, requestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }



}




