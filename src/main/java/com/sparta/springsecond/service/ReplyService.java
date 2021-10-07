package com.sparta.springsecond.service;

import com.sparta.springsecond.domain.Board;
import com.sparta.springsecond.domain.Reply;
import com.sparta.springsecond.dto.ReplyRequestDto;
import com.sparta.springsecond.repository.BoardRepository;
import com.sparta.springsecond.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    // 댓글 전체 조회
    public List<Reply> getList(Long bno) {
        return replyRepository.findAllByBno(bno, Sort.by("regDate").descending());
    }

    // 댓글 작성
    @Transactional
    public void register(ReplyRequestDto requestDto, String username, Long bno) {

        Reply reply = new Reply(requestDto, username, bno);

        Board board = boardRepository.findById(bno)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을수 없습니다."));

        if (board != null) {
            replyRepository.save(reply);
            board.addReply(reply);
            boardRepository.save(board);
        }



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
    @Transactional
    public void delete(Long rno) {

        Reply reply = replyRepository.findById(rno)
                .orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));

        Long bno = reply.getBno();

        Board board = boardRepository.findById(bno)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을수 없습니다."));

        List<Reply> replies = board.getReplies();

        replies.remove(reply);

        replyRepository.deleteById(rno);
    }

}
