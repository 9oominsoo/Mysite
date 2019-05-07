package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josh.dto.BoardDto;
import com.josh.interceptor.Auth;
import com.josh.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list")
	public String list(Model model, @RequestParam(value="pageNum", required=false, defaultValue="1" ) int pageNum) {
		System.out.println("게시글 출력");
		
		List<BoardDto> list = boardService.pagePerList(pageNum);
		int count = boardService.count();
		int startPage = boardService.startPage(); 
		int finalPage = boardService.finPage();
		int endPage = boardService.endPage();
		int curPage = pageNum;
		
		System.out.println(list.toString());
		System.out.println("pageNum: " + pageNum);
		System.out.println("");
		System.out.println("finalPage: " + finalPage);
		
		
		model.addAttribute("currentPage", curPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("finalPage", finalPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "board/list";
	}
	
	@Auth
	@RequestMapping("/writeform")
	public String writeform() {
		System.out.println("게시물 작성 페이지");
		
		return "board/writeform";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardDto boardDto) {
		System.out.println("게시글 추가");
	    int count = boardService.write(boardDto);
	    
	    if(count == 1)
	    	System.out.println("작성 성공");
	    else 
	    	System.out.println("작성 실패");
		return "redirect:/board/list";
	}
	
	@RequestMapping("/read")
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("게시글 상세 보기");
		
		BoardDto boardDto = boardService.read(no);
		System.out.println(boardDto.toString());
		
		model.addAttribute("boardDto", boardDto);
		
		return "board/read";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform() {
		System.out.println("게시글 수정 페이지");
		
		return "board/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardDto boardDto) {
		System.out.println("게시글 변경");
		
		boardService.modify(boardDto);
		return "redirect:/board/list";
	}
}
