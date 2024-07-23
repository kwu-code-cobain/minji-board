package com.cake.board1.service;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;
import com.cake.board1.domain.Board;
import com.cake.board1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
  데이터베이스와의 상호작용을 담당하고 주로 비즈니스 로직을 처리한다
 */

@Transactional
@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;

    // 게시글 생성
    @Override
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        String password= passwordEncoder.encode(requestDto.getPassword());

        Board board = new Board(requestDto);
        board.setPassword(password);  // 암호화된 비밀번호 저장
        Board saveBoard = boardRepository.save(board); // 데이터베이스에 저장

        return new BoardResponseDto(saveBoard);  // 저장된 엔티티를 ResponseDto로 변환해서 반환
    }


    // 게시글 조회 - 전체
    @Override
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc()
                // 데이터베이스에 모든 게시글을 수정시간 기준 내림차순을 조회하여
                // ResponseDto 리스트로 변환해서 반환
                .stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    // 게시글 조회 - 특정 id
    @Override
    public BoardResponseDto getBoardById(Long id) {
        Board board = findBoard(id);
        return new BoardResponseDto(board);

    }

    // 게시글 조회 - 특정 이름
    @Override
    public List<BoardResponseDto> getBoardByName(String name) {
        return boardRepository.findByName(name)
                .stream()
                .map(BoardResponseDto::new)
                .toList();

    }

    // 게시글 조회 - 특정 제목
    @Override
    public List<BoardResponseDto> getBoardByTitle(String title) {
        return boardRepository.findByTitle(title)
                .stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    // 게시글 수정
    @Override
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        String password = requestDto.getPassword();
        Board board = findBoard(id);

        if(!passwordEncoder.matches(password, board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        board.update(requestDto); // 게시글 업데이트
        return id;
    }


    // 게시글 삭제
    @Override
    public String deleteBoard(Long id, BoardRequestDto requestDto) {
        String password = requestDto.getPassword();
        Board board = findBoard(id);

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
