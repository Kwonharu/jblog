package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	/*
	//블로그 관리 페이지 예외처리용 함수
	private HttpSession session;
	private Model model;
	private String check(String paramId, String sessionId, String where) {
		//이 조건 먼저 체크 안 하면 getId()할 때 오류남
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/loginForm";
		//파라미터 id가 세션의 id와 같다면
		}else if(paramId.equals(sessionId)){
			Map<String, Object> blogmap = blogService.getOneUser(paramId);
			model.addAttribute("blogmap", blogmap);
			return "blog/admin/blog-admin-"+where;
		//나가
		}else {
			return "redirect:/error";
		}
	}
	*/
	
	
	//블로그 메인
	@RequestMapping(value="/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String main(@PathVariable(value="id") String id, Model model){
		System.out.println("BlogController.main()");
		//System.out.println(id);
		
		Map<String, Object> blogmap = blogService.mainAdminBlog(id);

		//id가 실제로 있는지
		if((blogmap.get("blogVo")) == null) {
			return "error/403";
		}else {
			model.addAttribute("blogmap", blogmap);
			return "blog/blog-main";
		}
	}
	
	//기본설정
	@RequestMapping(value="/{id}/admin/basic", method={RequestMethod.GET, RequestMethod.POST})
	public String basic(@PathVariable(value="id") String id, Model model, HttpSession session){
		System.out.println("BlogController.basic()");
		/*
		System.out.println("세션에 저장된 id: "+((UserVo) session.getAttribute("authUser")).getId());
		System.out.println("파라미터 id: "+id);
		System.out.println("세션에 저장된 id class: "+(((UserVo) session.getAttribute("authUser")).getId()).getClass());
		System.out.println("파라미터 id class: "+id.getClass());
		*/

		//이 조건 먼저 체크 안 하면 getId()할 때 오류남
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/loginForm";
		//파라미터 id가 세션의 id와 같다면
		}else if((id.equals(((UserVo) session.getAttribute("authUser")).getId()))){
			Map<String, Object> blogmap = blogService.mainAdminBlog(id);
			model.addAttribute("blogmap", blogmap);
			return "blog/admin/blog-admin-basic";
		//나가
		}else {
			return "redirect:/error";
		}
	}
	
	//기본설정변경
	@RequestMapping(value="/{id}/admin/basicUpdate", method={RequestMethod.GET, RequestMethod.POST})
	public String basicUpdate(@PathVariable(value="id") String id, 
							  @ModelAttribute BlogVo blogVo,
							  @RequestParam(value="file") MultipartFile file,
							  HttpSession session){
		System.out.println("BlogController.basicUpdate()");

		//로그인 했는지 확인
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/loginForm";
		//파라미터 id가 세션의 id와 같다면
		}else if((id.equals(((UserVo) session.getAttribute("authUser")).getId()))){
			
			String blogId = ((UserVo) session.getAttribute("authUser")).getId();
			String blogTitle = blogVo.getBlogTitle();

			blogService.blogUpdateBasic(blogId, blogTitle, file);
			
			return "redirect:/{id}";
		//나가
		}else {
			return "redirect:/error";
		}
	}
	
		
	//카테고리 페이지 + 리스트 출력
	@RequestMapping(value="/{id}/admin/category", method={RequestMethod.GET, RequestMethod.POST})
	public String category(@PathVariable(value="id") String id, Model model, HttpSession session){
		System.out.println("BlogController.category()");
		
		//이 조건 먼저 체크 안 하면 getId()할 때 오류남
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/loginForm";
		//파라미터 id가 세션의 id와 같다면
		}else if((id.equals(((UserVo) session.getAttribute("authUser")).getId()))){
			Map<String, Object> blogmap = blogService.mainAdminBlog(id);
			model.addAttribute("blogmap", blogmap);
			return "blog/admin/blog-admin-cate";
		//나가
		}else {
			return "redirect:/error";
		}
	}	
	
	//카테고리 리스트 출력 (ajax)
	@ResponseBody
	@RequestMapping(value="/{id}/admin/categoryList", method={RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> categoryList(@PathVariable(value="id") String id){
		System.out.println("BlogController.categoryInsert()");

		List<CategoryVo> categoryList = blogService.categoryList(id);
		
		return categoryList;
	}	
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value="/{id}/admin/categoryInsert", method={RequestMethod.GET, RequestMethod.POST})
	public CategoryVo categoryInsert(@ModelAttribute CategoryVo cateVo){
		System.out.println("BlogController.categoryInsert()");

		CategoryVo categoryVo = blogService.categoryInsert(cateVo);
		
		return categoryVo;

	}	
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/admin/categoryDelete", method={RequestMethod.GET, RequestMethod.POST})
	public int categoryDelete(@PathVariable(value="id") String id,
							  @RequestParam(value="cateNo") int cateNo){
		System.out.println("BlogController.categoryDelete()");
		
		int count = blogService.categoryDelete(cateNo);
		
		return count;
	}	
	
			
	//글작성
	@RequestMapping(value="/{id}/admin/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@PathVariable(value="id") String id, Model model, HttpSession session){
		System.out.println("BlogController.writeForm()");

		//이 조건 먼저 체크 안 하면 getId()할 때 오류남
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/loginForm";
		//파라미터 id가 세션의 id와 같다면
		}else if((id.equals(((UserVo) session.getAttribute("authUser")).getId()))){
			Map<String, Object> blogmap = blogService.mainAdminBlog(id);
			model.addAttribute("blogmap", blogmap);
			return "blog/admin/blog-admin-write";
		//나가
		}else {
			return "redirect:/error";
		}

	}
	
	//post
	@RequestMapping(value="/{id}/admin/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo){
		System.out.println("BlogController.write()");
		
		int count = blogService.writePost(postVo);
		
		return "redirect:/{id}";
	}	
	
	
	//에러 페이지 링크
	@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
	public String error(){
		System.out.println("BlogController.error()");
		
		return "error/403";
	}	
	
}





