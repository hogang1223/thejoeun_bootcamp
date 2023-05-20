package com.springlec.oneline01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.oneline01.command.OCommand;
import com.springlec.oneline01.util.Constant;


@Controller
public class OController {

	private OCommand listCommand = null;
	private OCommand writeCommand = null;
	private OCommand modifyCommand = null;
	private OCommand deleteCommand = null;
	private OCommand contentViewCommand = null;
	
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@Autowired
	public void auto(OCommand list, OCommand write, OCommand modify, OCommand delete, OCommand content) {
		this.listCommand = list;
		this.writeCommand = write;
		this.modifyCommand = modify;
		this.deleteCommand = delete;
		this.contentViewCommand = content;
	}

	
	@RequestMapping("/list")
	public String list(Model model) {
		listCommand.execute(model);
		System.out.println("list()");		
		return "list";
	}
	
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		writeCommand.execute(model);
//		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		contentViewCommand.execute(model);
		
		return "content_view";
	}
	
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		deleteCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		modifyCommand.execute(model);
		
		return "redirect:list";
	}
	
	
}
