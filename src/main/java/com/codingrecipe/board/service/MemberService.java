package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MemberDTO;
import com.codingrecipe.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public int mbsave(MemberDTO memberDTO){
        return MemberRepository.mbsave(memberDTO);
    }
    public boolean login(MemberDTO memberDTO){
        MemberDTO loginMember = memberRepository.login(memberDTO);
        if (loginMember != null)
            return true;
        else
            return false;
    }

}
