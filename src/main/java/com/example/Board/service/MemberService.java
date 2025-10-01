package com.example.Board.service;

import com.example.Board.domain.Member;
import com.example.Board.dto.MemberDTO;
import com.example.Board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 회원가입
    public String signup(MemberDTO memberDTO) {
        Optional<Member> existing = memberRepository.findByUsername(memberDTO.getUsername());
        if (existing.isPresent()) {
            return "이미 존재하는 회원입니다";
        }
        Member member = new Member();
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());
        memberRepository.save(member);
        return "회원가입이 완료되었습니다";
    }

    // 로그인
    public String login(MemberDTO memberDTO) {
        Optional<Member> found = memberRepository.findByUsername(memberDTO.getUsername());
        if (found.isPresent()) {
            if (found.get().getPassword().equals(memberDTO.getPassword())) {
                return "로그인 성공";
            } else {
                return "비밀번호가 틀렸습니다";
            }
        }
        return "아이디가 틀렸습니다";
    }
}