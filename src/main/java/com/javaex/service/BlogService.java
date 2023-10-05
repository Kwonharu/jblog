package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private UserDao userDao;
	
	
	public Map<String, Object> getOneUser(String id) {
		System.out.println("BlogService.getOneUser()");
		
		UserVo userVo = userDao.selectOneUserWithId(id);
		
		BlogVo blogVo = blogDao.selectOneBlogWithId(id);
		//System.out.println(blogVo);
		
		Map<String, Object> blogmap = new HashMap<String, Object>();
		
		blogmap.put("userVo", userVo);
		blogmap.put("blogVo", blogVo);

		return blogmap;
	}
	
}






