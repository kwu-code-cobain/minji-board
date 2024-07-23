package com.cake.board1.repository;

import com.cake.board1.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    /* findAll: 모든 엔티티를 조회한다
       By: 조회 조건을 지정한다. 수정 시간을 기준으로 조회함
       OrderBy: 결과를 정렬한다 수정 시간을 기준으로 내림차순으로
       ModifiedAt: 수정 시간에 대한 조건
       Desc: 내림차순으로 정렬
     */

    Optional<Board> findById(Long id); //
    Optional<List<Board>> findByName(String name);
    Optional<List<Board>> findByTitle(String title);
}
