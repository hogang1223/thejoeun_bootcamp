package com.test.fileupload01.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.fileupload01.command.Command;
import com.test.fileupload01.command.ListCommand;
import com.test.fileupload01.common.FilePath;
import com.test.fileupload01.dao.FileUploadDao;

@Controller
public class FileUploadController {
//	@Autowired
//	FileUploadService fileuploadService;
	
	@Autowired
	private SqlSession sqlSession;
	
	Command command = null;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		
		model.addAttribute("request", request);
		command = new ListCommand();
		command.execute(sqlSession, model);
		
		return "/list";
	}
	
	// uploadForm 보여주기
	@RequestMapping("/uploadForm")
	public String uploadForm() {
	
		return "uploadForm";
	}
	
	// upload하기 - File 지정 폴더에 저장 and DB에 insert
	@RequestMapping("upload")
	public String upload(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("upload()");
		
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
        }
        
        FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);
        dao.writeDao(mtfRequest.getParameter("name"), saveFilename);
        model.addAttribute("name", mtfRequest.getParameter("name"));
        model.addAttribute("imgfile", saveFilename);
        

		return "uploadResult"; 
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		System.out.println("detail()");
		
		FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);
		model.addAttribute("detail", dao.detailDao(request.getParameter("id")));
		
		return "/detail";
	}
	
	@RequestMapping("modify")
	public String modify(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("modify()");
		
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
        
        FileUploadDao dao = sqlSession.getMapper(FileUploadDao.class);
        dao.modifyDao(mtfRequest.getParameter("name"), saveFilename, mtfRequest.getParameter("id"));
		
		
		return "redirect:list";
	}

}
