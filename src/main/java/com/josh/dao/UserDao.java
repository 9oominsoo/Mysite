package com.josh.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josh.dto.UserDto;


@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public int insert(UserDto userDto) {
		return sqlSession.insert("user.insert",userDto);
	}

	public UserDto select(String email, String password){
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("email", email);
		userMap.put("password", password);
		return sqlSession.selectOne("user.select", userMap);
	}
	
	/*회원정보 이메일로 가져오기*/
	public UserDto select(String email) {
		return sqlSession.selectOne("user.selectByEmail", email);
	}
}
