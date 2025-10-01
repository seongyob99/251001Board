package com.example.Board.repository;


import com.example.Board.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // lastId보다 작은 게시글을 내림차순으로 5개 조회
    List<Board> findByIdLessThanOrderByIdDesc(Long lastId, Pageable pageable);

    // 첫 페이지: 가장 최신 게시글 5개
    List<Board> findAllByOrderByIdDesc(Pageable pageable);
}
