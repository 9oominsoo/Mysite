package com.josh.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.josh.dto.FileDto;

@Service
public class FileUploadService {
	
	public FileDto restore(MultipartFile file) {
		
		String saveDir = "D:\\bitStudy\\upload";
		// 오리지널 파일명 
		    // 실제로 우리가 올리는 파일의 이름 
		String orgName = file.getOriginalFilename();
		System.out.println("orginal name: " + orgName);
		
		// 확장자 
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("extender name: " + exName);
		
		// 저장할 파일명
			// 서버가 동일이름의 파일들을 구분하기 위해 새로운 이름을 랜덤으로 생성해서 저장해주는 이름 
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("save name: " + saveName);
		
		// 파일 패스 
			// 저장할 파일명과 
		String filePath = saveDir + "\\" + saveName;
		System.out.println("file path: " + filePath);
		
		// 파일 사이즈 
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);
		
		FileDto fileDto = new FileDto(filePath, orgName, saveName, fileSize);
		System.out.println(fileDto.toString());
		
		// 서버 파일 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);

			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);

			if (bout != null) {
				bout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 화면에 정보를 뿌려주기 위해서 fileDto를 리턴 
		return fileDto;
		
	}
}
