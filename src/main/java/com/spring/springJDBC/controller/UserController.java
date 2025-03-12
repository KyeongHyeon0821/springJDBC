package com.spring.springJDBC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.springJDBC.service.UserService;
import com.spring.springJDBC.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 회원 메인 보기
	@RequestMapping(value = ("/userMain"), method = RequestMethod.GET)
	public String userMainGet(Model model) {
		// 전체 인원수 구하기
		int userCnt = userService.getUserCnt();
		model.addAttribute("userCnt", userCnt);
		return "user/userMain";
	}
	// 회원등록 폼보기
	@RequestMapping(value = ("/userInput"), method = RequestMethod.GET)
	public String userInputGet() {
		return "user/userInput";
	}

	// 회원등록
	@RequestMapping(value = ("/userInput"), method = RequestMethod.POST)
	public String userInputPost(UserVo vo) {
		// 아이디 중복 체크
		UserVo vo2 = userService.getUserIdSearch(vo.getMid());
		System.out.println("vo2 : " + vo2);
		if (vo2 != null)
			return "redirect:/message/userIdDuplication";

		// 회원가입 처리
		int res = userService.setUserInput(vo);
		if (res != 0)	return "redirect:/message/userInputOk";
		else return "redirect:/message/userInputNo";
	}

	// 회원 검색폼 보기
	@RequestMapping(value = ("/userSearch"), method = RequestMethod.GET)
	public String userSearchGet() {
		return "user/userSearch";
	}

	// 회원 아이디 완전일치 검색 - 1건만 검색
	@RequestMapping(value = ("/userSearchList"), method = RequestMethod.GET)
	public String userSearchListGet(Model model, String mid) {
		UserVo vo = userService.getUserIdSearch(mid);
		if (vo != null) {
			model.addAttribute("vo", vo);
			return "user/userSearch";
		} else return "redirect:/message/userSearchNo";
	}
	
	// 회원 아이디 완전일치 검색 - 1건만 검색 - part에 따른 검색 (part : mid/name/address)
	@RequestMapping(value = ("/userSearchPart"), method = RequestMethod.GET)
	public String userSearchPartGet(Model model, String part, String content) {
		UserVo vo = userService.userSearchPart(part, content);
		
		if (vo != null) {
			model.addAttribute("vo", vo);
			return "user/userSearch";
		} else return "redirect:/message/userSearchNo";
	}
	
	// 회원 아이디 부분일치 검색 -1건 이상 검색
	@RequestMapping(value = ("/userSearchListOk"), method = RequestMethod.GET)
	public String userSearchListOkGet(Model model, String mid) {
		List<UserVo> vos = userService.getUserSearchListOkGet(mid);
		model.addAttribute("vos", vos);
		return "user/userSearch";
	}
	
	// 회원 전체 조회
	@RequestMapping(value = ("/userList"), method = RequestMethod.GET)
	public String userListGet(Model model) {
		List<UserVo> vos = userService.getUserList();
			model.addAttribute("vos", vos);
			return "user/userList";
	}
	
	// 회원 삭제
	@RequestMapping(value = ("/userDeleteOk"), method = RequestMethod.GET)
	public String userDeleteOkGet(int idx) {
		int res = userService.setUserDeleteOk(idx);
		if(res != 0) return "redirect:/message/userDeleteOk";
		else return "redirect:/message/userDeleteNo";
	}
	
	// 회원 수정 폼 보기
	@RequestMapping(value = ("/userUpdate"), method = RequestMethod.GET)
	public String userUpdateGet(Model model, int idx) {
		UserVo vo = userService.getUserIdxSearch(idx);
		model.addAttribute("vo", vo);
		return "user/userUpdate";
	}

	// 회원 수정 처리
	@RequestMapping(value = ("/userUpdate"), method = RequestMethod.POST)
	public String userUpdatePost(UserVo vo) {
		int res = userService.setUserUpdate(vo);
		if(res != 0) return "redirect:/message/userUpdateOk";
		else return "redirect:/message/userUpdateNo";
	}

	// 회원 전체 조회 part에 따른 검색 (part : idx desc/idx/name/age )
	@RequestMapping(value = ("/userListSearchPart"), method = RequestMethod.GET)
	public String userListSearchPartGet(Model model, String part) {
		List<UserVo> vos = userService.userListSearchPart(part);
		model.addAttribute("vos", vos);
		model.addAttribute("part", part);
		return "user/userList";
	}
}
