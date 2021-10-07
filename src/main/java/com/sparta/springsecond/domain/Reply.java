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
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String content;

    private String username;

    private Long bno;

    public Reply(ReplyRequestDto requestDto, String username) {
        this.username = username;
        this.content = requestDto.getContent();
        this.bno = requestDto.getBno();
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
