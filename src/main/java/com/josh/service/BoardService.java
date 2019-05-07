package com.josh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josh.dao.BoardDao;
import com.josh.dto.BoardDto;

@Service
public class BoardService {
  @Autowired
  private BoardDao boardDao;

  /* 리스트 출력 */
  public List<BoardDto> list(){
    return boardDao.select();
  }

  /* 게시글 추가 */
  public int write(BoardDto boardDto){
		return boardDao.insert(boardDto);
  }
  
  /* 게시글 상세보기 */
  @Transactional
  public BoardDto read(int no) {
	  BoardDto boardDto = boardDao.read(no);
	  boardDao.updateHit(no);
	  return boardDto;
  }

  /* 게시글 수정 */
  public int modify(BoardDto boardDto){
    return boardDao.modify(boardDto);
  }

  /*게시글 삭제 */
  public int delete(BoardDto boardDto){
    return boardDao.delete(boardDto);
  }
  
  /* 게시물 개수 출력 */
  public int count() {
	  return boardDao.count();
  }
  
  /* 하나의 페이지 당 게시물의 개수 */
  public int countForPage() {
	  return 5;
  }
  
  public List<BoardDto> pagePerList(int pageNum) {
	  if(pageNum<1)
			pageNum = 1;
	  
	  pageNum = pageNum*5;
	  return boardDao.pageSelect(pageNum);
  }

  public int finPage() {
	  int temp = boardDao.count();
	  int countForPage = countForPage();
	  int finPage = (int)(Math.ceil(temp/countForPage));
	  return finPage;
  }	
  
  public int startPage() {
	  int temp = finPage();
	  int startPage;
	  
	  if (temp/10 == 0)
		  startPage = 1;
	  else 
		  startPage = (temp/10)*10;
	  
	  return startPage;
  }
  
  public int endPage() {
	  int temp = finPage();
	  int endPage;
	  
	  if(temp/10 == 0)
		  endPage = 10;
	  else 
		  endPage = ((temp/10)+1)*10;
	  
	  return endPage;
  }
  
}
