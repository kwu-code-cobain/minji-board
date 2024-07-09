package com.cake.board1.service;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;
import com.cake.board1.entity.Board;
import com.cake.board1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/*
  데이터베이스와의 상호작용을 담당하고 주로 비즈니스 로직을 처리한다
 */

@Service  // 서비스 클래스임을 선언
@RequiredArgsConstructor  // 필수 생성자 주입 자동으로 처리
public class BoardService {
    // 의존성 주입
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;

    // 1. 게시글 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        String password= passwordEncoder.encode(requestDto.getPassword());
        // 비밀번호를 암호화하여 저장함

        // RequestDto -> Entity 변환
        Board board = new Board(requestDto);
        board.setPassword(password);  // 암호화된 비밀번호 저장
        Board saveBoard = boardRepository.save(board); // 데이터베이스에 저장

        return new BoardResponseDto(saveBoard);  // 저장된 엔티티를 ResponseDto로 변환해서 반환
    }


    // 2. 전체 게시글 목록 조회
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc()
                // 데이터베이스에 모든 게시글을 수정시간 기준 내림차순을 조회하여
                // ResponseDto 리스트로 변환해서 반환
                .stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    // 3. 게시글 선택 조회
    public BoardResponseDto getBoardById(Long id) {
        // 주어진 id로 게시글 찾아서 ResponseDto로 변환
        Board board = findBoard(id);
        return new BoardResponseDto(board);

    }

    // 4. 게시글 수정
    /*
        트랜잭션이란 데이터베이스 관리시스템의 상호작용 단위
        Atomicity: 완전히 성공하거나 완전히 실패하는 단일 단위
        Consistency: 트랜잭션이 성공적으로 완료되면 언제나 동일한 데이터베이스 상태유지
        Isolation: 트랜잭션 수행시 다른 트랜잭션의 작업이 끼어들지 못함
        Durability: 성공적으로 수행된 트랜잭션은 영원히 반영되어야함
     */
    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        String password = requestDto.getPassword(); // 요청에서 받은 비밀번호
        Board board = findBoard(id);  // 주어진 id로 게시글 조회

        // 비밀번호 검증
        if(!passwordEncoder.matches(password, board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        board.update(requestDto); // 게시글 업데이트
        return id;
    }

    // 5. 게시글 삭제
    public String deleteMemo(Long id, BoardRequestDto requestDto) {
        String password = requestDto.getPassword(); // 요청에서 받은 비밀번호
        Board board = findBoard(id);  // 주어진 id로 게시글 조회

        // 비밀번호 검증
        if(!passwordEncoder.matches(password, board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        boardRepository.delete(board);
        return "게시글 삭제 완료";
    }



    // + 게시판 찾기 메서드
    private Board findBoard(Long id) {
        // 주어진 id로 게시글 찾아서 반환, 없으면 예외 발생
        return boardRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
