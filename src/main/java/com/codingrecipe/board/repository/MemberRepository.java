package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.MemberDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {

    public static int mbsave(MemberDTO memberDTO) {
        System.out.print("memberDTO+ " + memberDTO);
        return 0;
    }

    /*
    public MemberDTO login(MemberDTO memberDTO){

        return sql.selectOne(statement: "Member.login", memberDTO);
    }
    */

}