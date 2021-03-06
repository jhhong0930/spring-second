package com.sparta.springsecond.controller;

import com.sparta.springsecond.domain.Board;
import com.sparta.springsecond.dto.BoardRequestDto;
import com.sparta.springsecond.security.UserDetailsImpl;
import com.sparta.springsecond.service.BoardService;
import com.sparta.springsecond.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    // 게시글 전체 조회(메인페이지)
    @GetMapping("/")
    public String getList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        model.addAttribute("list", boardService.getList());

        return "mainPage";
    }

    // 게시글 작성 페이지
    @GetMapping("/new")
    public String registerForm(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "board/register";
    }

    // 게시글 작성 기능
    @PostMapping("/boards/new")
    public String register(@ModelAttribute BoardRequestDto requestDto, String username) {

        boardService.register(requestDto, username);

        return "redirect:/";
    }

    // 게시글 상세보기 페이지
    @GetMapping("/boards/{bno}")
    public String read(@PathVariable Long bno, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        model.addAttribute("board", boardService.read(bno));
        model.addAttribute("reply", replyService.getList(bno));

        return "board/read";
    }

    // 게시글 수정 페이지
    @GetMapping("/boards/{bno}/edit")
    public String updateForm(@PathVariable Long bno, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        model.addAttribute("board", boardService.read(bno));

        return "board/edit";
    }

    // 게시글 수정 기능
    @PostMapping("/boards/{bno}/edit")
    public String update(@PathVariable Long bno, BoardRequestDto requestDto) {

        boardService.update(bno, requestDto);

        return "redirect:/boards/" + bno;
    }

    // 게시글 삭제 기능
    @PostMapping("/boards/{bno}/delete")
    public String delete(@PathVariable Long bno) {

        boardService.delete(bno);

        return "redirect:/";
    }
}
