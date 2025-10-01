package com.example.Board.service;

import com.example.Board.domain.Board;
import com.example.Board.dto.BoardDTO;
import com.example.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 생성
    public BoardDTO create(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        Board saved = boardRepository.save(board);
        return new BoardDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getWriter());
    }

    // 게시글 전체 조회
    public List<BoardDTO> getAll() {
        return boardRepository.findAll().stream()
                .map(b -> new BoardDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getContent(),
                        b.getWriter()))
                .collect(Collectors.toList());
    }

    // 게시글 단일 조회
    public BoardDTO getOne(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.map(b -> new BoardDTO(
                b.getId(),
                b.getTitle(),
                b.getContent(),
                b.getWriter())).orElse(null);
    }

    // 게시글 수정
    public BoardDTO update(Long id, BoardDTO boardDTO) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            Board b = board.get();
            b.setTitle(boardDTO.getTitle());
            b.setContent(boardDTO.getContent());
            Board updated = boardRepository.save(b);
            return new BoardDTO(
                    updated.getId(),
                    updated.getTitle(),
                    updated.getContent(),
                    updated.getWriter());
        }
        return null;
    }

    // 게시글 삭제
    public boolean delete(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 커서기반 페이징
    public List<BoardDTO> getBoards(Long lastId, int size) {
        List<Board> boards;
        Pageable pageable = PageRequest.of(0, size);

        if (lastId == null) {
            boards = boardRepository.findAllByOrderByIdDesc(pageable);
        } else {
            boards = boardRepository.findByIdLessThanOrderByIdDesc(lastId, pageable);
        }

        return boards.stream()
                .map(b -> new BoardDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getContent(),
                        b.getWriter()))
                .collect(Collectors.toList());
    }
}