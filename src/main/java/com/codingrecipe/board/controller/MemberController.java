package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.MemberDTO;
import com.codingrecipe.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.metamodel.StaticMetamodel;

import static jdk.internal.org.jline.utils.Colors.s;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member/save")
    public String mbsaveForm(){
        return "mbsave";
    }
    @PostMapping("/member/save")
    public String mbsave(@ModelAttribute MemberDTO memberDTO){
        int saveResult = memberService.mbsave(memberDTO);
        if(saveResult > 0)
            return "login"; // 가입이 성공한 경우
        else
            return "save"; // 가입이 실패한 경우
    }

    @GetMapping("/login")
    public String login(@StaticMetamodel(MemberDTO memberDTO, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult) {
            session.setAttribute(s:"loginEmail", memberDTO.getMemberEmail());
            return "main"; // 로그인 성공시
        }
        else
            return "login";
        }
    }
}
