package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 기본
	@RequestMapping(value="/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String blog(@PathVariable(value="id") String id, Model model){
		System.out.println("BlogController.blog()");
		System.out.println(id);
		
		UserVo userVo = blogService.getOneUser(id);

		if(userVo == null) {
			return "error/403";
		}else {
			model.addAttribute(userVo);
			return "blog/blog-main";
		}
	}
	
	//블로그 기본설정 수정
	@RequestMapping(value="/{id}/admin/basic", method={RequestMethod.GET, RequestMethod.POST})
	public String basic(@PathVariable(value="id") String id, Model model){
		System.out.println("BlogController.basic()");
		
		
		return "blog/admin/blog-admin-basic";
	}
	
	
}





