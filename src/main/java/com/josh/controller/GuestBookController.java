package com.josh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josh.dao.GuestBookDao;
import com.josh.dto.GuestBookDto;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	GuestBookDao dao;

	@RequestMapping("/addform")
	public String addform(Model model) {
		System.out.println("show list");
		// GuestBookDao dao = new GuestBookDao();

		List<GuestBookDto> list = new ArrayList<GuestBookDto>();
		list = dao.getList();
		model.addAttribute("list", list);

		return "guestbook/addform";
	}

	@RequestMapping("/deleteform")
	public String deleteform() {
		System.out.println("delete list");

		return "guestbook/deleteform";
	}
	
	/* AjaxGuestBook */
	@RequestMapping("/ajaxlist")
	public String ajaxList() {
		System.out.println("ajax list");
		
		return "guestbook/ajaxlist"; 
	}
}
