package com.josh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josh.dto.UserDto;
import com.josh.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		System.out.println("회원가입");

		return "user/joinform";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserDto userDto) {
		System.out.println("유저등록");

		int count=userService.join(userDto);
		if(count == 1) {
			System.out.println("등록 성공!");
		}else {
			System.out.println("등록 실패");
		}

		return "user/joinsuccess";
	}

	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform(){
		System.out.println("로그인");

		return "user/loginform";
	}

//  @RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(@RequestParam("email") String email,
//						@RequestParam("password") String password,
//						HttpSession session){
//		UserDto authUser = userService.login(email, password);
////		System.out.println(authUser.toString());
//		if(authUser != null) {
//			session.setAttribute("authUser", authUser);
//			return "redirect:/main";
//		}else {
//			return "redirect:/user/longinform?result=fail";
//		}
//	}
  
  @ResponseBody // html파일과 조합해서 보내는 것이 아니라, 순수 데이터만 보낼 것이다. 따라서 jsp 파일을 찾을 필요가 없다.
  // return 형도 String 필요가 없다. 기존에는 dispatcher가 알아볼 수 있게 String 형이어야 햇다!!
  // 화면은 바꾸면 안되기 대문에 jsp 파일을 return 하면 안된다.
  // 명시 후 data만 보내주어야 한다.
  @RequestMapping(value="/emailcheck", method=RequestMethod.POST)
  public boolean emailcheck(@RequestParam("email") String email) {
	  System.out.println(email);
	  
	  boolean check = userService.emailCheck(email);
	  
	  return check;
  }
}
