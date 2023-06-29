package com.sparta.byblog.repository;

import com.sparta.byblog.dto.BoardResponseDto;
import com.sparta.byblog.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByOrderByModifiedAtDesc();

}
