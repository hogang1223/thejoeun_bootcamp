package com.springlec.base0701.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0701.command.BCommand;
import com.springlec.base0701.command.BContentViewCommand;
import com.springlec.base0701.command.BDeleteCommand;
import com.springlec.base0701.command.BListCommand;
import com.springlec.base0701.command.BModifyCommand;
import com.springlec.base0701.command.BWriteCommand;
import com.springlec.base0701.util.Constant;
@Controller
public class BController {

	private BCommand listCommand = null;
	private BCommand writeCommand = null;
	private BCommand modifyCommand = null;
	private BCommand deleteCommand = null;
	private BCommand contentViewCommand = null;
	
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	
	
	@Autowired
	public void auto(BCommand list, BCommand write, BCommand modify, BCommand delete, BCommand content) {
		this.listCommand = list;
		this.writeCommand = write;
		this.modifyCommand = modify;
		this.deleteCommand = delete;
		this.contentViewCommand = content;
	}

	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
//		command = new BListCommand();
//		command.execute(model);
		listCommand.execute(model);
		
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
//		command = new BWriteCommand();
//		command.execute(model);
		writeCommand.execute(model);
//		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
//		command = new BContentViewCommand();
//		command.execute(model);
		contentViewCommand.execute(model);
		
		return "content_view";
	}
	
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
//		command = new BDeleteCommand();
//		command.execute(model);
		deleteCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
//		command = new BModifyCommand();
//		command.execute(model);
		modifyCommand.execute(model);
		
		return "redirect:list";
	}

}
