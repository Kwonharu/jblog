package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserService.userInsert()");
		
		int count = userDao.insertUser(userVo);
		
		return count;
	}
	
	//1명 가져오기
	public UserVo oneUserSelect(UserVo userVo) {
		System.out.println("UserService.oneUserSelect()");
		
		UserVo authUser = userDao.selectOneUser(userVo);
		
		return authUser;
	}
	
}





