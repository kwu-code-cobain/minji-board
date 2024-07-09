package com.cake.board1.controller;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;
import com.cake.board1.service.BoardService;
import com.cake.board1.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

/*
 controller는 사용자(클라이언트)로부터 HTTP 요청을 받아
 적절한 서비스 레이어의 메소드를 호출하여 해당 요청 처리후
 결과를 사용자에게 반환한다.

 주요기능
 1. 게시글 생성
 2. 게시글 조회
 3. 게시글 수정
 4. 게시글 삭제
*/

import java.sql.*;
import java.util.List;

@RestController // HTTP 요청 처리
@RequestMapping("/api") //api 경로로 들어오는 요청을 처리하도록 설정
public class BoardController {
    private final BoardService boardService;
    // 게시판 서비스를 사용하기 위해 선언
    // private final을 사용하는건 생성자 기반 의존성 주입 방법이다.

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    // 위 두줄을 생략하고 @AllArgsConstructor 사용 가능
    }

    // 1. 게시글 생성
    @PostMapping("/boards") // HTTP POST 요청을 /boards 경로로 받음
    public BoardRequestDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }
    // 요청 본문에 담긴 JSON 데이터를 BoardRequestDto 객체로 변환하여 서비스에 전달

    // 2. 게시글 목록 전체조회
    @GetMapping("/boards") // HTTP GET 요청을 /boards 경로로 받음
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    // 3. 게시글 수정하기 - 비밀번호 검증
    @PutMapping("/boards/{id}") // HTTP PUT 요청을 /boards/{id} 경로롤 받음
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
        // 경로 변수로 받은 id와 , 요청 본문에 담긴 수정 정보를 서비스에 전달

    // 4. 게시글 삭제하기 - 비밀번호 검증
    @DeleteMapping("/boards/{id}") // HTTP DELETE 요청을 /boards/{id} 경로로 받음
    public String deleteMemo(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteMemo(id,requestDto);
        }
        // 경로 변수로 받은 id와, 요청 본문에 담긴 삭제 정보를 서비스에 전달
    }
}
