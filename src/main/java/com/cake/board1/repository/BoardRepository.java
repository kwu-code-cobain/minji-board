package com.cake.board1.repository;

import com.cake.board1.dto.BoardRequestDto;
import com.cake.board1.dto.BoardResponseDto;
import com.cake.board1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

// <관리할 엔티티 타입, 엔티티의 식별자(Primary Key) 타입>
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    /* findAll: 모든 엔티티를 조회한다
       By: 조회 조건을 지정한다. 수정 시간을 기준으로 조회함
       OrderBy: 결과를 정렬한다 수정 시간을 기준으로 내림차순으로
       ModifiedAt: 수정 시간에 대한 조건
       Desc: 내림차순으로 정렬
     */
}
