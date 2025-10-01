package com.example.Board.controller;

import com.example.Board.dto.MemberDTO;
import com.example.Board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String signup(MemberDTO memberDTO, Model model) {
        String msg = memberService.signup(memberDTO);
        model.addAttribute("message", msg);
        return "signupResult";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Login 처리
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, Model model) {
        String msg = memberService.login(memberDTO);
        model.addAttribute("message", msg);
        return "loginResult";
    }
}
