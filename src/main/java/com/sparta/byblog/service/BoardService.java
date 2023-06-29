package com.sparta.byblog.service;

import com.sparta.byblog.dto.BoardRequestDto;
import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.entity.Board;
import com.sparta.byblog.entity.User;
import com.sparta.byblog.repository.BoardRepository;
import com.sparta.byblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    private final CommentRepository commentRepo;

//    public List<BoardResponseDto> getContent() {
//        //db에 있는 데이터들 List형태로 보내기
//        return repository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
//    }


    public List<BoardResponseDto> getContent() {
        //db에 있는 데이터들 List형태로 보내기
        List<Board> list = repository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardList = list.stream().map(BoardResponseDto::new).toList();
        return boardList;
    }


    //해당 번호에 맞는 글 찾기
    public BoardResponseDto findContentComment(Integer bno) {
        Board board = repository.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
        return new BoardResponseDto(board);
    }



    public Board findContent(Integer bno) {
        return repository.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
    }





    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    //작성
    @Transactional
    public BoardResponseDto writeContent(BoardRequestDto requestDto, User user) {
        Board board = repository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }


    //삭제
    @Transactional
    public void deleteContent(Integer bno, User user) {

        //db의 user와 넘어온 user가 같다면 삭제 진행해 and 권한이 ADMIN이면 무조건 ok
        String id = findContent(bno).getUser().getId(); //기존 db의 id


        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            repository.deleteById(bno);
        } else {
            System.out.println("삭제할 권한이 없습니다.");
        }


    }


    //수정
    @Transactional
    public BoardResponseDto updateContent(Integer bno, BoardRequestDto requestDto, User user) {

        //넘어온 id에 맞는 게시글 찾기
        Board board = findContent(bno);
        String id = findContent(bno).getUser().getId(); //기존 db의 id

        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            System.out.println("수정할 권한이 없습니다.");
            return null;
        }


    }


}
