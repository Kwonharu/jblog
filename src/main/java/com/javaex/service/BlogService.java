package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
}






