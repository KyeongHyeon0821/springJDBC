package com.spring.springJDBC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	@RequestMapping(value = "/message/{msgFlag}", method = RequestMethod.GET)
	public String getMessage(Model model, @PathVariable String msgFlag,
			@RequestParam(name="mid", defaultValue = "", required = false) String mid
		) {
		
		if(msgFlag.equals("userInputOk")) {
			model.addAttribute("message", "회원에 가입되었습니다.");
			model.addAttribute("url", "/user/userMain");
		}
		else if(msgFlag.equals("userInputNo")) {
			model.addAttribute("message", "회원 가입 실패");
			model.addAttribute("url", "/user/userInput");
		}
		else if(msgFlag.equals("userIdDuplication")) {
			model.addAttribute("message", "이미 사용중인 아이디 입니다.\\n다른 아이디로 가입하세요.");
			model.addAttribute("url", "/user/userInput");
		}
//		else if(msgFlag.equals("userSearchNo")) {
//			model.addAttribute("message", "등록되지 않은 아이디입니다.\\n다시 검색해 주세요.");
//			model.addAttribute("url", "/user/userSearch");
//		}
		else if(msgFlag.equals("userSearchNo")) {
			model.addAttribute("message", "회원 정보를 찾지 못했습니다.\\n다시 검색해 주세요.");
			model.addAttribute("url", "/user/userSearch");
		}
		else if(msgFlag.equals("userDeleteOk")) {
			model.addAttribute("message", "회원을 삭제했습니다.");
			model.addAttribute("url", "/user/userList");
		}
		else if(msgFlag.equals("userDeleteNo")) {
			model.addAttribute("message", "회원삭제 실패.");
			model.addAttribute("url", "/user/userList");
		}
		else if(msgFlag.equals("userUpdateOk")) {
			model.addAttribute("message", "회원정보가 수정되었습니다.");
			model.addAttribute("url", "/user/userList");
		}
		else if(msgFlag.equals("userUpdateNo")) {
			model.addAttribute("message", "다시 시도해주세요.");
			model.addAttribute("url", "/user/userList");
		}
		return "include/message";
	}
	
}
