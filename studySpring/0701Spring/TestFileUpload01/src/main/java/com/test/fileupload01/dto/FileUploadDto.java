package com.test.fileupload01.dto;

public class FileUploadDto {
	
	int id;
	String name;
	String filepath;
	
	public FileUploadDto() {
		// TODO Auto-generated constructor stub
	}

	public FileUploadDto(int id, String name, String filepath) {
		super();
		this.id = id;
		this.name = name;
		this.filepath = filepath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	

}
