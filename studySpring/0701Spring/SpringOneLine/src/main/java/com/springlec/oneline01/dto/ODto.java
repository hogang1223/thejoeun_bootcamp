package com.springlec.oneline01.dto;

import java.sql.Timestamp;

public class ODto {

	int oId;
	String oName;
	String oTitle;
	String oContent;
	Timestamp oDate;
	
	public ODto() {
		// TODO Auto-generated constructor stub
	}

	public ODto(int oId, String oName, String oTitle, String oContent, Timestamp oDate) {
		super();
		this.oId = oId;
		this.oName = oName;
		this.oTitle = oTitle;
		this.oContent = oContent;
		this.oDate = oDate;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoTitle() {
		return oTitle;
	}

	public void setoTitle(String oTitle) {
		this.oTitle = oTitle;
	}

	public String getoContent() {
		return oContent;
	}

	public void setoContent(String oContent) {
		this.oContent = oContent;
	}

	public Timestamp getoDate() {
		return oDate;
	}

	public void setoDate(Timestamp oDate) {
		this.oDate = oDate;
	}
	
	
	
	

}
