package com.snow.board.controller;

import com.snow.board.dto.BoardDTO;
import com.snow.board.dto.MemberDTO;
import com.snow.board.dto.CommentDTO;
import com.snow.board.service.BoardService;
import com.snow.board.service.MemberService;
import com.snow.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping("/board/save")
    public String saveForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO showMemberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",showMemberDTO);
        return "save";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "redirect:/board/paging";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable, HttpSession session) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());

        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO showMemberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",showMemberDTO);
        return "detail";
    }

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);

        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO showMemberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",showMemberDTO);
        return "update";
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model, @PageableDefault(page=1) Pageable pageable, HttpSession session) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        model.addAttribute("page",pageable.getPageNumber());

        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO showMemberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",showMemberDTO);
        return "detail";
    }

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/paging";
    }

    @GetMapping("/board/main")
    public String maining(){
        return "main";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute("member") MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            String myEmail = (String) session.getAttribute("loginEmail");
            MemberDTO showMemberDTO = memberService.updateForm(myEmail);
            model.addAttribute("member",showMemberDTO);
            return "main";
        } else {
            return "login";
        }
    }

    @GetMapping("/board/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model,HttpSession session) {

        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO showMemberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",showMemberDTO);
        return "paging";
    }

    @GetMapping("/member/register")
    public String registerForm() {
        return "register";
    }
    @GetMapping("/member/mainLogin")
    public String showDetail(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "mainLogin";
    }

    @PostMapping("/member/register")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }
    @GetMapping("/member/toHome")
    public String toHome(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("member",memberDTO);
        if ( myEmail!=null ) {
            return "mainLogin";
        } else {
            return "index";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberDetail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "updateMember";
    }
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }
    @GetMapping("/member/find")
    public String myInfo(HttpSession session){
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }
}
