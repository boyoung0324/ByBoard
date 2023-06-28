//package com.sparta.byblog;
//
//
//import com.sparta.byblog.entity.Board;
//import com.sparta.byblog.entity.Comment;
//import com.sparta.byblog.repository.BoardRepository;
//import com.sparta.byblog.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//public class TableTest {
//
//    @Autowired
//    BoardRepository boardRepo;
//
//    @Autowired
//    UserRepository userRepo;
//
//
//
//    @Test
//    public void testList(){
//        Board board = new Board();
//        List<Comment> list = board.getCommentList();
//
//
//
//
//    }
//
////    @Autowired
////    CommentRepository commentRepo;
//
//
////    @BeforeEach //테스트마다 데이터 넣는 것이 실행되도록
////    @Test
////    @Transactional
////    @BeforeEach
////    public void testData() {
////
////        for (int i = 0; i < 100; i++) {
////
////
////
////            User user = new User();
////            user.setId("xiahni"+i);
////            user.setPwd("1234"+i);
////            user.setUsername("boyoung"+i);
////            user.setRole(UserRoleEnum.valueOf("USER"));
////            userRepo.save(user);
////
////
////
////            Board board = new Board();
////            board.setBno(i);
////            board.setTitle("title" + i);
////            board.setContent("content" + i);
////            board.setUser(user);
////            boardRepo.save(board);
////
//////            Comment comment =  new Comment();
//////            comment.setCno(i);
//////            comment.setBoard(board);
//////            comment.setUser(user);
//////            comment.setComment("comment"+i);
//////            commentRepo.save(comment);
////
////        }
////    }
//
////    @Transactional
////
////    @Test
////    public void delete(){
////       boardRepo.deleteById(99);
//////       commentRepo.deleteByBoard_Bno(99);
////    }
////
//////    @Transactional
////    @Rollback(value = false)
////    @Test
////    void findContent() {
////       boardRepo.deleteById(5);
////        Board board =  boardRepo.findById(5).orElseThrow(() ->
////                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
////        System.out.println("Board = " + board);
////
////    }
//
//
//
//
//}
