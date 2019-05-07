package com.josh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.josh.dto.FileDto;
import com.josh.service.FileUploadService;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Autowired 
	private FileUploadService service;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		
		return "fileupload/form";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("file uploading...");
		System.out.println(file.getOriginalFilename());
		
		FileDto fileDto = service.restore(file);
		model.addAttribute("fileDto", fileDto);
		
		return "fileupload/result";
	}
}
