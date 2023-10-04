package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//등록
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
		int count = sqlSession.insert("user.insertUser", userVo);
		//System.out.println(count);
		
		return count;
	}
	
	//1명
	public UserVo selectOneUser(UserVo userVo) {
		System.out.println("UserDao.selectOneUser()");
		
		UserVo authUser = sqlSession.selectOne("user.selectOneUser", userVo);
		
		return authUser;
	}
	
}






