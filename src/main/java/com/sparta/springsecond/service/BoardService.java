package com.sparta.springsecond.service;

import com.sparta.springsecond.domain.Board;
import com.sparta.springsecond.dto.BoardRequestDto;
import com.sparta.springsecond.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    // 게시글 작성
    public Long register(BoardRequestDto requestDto, Long userId) {

        Board board = new Board(requestDto, userId);

        boardRepository.save(board);

        return board.getBno();
    }

    // 게시글 조회
    public Board read(Long bno) {

        return boardRepository.findById(bno)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
    }

    // 게시글 수정
    public Board update(Long bno, BoardRequestDto requestDto) {

        Board board = boardRepository.findById(bno)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));

        board.changeTitle(requestDto.getTitle());
        board.changeContent(requestDto.getContent());

        boardRepository.save(board);

        return board;
    }

    // 게시글 삭제
    public void delete(Long bno) {
        boardRepository.deleteById(bno);
    }

}
