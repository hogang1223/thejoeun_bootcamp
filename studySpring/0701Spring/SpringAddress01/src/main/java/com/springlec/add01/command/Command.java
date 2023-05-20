package com.springlec.add01.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface Command {
	
	void execute(SqlSession sqlSession, Model model);

}
