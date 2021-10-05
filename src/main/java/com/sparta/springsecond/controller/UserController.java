package com.sparta.springsecond.controller;

import com.sparta.springsecond.dto.SignupRequestDto;
import com.sparta.springsecond.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("requestDto", requestDto);

            Map<String, String> validatorResult = userService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "signup";
        }

        if (!requestDto.getPassword().equals(requestDto.getPassword2())) {
            model.addAttribute("password_error", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        try {
            userService.registerUser(requestDto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }


        return "redirect:/user/login";
    }

}
