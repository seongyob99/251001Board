package com.example.Board.controller;

import com.example.Board.dto.MemberDTO;
import com.example.Board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // Signup 처리
    @PostMapping("/signup")
    public String signup(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        String msg = memberService.signup(memberDTO);
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/members/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Login 처리
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        String msg = memberService.login(memberDTO);
        if(msg.equals("로그인 성공")) {
            session.setAttribute("loginUser", memberDTO); // 로그인 성공 시 세션에 저장
            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            return "redirect:/boards"; // 로그인 후 게시판으로 이동
        } else {
            redirectAttributes.addFlashAttribute("message", msg);
            return "redirect:/members/login";
        }
    }
}
