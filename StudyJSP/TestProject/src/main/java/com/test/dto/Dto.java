package com.test.dto;

import java.sql.Timestamp;

public class Dto {
	int no;
	String title;
	String writer;
	String content;
	Timestamp date;
	String filePath;

	public Dto() {

	}

	public Dto(String title, String writer, String content, String filePath) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.filePath = filePath;
	}

	public Dto(int no, String title, String writer, Timestamp date) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.date = date;
	}

	public Dto(int no, String title, String writer, String content, Timestamp date, String filePath) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.filePath = filePath;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
