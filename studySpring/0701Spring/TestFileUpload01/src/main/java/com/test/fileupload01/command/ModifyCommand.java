package com.test.fileupload01.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.fileupload01.dao.FileUploadDao;

public class ModifyCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub

		
		FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfRequest");
	
		HttpSession session = mtfRequest.getSession(); 
		String root_path = session.getServletContext().getRealPath("/"); // 웹서비스 root 경로 
		String attach_path = "resources/test/";
		
		String uploadPath = root_path + attach_path;
		System.out.println(uploadPath);
		
		// 폴더가 없을시 폴더 생성
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
        
        String saveFilename = null;
        
        // upload File
        MultipartFile mf = mtfRequest.getFile("imgfile");
    	String originFileName = mf.getOriginalFilename(); // 원본 파일 명
        long fileSize = mf.getSize(); // 파일 사이즈
        
        // file upload check
        if(fileSize != 0) {
	        saveFilename = System.currentTimeMillis() + originFileName; // 저장될 파일명
	        String saveFile = uploadPath + saveFilename;
	        
	        try {
	        	mf.transferTo(new File(saveFile));
	        }catch (IllegalStateException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	           e.printStackTrace();
	        }
        }else {
        	saveFilename = mtfRequest.getParameter("oldFilepath");
        }
		
        dao.modifyDao(mtfRequest.getParameter("name"), saveFilename, mtfRequest.getParameter("id"));
		
	}

}
