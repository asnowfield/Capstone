package com.snow.board.service;

import com.snow.board.dto.MemberDTO;
import com.snow.board.entity.MemberEntity;
import com.snow.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO, passwordEncoder);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(passwordEncoder.encode(memberDTO.getMemberPassword()))) {
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {return null;} //Failed to log in
        } else {return null;} //Not registered
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {return null;}
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {return null;}
    }

    public void update(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO, passwordEncoder));
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {return null;// 조회결과가 있다 -> 사용할 수 없다.
        } else {return "ok";}// 조회결과가 없다 -> 사용할 수 있다.
    }
}













