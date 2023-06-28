package com.sparta.byblog.controller;

import com.sparta.byblog.dto.CommentRequestDto;
import com.sparta.byblog.dto.CommentResponseDto;
import com.sparta.byblog.security.UserDetailsImpl;
import com.sparta.byblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    //댓글
    private final CommentService service;


    //댓글 작성
    @PostMapping("/comment")
    public CommentResponseDto write(@RequestBody CommentRequestDto requestDto, @RequestParam Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeComment(requestDto,userDetails.getUser(),bno);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{cno}")
    public String delete(@PathVariable Integer cno,@RequestParam Integer bno,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        service.deleteComment(cno,bno,userDetails.getUser());
        return "삭제 완료";

    }

    //댓글 수정
    @PutMapping("/comment/{cno}")
    public CommentResponseDto update(@PathVariable Integer cno,@RequestParam Integer bno, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateCommnet(cno,bno,requestDto,userDetails.getUser());
    }












}
