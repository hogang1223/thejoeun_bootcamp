package com.test.fileupload01.dao;

import java.util.ArrayList;

import com.test.fileupload01.dto.FileUploadDto;

public interface FileUploadDao {
	
	// 전체 검색
	public ArrayList<FileUploadDto> listDao();
	
	// 입력
	public void writeDao(String name, String filepath);
		

}
