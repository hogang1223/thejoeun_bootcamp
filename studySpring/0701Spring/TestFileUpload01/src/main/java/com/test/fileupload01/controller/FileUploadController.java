package com.test.fileupload01.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.fileupload01.command.Command;
import com.test.fileupload01.command.ListCommand;
import com.test.fileupload01.command.ModifyCommand;
import com.test.fileupload01.command.WriteCommand;
import com.test.fileupload01.dao.FileUploadDao;

@Controller
public class FileUploadController {
//	@Autowired
//	FileUploadService fileuploadService;
	
	@Autowired
	private SqlSession sqlSession;
	
	Command command = null;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		
		model.addAttribute("request", request);
		command = new ListCommand();
		command.execute(sqlSession, model);
		
		return "/list";
	}
	
	// uploadForm 보여주기
	@RequestMapping("/uploadForm")
	public String uploadForm() {
	
		return "uploadForm";
	}
	
	// upload하기 - File 지정 폴더에 저장 and DB에 insert
	@RequestMapping("upload")
	public String upload(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("upload()");
		
		model.addAttribute("mtfRequest", mtfRequest);
		command = new WriteCommand();
		command.execute(sqlSession, model);
		return "uploadResult"; 
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		System.out.println("detail()");
		FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);
		model.addAttribute("detail", dao.detailDao(request.getParameter("id")));
		
		return "/detail";
	}
	
	@RequestMapping("modify")
	public String modify(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("mtfRequest", mtfRequest);
		command = new ModifyCommand();
		command.execute(sqlSession, model);
		return "redirect:list";
	}

}
