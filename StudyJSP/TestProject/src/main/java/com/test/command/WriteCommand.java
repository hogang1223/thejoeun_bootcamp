package com.test.command;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.dao.Dao;

public class WriteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest multi = null;
		String filePath = null;
		String writer = null;
		String title = null;
		String content = null;

		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/save");
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(uploadPath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			writer = multi.getParameter("writer");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			
			String uploadFile = multi.getFilesystemName("uploadFile");
			Dao dao = new Dao();
			filePath = "save/" + uploadFile;
			dao.write(writer, title, content, filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
