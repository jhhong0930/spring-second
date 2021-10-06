package com.sparta.springsecond.controller;

import com.sparta.springsecond.domain.Board;
import com.sparta.springsecond.dto.BoardRequestDto;
import com.sparta.springsecond.security.UserDetailsImpl;
import com.sparta.springsecond.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성 페이지
    @GetMapping("/boards/new")
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

}
