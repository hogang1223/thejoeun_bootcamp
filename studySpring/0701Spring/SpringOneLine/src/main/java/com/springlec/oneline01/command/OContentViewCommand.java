package com.springlec.oneline01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.oneline01.dao.ODao;
import com.springlec.oneline01.dto.ODto;


public class OContentViewCommand implements OCommand{

	private ODao dao = null;
	@Autowired
	public void setDao(ODao dao) {
		this.dao = dao;
	}
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String oId = request.getParameter("oId");
		ODto dto = dao.contentView(oId);
		
		model.addAttribute("content_view", dto);
		
		
	}
	

}