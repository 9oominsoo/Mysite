package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josh.dto.HierarchyDto;
import com.josh.service.HierarchyService;

@Controller
@RequestMapping("hierarchy")
public class HierarchyController {

  @Autowired
  private HierarchyService service;

  @RequestMapping("/list")
  public String list(Model model){
    System.out.println("계층형 게시판 메인 화면");

    List<HierarchyDto> list = service.getList();
    System.out.println(list.toString());
    model.addAttribute("list", list);

    return "hierarchy/list";
  }

  @RequestMapping("/writeform")
  public String writeform(){
    System.out.println("계층형 게시판 게시글 작성 화면");

    return "hierarchy/writeform";
  }

  @RequestMapping("/write")
  public String write(@ModelAttribute HierarchyDto hierarchyDto){
    System.out.println("새글 등록");
    System.out.println(hierarchyDto.toString());

    int count = service.insertNew(hierarchyDto);

    if(count == 1)
      System.out.println("새글 등록 성공!");
    else
      System.out.println("새글 등록 실패");

    return "redirect:/hierarchy/list";
  }

  @RequestMapping("/read")
  public String read(){
    System.out.println("계층형 게시판 게시글 상세 보기 화면");
    
    return "hierarchy/read";
  }

  @RequestMapping("/modifyform")
  public String modifyform(){
    System.out.println("계층형 게시판 게시글 변경 화면");

    return "hierarchy/modifyform";
  }

  @RequestMapping("/replyform")
  public String replyform() {
	  System.out.println("답글 등록 화면");

	  return "hierarchy/replyform";
  }

  @RequestMapping("/reply")
  public String reply(@ModelAttribute HierarchyDto hierarchyDto,
		  			  @RequestParam("groupNo") int groupNo,
		  			  @RequestParam("orderNo") int orderNo,
		  			  @RequestParam("parentDepth") int parentDepth) {
	  System.out.println("답글 등록");
	  System.out.println("그룹 넘버: " + groupNo);
	  System.out.println("순번: " + orderNo);
	  System.out.println("원글의 깊이: " + parentDepth);


	  service.insertReply(hierarchyDto, groupNo, orderNo, parentDepth);

	  return "redirect:/hierarchy/list";
  }

  @RequestMapping("deleteReply")
  public String reply(@ModelAttribute HierarchyDto hierarchyDto){
    System.out.println("답글 삭제");
    System.out.println(hierarchyDto.toString());
    service.deleteReply(hierarchyDto);

    return "redirect:/hierarchy/list";
  }

}
