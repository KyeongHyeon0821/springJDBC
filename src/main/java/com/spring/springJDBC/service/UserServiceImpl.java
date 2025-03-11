package com.spring.springJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springJDBC.dao.UserDao;
import com.spring.springJDBC.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public int setUserInput(UserVo vo) {
		return userDao.setUserInput(vo);
	}

	@Override
	public int getUserCnt() {
		return userDao.getUserCnt();
	}

	@Override
	public UserVo getUserIdSearch(String mid) {
		return userDao.getUserIdSearch(mid);
	}
}
