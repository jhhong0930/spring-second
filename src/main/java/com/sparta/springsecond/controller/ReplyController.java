package com.sparta.springsecond.controller;

import com.sparta.springsecond.domain.Reply;
import com.sparta.springsecond.dto.ReplyRequestDto;
import com.sparta.springsecond.security.UserDetailsImpl;
import com.sparta.springsecond.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    // λκΈ μμ±
    @PostMapping("/api/replies")
    public Reply register(@RequestBody ReplyRequestDto requestDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUsername();

        return replyService.register(requestDto, username);
    }

}
