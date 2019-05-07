package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.josh.dto.GalleryDto;
import com.josh.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService service;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String galleryForm() {
		System.out.println("gallery form");
		
		return "gallery/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/print")
	public List<GalleryDto> printGallery() {
		System.out.println("print out");
		
		List<GalleryDto> list = service.getGallery();
//		
//		model.addAttribute("galleryList", list);
		
		System.out.println(list.toString());
		return list;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, 
						 @RequestParam("userNo") String userNo, 
						 @RequestParam("comments") String comments) {
		System.out.println("uploading...");
		// 파일 이름 출력
		System.out.println("file information: " + file.getOriginalFilename());
		System.out.println("user number: " + userNo);
		System.out.println("comment: " + comments);
		
		int userno = Integer.parseInt(userNo);
		service.restore(file, userno, comments);
		
		return "redirect:/gallery/form";
	}
	
	@ResponseBody
	@RequestMapping(value="/detail", method=RequestMethod.POST)
	public GalleryDto detail(@RequestBody GalleryDto galleryDto, Model model) {
		System.out.println("detail look");
		System.out.println(galleryDto.toString());
		
		GalleryDto dto = service.detailLook(galleryDto);

		return dto;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public int delete(@RequestBody GalleryDto galleryDto) {
		System.out.println("delete view...");
		System.out.println(galleryDto.toString());
		
		service.delete(galleryDto);
		
		return galleryDto.getNo();
	}
}
