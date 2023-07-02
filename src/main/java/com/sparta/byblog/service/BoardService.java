package com.sparta.byblog.service;

import com.sparta.byblog.dto.BoardRequestDto;
import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.entity.Board;
import com.sparta.byblog.entity.User;
import com.sparta.byblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;


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




    //작성
    @Transactional
    public BoardResponseDto writeContent(BoardRequestDto requestDto, User user) {

        if (user == null) {
            throw new IllegalArgumentException("로그인 후 작성이 가능합니다.");
        }

        Board board = repository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }


    //삭제
    @Transactional
    public void deleteContent(Integer bno, User user) {

        //db의 user와 넘어온 user가 같다면 삭제 진행해 and 권한이 ADMIN이면 무조건 ok
        String id = findContent(bno).getUser().getId(); //기존 db의 id


        if (!(id.equals(user.getId()) || user.getRole().toString().equals("ADMIN"))) {
            throw new RejectedExecutionException();
        }
        repository.deleteById(bno);


    }


    //수정
    @Transactional
    public BoardResponseDto updateContent(Integer bno, BoardRequestDto requestDto, User user) {

        //넘어온 id에 맞는 게시글 찾기
        Board board = findContent(bno);
        String id = findContent(bno).getUser().getId(); //기존 db의 id


        if (!(id.equals(user.getId()) || user.getRole().toString().equals("ADMIN"))) {
            throw new RejectedExecutionException();
        }
        board.update(requestDto);
        return new BoardResponseDto(board);


    }


}
