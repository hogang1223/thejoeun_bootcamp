package com.springlec.add01.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.add01.command.Command;
import com.springlec.add01.command.ListCommand;
import com.springlec.add01.dao.Dao;

@Controller
public class AddController {
	
	@Autowired
	private SqlSession sqlSession;
	
	Command command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
//		Dao dao = sqlSession.getMapper(Dao.class);
//		model.addAttribute("list", dao.listDao());
		
		command = new ListCommand();
		command.execute(sqlSession, model);
		
		return "/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/writeForm";
	}
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");

		Dao dao = sqlSession.getMapper(Dao.class);
		dao.writeDao(request.getParameter("name"), request.getParameter("telno"), request.getParameter("address")
				, request.getParameter("email"), request.getParameter("relation"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request, Model model) {
		System.out.println("content()");

		Dao dao = sqlSession.getMapper(Dao.class);
		model.addAttribute("content", dao.contentDao(request.getParameter("seqno")));
		
		return "/content";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");

		Dao dao = sqlSession.getMapper(Dao.class);
		dao.deleteDao(request.getParameter("seqno"));
		
		return "redirect:list";
	}
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");

		Dao dao = sqlSession.getMapper(Dao.class);
		dao.modifyDao(request.getParameter("name"), request.getParameter("telno"), request.getParameter("address")
				, request.getParameter("email"), request.getParameter("relation"), request.getParameter("seqno"));
		model.addAttribute("content", dao.contentDao(request.getParameter("seqno")));
		
		return "/content";
	}
	
	@RequestMapping("listQuery")
	public String listQuery(HttpServletRequest request, Model model) {
		Dao dao = sqlSession.getMapper(Dao.class);
		model.addAttribute("list", dao.listQuery(request.getParameter("query"), request.getParameter("content")));
		return "/list";
	}
	

}
