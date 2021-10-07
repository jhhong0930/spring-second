package com.sparta.springsecond.repository;

import com.sparta.springsecond.domain.Reply;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByBno(Long bno, Sort regDate);

}
