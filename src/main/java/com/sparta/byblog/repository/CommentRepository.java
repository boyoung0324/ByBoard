package com.sparta.byblog.repository;

import com.sparta.byblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findByBoard_Bno(Integer bno);

}
