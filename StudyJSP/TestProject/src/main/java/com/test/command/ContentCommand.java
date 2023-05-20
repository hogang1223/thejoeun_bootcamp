package com.test.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.FilePath;
import com.test.dao.Dao;
import com.test.dto.Dto;

public class ContentCommand implements Command {
	
	FilePath  cv = new FilePath();

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));

		Dao dao = new Dao();
		Dto dto = dao.content(no);
		
		//파일이 업로드되있는 상태라면 (이름+경로) - 경로를 해서 파일 이름만 추출해서 request로 전송
		if(dto.getFilePath() != null) {
			String filePath = dto.getFilePath();
			String fileName = filePath.substring(cv.YMboard.length() + 1);
			request.setAttribute("fileName", fileName);
		}
		request.setAttribute("content", dto);
	}

}
