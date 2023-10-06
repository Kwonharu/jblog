package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//회원가입 시 블로그 타이틀 생성
	public int insertBlogTitle(UserVo userVo) {
		System.out.println("BlogDao.insertBlogTitle()");
		
		return sqlSession.insert("blog.insertBlogTitle", userVo);
	}

	//id로 블로그 정보 찾기
	public BlogVo selectOneBlogWithId(String id) {
		System.out.println("BlogDao.selectOneBlogWithId()");
		
		BlogVo blogVo = sqlSession.selectOne("blog.selectOneBlogWithId", id);
		//System.out.println(blogVo);
		
		return blogVo;
	}
	
	//기본 설정 업데이트
	public int updateBlogBasic(BlogVo BlogVo) {
		System.out.println("BlogDao.updateBlogBasic()");
		
		return sqlSession.update("blog.updateBlogBasic", BlogVo);
	}
	
	
	//카테고리 리스트
	public List<CategoryVo> selectCateList(String id) {
		System.out.println("BlogDao.selectCateList()");
		
		List<CategoryVo> cateList = sqlSession.selectList("blog.selectCateList", id);
		System.out.println(cateList);
		
		return cateList;
	}
	
}






