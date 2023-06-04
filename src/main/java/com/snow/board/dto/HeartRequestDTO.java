package com.snow.board.dto;

import com.snow.board.controller.HeartController;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDTO {

    private Long memberId;
    private Long boardID;

    public HeartRequestDTO(Long memberId, Long boardID){
        this.memberId=memberID;
        this.boardID = boardID;

    }
}

