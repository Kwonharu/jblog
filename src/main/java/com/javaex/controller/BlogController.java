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
	
	@RequestMapping(value="/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String blog(@PathVariable(value="id") String id, Model model){
		System.out.println("BlogController.blog()");
		
		UserVo userVo = blogService.getOneUser(id);
		
		if(userVo == null) {
			return "error/403";
		}else {
			model.addAttribute(userVo);
			return "blog/blog-main";
		}

	}
	
}





