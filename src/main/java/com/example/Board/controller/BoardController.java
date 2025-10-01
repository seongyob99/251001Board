package com.example.Board.controller;

import com.example.Board.domain.Board;
import com.example.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    // Create
    @PostMapping
    public Board create(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    // Read All
    @GetMapping
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    // Read One
    @GetMapping("{/id}")
    public Board getOne(@PathVariable Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // Update
    @PutMapping
    public Board update(@PathVariable Long id, @RequestBody Board newBoard) {
       Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setTitle(newBoard.getTitle());
            board.setContent(newBoard.getContent());
            return boardRepository.save(board);
        }
        return null;
    }

    // Delete
    @DeleteMapping
    public void delete(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}
