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
	
	
	//블로그 접속 시 진짜 있는 id인지 확인
	public UserVo selectOneUserWithId(String id) {
		System.out.println("UserDao.selectOneUserWithId()");
		
		UserVo userVo = sqlSession.selectOne("user.selectOneUserWithId", id);
		
		return userVo;
	}
	
}






