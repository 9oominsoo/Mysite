package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josh.dto.GuestBookDto;
import com.josh.service.GuestBookService;

@Controller
@RequestMapping(value="/api/gb")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;

	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	//return이 String이 아니다
	public List<GuestBookDto> list() {
		System.out.println("ajax list");
		List<GuestBookDto> guestBookList = guestBookService.getList();
		System.out.println(guestBookList.toString());
		
		return guestBookList;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteList", method=RequestMethod.POST)
	public int deleteList(@ModelAttribute GuestBookDto guestBookDto) {
		System.out.println("delete list");
		System.out.println(guestBookDto.toString());
		int result = guestBookService.deleteList(guestBookDto);

		return result;
	}
	
//	@RequestBody json형태로 받기위해 requestbody안에 파라미터가 들어있다고 알려주는 어노테이션
//  @Model Attribute나 @Requestparam은 파라미터로 넘어오는 경우, @requestBody는 바디안에 같이 넘어오는 경우
	@ResponseBody 
	@RequestMapping(value="/insertList", method=RequestMethod.POST)
	public GuestBookDto insertList(@RequestBody GuestBookDto guestBookDto) {
		System.out.println("create list");
		System.out.println("input: "+ guestBookDto.toString());
		
		GuestBookDto dto = guestBookService.insertList(guestBookDto);
		System.out.println("output: "+dto.toString());
		return dto;
	}
}
