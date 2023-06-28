package com.sparta.byblog.service;

import com.sparta.byblog.dto.CommentRequestDto;
import com.sparta.byblog.dto.CommentResponseDto;
import com.sparta.byblog.entity.Board;
import com.sparta.byblog.entity.Comment;
import com.sparta.byblog.entity.User;
import com.sparta.byblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final BoardService boardService;


    //작성
    @Transactional
    public CommentResponseDto writeComment(CommentRequestDto requestDto, User user, Integer bno) {


        //bno에 맞는 board객체 찾기
        Board board = boardService.findContent(bno);

        //comment객체에 requestDto랑  user랑 board넣기
        Comment comment = commentRepo.save(new Comment(requestDto, user, board));


        //board의 commentList에 comment 넣기
        board.addCommentList(comment);

        List<Comment> list = board.getCommentList();
        for (Comment c : list) {
            System.out.println("Comment = " + c.getComment());
        }

        return new CommentResponseDto(comment);


    }

    //삭제
    @Transactional
    public void deleteComment(Integer cno, Integer bno, User user) {
        String id = findComment(cno).getUser().getId();
        Board board = boardService.findContent(bno);

        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            commentRepo.deleteById(cno);
            List<Comment> list = board.getCommentList();


            list.removeIf(c -> c.getCno() == cno);

            for (Comment c : list) {
                System.out.println("Comment = " + c.getCno());
            }
        } else {
            System.out.println("삭제할 권한이 없습니다.");
        }


    }


    @Transactional
    public CommentResponseDto updateCommnet(Integer cno, Integer bno, CommentRequestDto requestDto, User user) {

        String id = findComment(cno).getUser().getId();
        Board board = boardService.findContent(bno); //1번 게시글. 댓글 많이 달려있음
        Comment comment = findComment(cno); //하나의 댓글

        //db수정 & commentList도 수정해야 한다

        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            List<Comment> list = board.getCommentList();
            comment.update(requestDto);

            return new CommentResponseDto(comment);
        } else {
            System.out.println("수정할 권한이 없습니다.");
            return null;
        }


    }


    //댓글 찾기
    public Comment findComment(Integer cno) {
        return commentRepo.findById(cno).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
    }

}
