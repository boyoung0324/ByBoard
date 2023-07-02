package com.sparta.byblog.dto;

import com.sparta.byblog.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponseDto {

    private Integer cno;
    private String comment;
    private String commenter;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.cno = comment.getCno();
        this.comment = comment.getComment();
        this.commenter = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


}
