package com.springlec.add01.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springlec.add01.dao.Dao;

public class ListCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		
		Dao dao = sqlSession.getMapper(Dao.class);
		model.addAttribute("list", dao.listDao());
	}

}
