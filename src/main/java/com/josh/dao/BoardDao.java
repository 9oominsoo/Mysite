package com.josh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josh.dto.BoardDto;

@Repository
public class BoardDao {

    @Autowired
    private SqlSession sqlSession;

    public List<BoardDto> select(){
      List<BoardDto> list = sqlSession.selectList("board.select");
      return list;
    }
    
    public List<BoardDto> pageSelect(int pageNum){
    	List<BoardDto> list = sqlSession.selectList("board.paging", pageNum);
    	return list;
    }
    
    public BoardDto read(int no) {
    	BoardDto boardDto = sqlSession.selectOne("board.read", no);
    	return boardDto;
    }
    
    public int updateHit(int no) {
    	return sqlSession.update("board.update", no);
    }
    
    public int insert(BoardDto boardDto){
      return sqlSession.insert("board.insert", boardDto);
    }

    public int modify(BoardDto boardDto){
      return sqlSession.selectOne("board.modify", boardDto);
    }

    public int delete(BoardDto boardDto){
      return sqlSession.selectOne("board.delete", boardDto);
    }
    
    public int count() {
    	int count = sqlSession.selectOne("board.count");
    	return count;
    }
    
}
