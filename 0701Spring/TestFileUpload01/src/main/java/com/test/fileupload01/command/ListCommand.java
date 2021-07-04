package com.test.fileupload01.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.test.fileupload01.dao.FileUploadDao;

public class ListCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);
		model.addAttribute("list", dao.listDao());
	}

}
