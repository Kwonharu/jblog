package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public UserVo selectOneUser(String id) {
		System.out.println("BlogDao.selectOneUser()");
		
		UserVo userVo = sqlSession.selectOne("blog.selectOneUser", id);
		
		return userVo;
	}
	
}






