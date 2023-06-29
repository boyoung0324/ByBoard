package com.sparta.byblog.controller;

import com.sparta.byblog.dto.BoardRequestDto;
import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.entity.Board;
import com.sparta.byblog.security.UserDetailsImpl;
import com.sparta.byblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //전체 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getContent() {
        return service.getContent();
    }


    //선택 조회
    @GetMapping("/board/{bno}")
    public BoardResponseDto getContentByBno(@PathVariable Integer bno) {
        return service.findContentComment(bno);

    }


    //작성
    @PostMapping("/board")
    public BoardResponseDto write(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.writeContent(requestDto, userDetails.getUser());
    }

    //삭제
    @DeleteMapping("/board/{bno}")
    public String delete(@PathVariable Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        service.deleteContent(bno, userDetails.getUser());
        return "삭제 완료";

    }

    //수정
    @PutMapping("/board/{bno}")
    public BoardResponseDto update(@PathVariable Integer bno, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.updateContent(bno, requestDto, userDetails.getUser());
    }

}
