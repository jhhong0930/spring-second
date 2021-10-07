package com.sparta.springsecond.service;

import com.sparta.springsecond.domain.Reply;
import com.sparta.springsecond.dto.ReplyRequestDto;
import com.sparta.springsecond.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    // 댓글 전체 조회
    public List<Reply> getList(Long bno) {
        return replyRepository.findAllByBno(bno, Sort.by("regDate").descending());
    }

    // 댓글 작성
    public void register(ReplyRequestDto requestDto, String username, Long bno) {

        Reply reply = new Reply(requestDto, username, bno);

        replyRepository.save(reply);
    }

    // 댓글 수정
    public Reply update(Long rno, ReplyRequestDto requestDto) {

        Reply reply = replyRepository.findById(rno)
                .orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));

        reply.changeContent(requestDto.getContent());

        replyRepository.save(reply);

        return reply;
    }

    // 댓글 삭제
    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }

}
