package com.spring.springJDBC.service;

import java.util.List;

import com.spring.springJDBC.vo.UserVo;

public interface UserService {

	int setUserInput(UserVo vo);

	int getUserCnt();

	UserVo getUserIdSearch(String mid);

	List<UserVo> getUserList();

	int setUserDeleteOk(int idx);

	UserVo getUserIdxSearch(int idx);

	int setUserUpdate(UserVo vo);

	List<UserVo> getUserSearchListOkGet(String mid);

	UserVo userSearchPart(String part, String content);

	List<UserVo> userListSearchPart(String part);

}
