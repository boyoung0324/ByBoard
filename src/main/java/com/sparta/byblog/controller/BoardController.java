package com.sparta.byblog.controller;

import com.sparta.byblog.dto.BoardRequestDto;
import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.entity.Board;
import com.sparta.byblog.entity.Comment;
import com.sparta.byblog.security.UserDetailsImpl;
import com.sparta.byblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //전체 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getContent(){
        return service.getContent();
    }

    //선택 조회
    @GetMapping("/board/{bno}")
    public ResponseEntity<List<BoardResponseDto>> getContentByBno(@PathVariable Integer bno){
//        Board board =  service.findContent(bno);
//        BoardResponseDto responseDto = new BoardResponseDto(board);
//        return responseDto;

        return service.findContentandComment(bno);

    }


    //작성
    @PostMapping("/board")
    public BoardResponseDto write(@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeContent(requestDto,userDetails.getUser());
    }

    //삭제
    @DeleteMapping("/board/{bno}")
    public String delete(@PathVariable Integer bno,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        service.deleteContent(bno,userDetails.getUser());
        return "삭제 완료";

    }

    //수정
    @PutMapping("/board/{bno}")
    public BoardResponseDto update(@PathVariable Integer bno,@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateContent(bno,requestDto,userDetails.getUser());
    }

}
