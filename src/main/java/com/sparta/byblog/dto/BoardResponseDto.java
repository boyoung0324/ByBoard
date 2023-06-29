package com.sparta.byblog.dto;

import com.sparta.byblog.entity.Board;
import com.sparta.byblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class BoardResponseDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
//    private List<Comment> commentList; 넣으니까 조회 실행이 안 돼서 주석함ㅠ

    public BoardResponseDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
//        this.commentList = board.getCommentList();
    }


}
