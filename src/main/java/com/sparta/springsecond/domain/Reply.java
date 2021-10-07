package com.sparta.springsecond.domain;

import com.sparta.springsecond.dto.ReplyRequestDto;
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
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String content;

    private String username;

    private Long bno;

    public Reply(ReplyRequestDto requestDto, String username, Long bno) {
        this.username = username;
        this.bno = bno;
        this.content = requestDto.getContent();
    }

}
