package com.sparta.byblog.controller;

import com.sparta.byblog.dto.ApiResponseDto;
import com.sparta.byblog.dto.BoardRequestDto;
import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.security.UserDetailsImpl;
import com.sparta.byblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //전체 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> getContent() {
        return service.getContent();
    }


    //선택 조회
    @GetMapping("/boards/{bno}")
    public BoardResponseDto getContentByBno(@PathVariable Integer bno) {
        return service.findContentComment(bno);

    }


    //작성
    @PostMapping("/boards")
    public BoardResponseDto write(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.writeContent(requestDto, userDetails.getUser());
    }

    //삭제
    @DeleteMapping("/boards/{bno}")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            service.deleteContent(bno, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("삭제 성공", HttpStatus.OK.value()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }

    }

//    수정
    @PutMapping("/boards/{bno}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable Integer bno, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
           BoardResponseDto responseDto =  service.updateContent(bno, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("수정 성공", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }





}
