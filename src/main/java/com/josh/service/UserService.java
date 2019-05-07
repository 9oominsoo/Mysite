package com.josh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.dao.UserDao;
import com.josh.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/* 회원 가입 */
	public int join(UserDto userDto) {
		return userDao.insert(userDto);
	}
	
	/* 로그인 */
	public UserDto login(String email, String password) {
		return userDao.select(email, password);
	}
	
	/* 이메일 중복 검사 */
  	public boolean emailCheck(String email) {
  		UserDto dto = userDao.select(email);
  		
  		//데이터터가 없으면 true --> 가입할 수 있음
  		if(dto == null) {
  			return true;
  		}else {
  		// 데이터가 있으면 false --> 가입할 수 없음
  			return false;
  		}
  	}

}
