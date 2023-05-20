package com.springlec.add01.dao;

import java.util.ArrayList;

import com.springlec.add01.dto.Dto;

public interface Dao {

	// 전체 검색
	public ArrayList<Dto> listDao();
	
	// 입력
	public void writeDao(String name, String telno, String address, String email, String relation);
	
	// 상세보기
	public Dto contentDao(String seqno);
	
	// 삭제
	public void deleteDao(String seqno);
	
	// 수정
	public void modifyDao(String name, String telno, String address, String email, String relation, String seqno);
	
	// 조건 검색
	public ArrayList<Dto> listQuery(String query, String content);
	
}
