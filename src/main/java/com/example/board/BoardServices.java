package com.example.board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardServices{
    public int insertBoard(BoardVO vo);
    public int deleteBoard(int seq);
    public int updateBoard(BoardVO vo);
    public BoardVO getBoard(int seq);
    public List<BoardVO> getBoardList();
    public int updatelikecount(BoardVO vo);
}