package com.example.Board.controller;


import com.example.Board.domain.Member;
import com.example.Board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    // Signup
    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        Optional<Member> existing = memberRepository.findByUsername(member.getUsername());
        if (existing.isPresent()) {
            return ("이미 존재하는 회원입니다");
        }
        memberRepository.save(member);
        return ("회원가입이 완료됬습니다");
    }


    // Login
    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        Optional<Member> found = memberRepository.findByUsername(member.getUsername());
        if (found.isPresent()) {
            if (found.get().getPassword().equals(member.getPassword())) {
                return "로그인 성공";
            } else {
                return "비밀번호가 틀렸습니다";
            }
        }
        return "아이디가 틀렸습니다";
    }
}