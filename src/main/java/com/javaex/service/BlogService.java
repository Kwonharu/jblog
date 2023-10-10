package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private UserDao userDao;
	
	//메인, 어드민
	public Map<String, Object> mainAdminBlog(String id) {
		System.out.println("BlogService.getOneUser()");
		
		
		//블로그 메인에 이름!!!
		UserVo userVo = userDao.selectOneUserWithId(id);
		
		//id확인 + 화면 포워딩
		BlogVo blogVo = blogDao.selectOneBlogWithId(id);
		//System.out.println(blogVo);
		
		//카테고리 리스트
		List<CategoryVo> cateList = blogDao.selectCateList(id);
		
		
		Map<String, Object> blogmap = new HashMap<String, Object>();

		blogmap.put("userVo", userVo);
		blogmap.put("blogVo", blogVo);
		blogmap.put("cateList", cateList);
		
		return blogmap;
	}
	
	
	//블로그 기본 수정 + 파일 업데이트
	public int blogUpdateBasic(String id, String title, MultipartFile file) {
		System.out.println("BlogService.blogUpdateBasic()");
		
		int count = -1;
		
		//파일 없어도 작동 해야 함
		if(file.isEmpty() == false) {

			//파일 저장 디렉토리
			String saveDir = "C:\\javaStudy\\jblog_file";
			
			//오리지널 파일 명
			String orgName = file.getOriginalFilename();
			
			//확장자
			String exName = orgName.substring(orgName.lastIndexOf("."));
			
			//저장 파일명(겹치지 않아야 함)
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
								//		 long		 	 +			String
			
			//파일 전체 경로
			String filePath = saveDir + "\\" + saveName;
			
			//vo로 묶기
			BlogVo blogVo = new BlogVo(id, title, saveName);
			
			//dao 만들기 --> db 저장
			count = blogDao.updateBlogBasic(blogVo);
			
			
			//파일 저장(서버쪽 하드 디스크에 저장) //////////////////////////////
			
			try {
				byte[] fileData;
				
				fileData = file.getBytes();
				
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String logoFile = blogDao.selectOneBlogWithId(id).getLogoFile();
			
			BlogVo blogVo = new BlogVo(id, title, logoFile);
			
			count = blogDao.updateBlogBasic(blogVo);
		}
		
		return count;
	}
	
	
	//카테고리 리스트
	public List<CategoryVo> categoryList(String id) {
	
		List<CategoryVo> cateList = blogDao.selectCateList(id);
	
		return cateList;
	}
	
	//카테고리 추가
	public CategoryVo categoryInsert(CategoryVo cateVo) {
		System.out.println("BlogService.categoryInsert()");
		
		//System.out.println(cateVo);
		blogDao.insertCategory(cateVo);
		//System.out.println(cateVo);
		
		CategoryVo categoryVo = blogDao.selectOneCate(cateVo);
		
		return categoryVo;
	}
	
	//카테고리 삭제
	public int categoryDelete(int cateNo) {
		System.out.println("BlogService.categoryDelete()");
		
		return blogDao.deleteCategory(cateNo);
	}
	

	//post 추가
	public int writePost(PostVo postVo) {
		System.out.println("BlogService.writePost()");
		
		//제목 not null 처리 해야함
		if(postVo.getPostTitle().equals(null)) {
			return -1;
		}
		
		return blogDao.insertPost(postVo);
	}
	
	
}






