package com.sparta.springsecond.domain;

import com.sparta.springsecond.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private Long userId;

    public Board(BoardRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
