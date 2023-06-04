package com.snow.board.service;

import com.snow.board.controller.HeartController;
import com.snow.board.dto.HeartRequestDTO;
import com.snow.board.repository.BoardRepository;
import com.snow.board.repository.HeartRepository;
import com.snow.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final HeartRepository heartRepository;

    public void insert(HeartRequestDTO heartRequestDTO) throws Exception{
        Member member = memberRepository.findById(heartRequestDTO.getMemberId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));)

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));

        Heart heart = Heart.builder()
              .board(board)
                .member(member)
                .build();

        heartRepository.save(heart);
    }
}
