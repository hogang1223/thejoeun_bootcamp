package com.test.fileupload01.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.test.fileupload01.dao.FileUploadDao;

public class ListCommand implements Command {

	int numOfTuplesPerPage = 3;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
	
		int requestPage = 1;
		FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		if(request.getParameter("page") != null) {
			requestPage = Integer.parseInt(request.getParameter("page"));
		}
		
		
		int countedTuple = dao.listCountDao();
		ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
		model.addAttribute("pageList", pageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= numOfTuplesPerPage;
		}
		
		model.addAttribute("list", dao.listDao(offset, numOfTuplesPerPage));
	}
	
	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> qnaArr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % numOfTuplesPerPage == 0) {
			calcPage = countedTuple / numOfTuplesPerPage;
		} else {
			calcPage = countedTuple / numOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			qnaArr.add(i);
		}
		
		return qnaArr;
	}

}
