package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	
	public UserVo getOneUser(String id) {
		
		UserVo userVo = blogDao.selectOneUser(id);
		
		return userVo;
	}
	
}






