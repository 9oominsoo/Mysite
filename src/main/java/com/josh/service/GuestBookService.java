package com.josh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.dao.GuestBookDao;
import com.josh.dto.GuestBookDto;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao dao;
	
	public List<GuestBookDto> getList(){
		return dao.getList();
	};
	
	public int deleteList(GuestBookDto dto){
		int flag = dao.delete(dto);
		
		if(flag == 1)
			return dto.getNo();
		else 
			return 0;
	}
	
	public GuestBookDto insertList(GuestBookDto dto) {
		System.out.println("before: " + dto.getNo());// 값 출력안됨. 왜? 아직 dao에서 sequence를 안받았거든 !!
		int flag = dao.insert(dto);
		System.out.println("after: "+ dto.getNo());// 값 출력됨, 왜? dao 에서 sequence를  할당받았기 때문에 값이 들어있기 때문이다.
		
		if(flag == 1) {
			GuestBookDto guestBookDto = dao.findList(dto);
			return guestBookDto;
		}		
		else {
			return null;
		}
	}
	
}
