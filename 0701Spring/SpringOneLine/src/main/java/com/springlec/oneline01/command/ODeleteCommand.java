package com.springlec.oneline01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.oneline01.dao.ODao;


public class ODeleteCommand implements OCommand{
	
	private ODao dao = null;
	@Autowired
	public void setDao(ODao dao) {
		this.dao = dao;
	}

	@Override
	public void execute(Model model) {
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			
			String oId = request.getParameter("oId");
			dao.delete(oId);
			
	}
}