package com.sparta.byblog.dto;

import com.sparta.byblog.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class BoardResponseDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public BoardResponseDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentList = board.getCommentList().stream()
                .map(CommentResponseDto::new)
                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed())
                .collect(Collectors.toList());


    }

}
