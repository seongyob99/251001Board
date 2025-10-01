package com.example.Board.controller;

import com.example.Board.dto.BoardDTO;
import com.example.Board.dto.MemberDTO;
import com.example.Board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String getAll(
            @RequestParam(required = false) Long lastId, // 커서
            @RequestParam(defaultValue = "10") int size,  // 한 번에 가져올 글 수
            HttpSession session,
            Model model) {

        // 로그인 유저
        Object loginUser = session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        // 게시글 조회
        List<BoardDTO> list = boardService.getBoards(lastId, 5); // 5개씩
        model.addAttribute("boards", list);

        // 다음 페이지를 위한 lastId
        Long newLastId = list.isEmpty() ? null : list.get(list.size() - 1).getId();
        model.addAttribute("lastId", newLastId);

        return "boardList";
    }

    // 게시글 작성 폼
    @GetMapping("/create")
    public String createForm() {
        return "boardCreate";
    }

    // 게시글 작성 처리
    @PostMapping("/create")
    public String create(BoardDTO boardDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if(loginUser != null) {
            boardDTO.setWriter(loginUser.getUsername()); // 세션에서 로그인한 유저 이름을 writer로 설정
        }
        boardService.create(boardDTO);
        redirectAttributes.addFlashAttribute("message", "게시글이 작성되었습니다!");
        return "redirect:/boards";
    }


    // 게시글 상세
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, HttpSession session, Model model) {
        BoardDTO board = boardService.getOne(id);
        model.addAttribute("board", board);
        Object loginUser = session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        return "boardDetail";
    }

    // 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BoardDTO board = boardService.getOne(id);
        model.addAttribute("board", board);
        return "boardEdit";
    }

    // 게시글 수정 처리
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, BoardDTO boardDTO) {
        boardService.update(id, boardDTO);
        return "redirect:/boards/" + id;
    }

    // 게시글 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }
}
