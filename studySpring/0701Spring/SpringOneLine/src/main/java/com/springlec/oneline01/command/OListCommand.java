package com.springlec.oneline01.command;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.oneline01.dao.ODao;
import com.springlec.oneline01.dto.ODto;

public class OListCommand implements OCommand{
	
	
	private ODao dao = null;
	@Autowired
	public void setDao(ODao dao) {
		this.dao = dao;
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		ArrayList<ODto> dtos = dao.list();
		model.addAttribute("list", dtos);
		
	}

}
