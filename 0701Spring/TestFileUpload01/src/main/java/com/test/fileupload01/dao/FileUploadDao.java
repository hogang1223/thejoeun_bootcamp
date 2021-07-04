package com.test.fileupload01.dao;

import java.util.ArrayList;

import com.test.fileupload01.dto.FileUploadDto;

public interface FileUploadDao {
	
	// 전체 검색
	public ArrayList<FileUploadDto> listDao(int requestPage, int listCount);
	
	// 게시물 수 확인
	public int listCountDao();
	
	// 입력
	public void writeDao(String name, String filepath);
	
	// 상세보기
	public FileUploadDto detailDao(String id);
	
	// 수정
	public void modifyDao(String name, String filepath, String id);
		

}
