package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	
	//회원가입
	public boolean userInsert(UserVo userVo) {
		System.out.println("UserService.userInsert()");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		userDao.insertUser(userVo);
		
		//타이틀 문구 만들어서 세터...
		blogDao.insertBlogTitle(userVo);
		
		
		//전부 성공 시 true, 하나라도 실패 시 rollback;
		return true;
	}
	
	//1명 가져오기
	public UserVo oneUserSelect(UserVo userVo) {
		System.out.println("UserService.oneUserSelect()");
		
		UserVo authUser = userDao.selectOneUser(userVo);
		
		return authUser;
	}
	
}





