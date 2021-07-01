package com.springlec.oneline01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.springlec.oneline01.dto.ODto;
import com.springlec.oneline01.util.Constant;

public class ODao {

	JdbcTemplate template;
	public ODao() {
		// TODO Auto-generated constructor stub
		this.template = Constant.template;

	}
	
	// 초기화면(검색)
	public ArrayList<ODto> list(){
		String query = "select oId, oName, oTitle, oContent, oDate from oneline";
		System.out.println(query);
		return (ArrayList<ODto>) template.query(query, new BeanPropertyRowMapper<ODto>(ODto.class));
		
	}
	

	public void write(final String oName, final String oTitle, final String oContent) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into oneline (oName, oTitle, oContent, oDate) values (?,?,?,now())";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, oName);
				preparedStatement.setString(2, oTitle);
				preparedStatement.setString(3, oContent);
				
				return preparedStatement;
			}
		});
		
	}
	
	// content view
	public ODto contentView(String strId) {
		String query = "SELECT * FROM oneline where oId =" + strId;
		return template.queryForObject(query,  new BeanPropertyRowMapper<ODto>(ODto.class));

	}
	
	
	public void delete(final String strId) {
		
		String query = "DELETE FROM oneline where oId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(strId));
			}
		});

	}
	
	
	public void modify(final String oId, final String oName, final String oTitle, final String oContent) {
		
		String query = "UPDATE oneline SET oName = ? , oTitle = ?, oContent = ?, oDate = now() WHERE oId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, oName);
				ps.setString(2, oTitle);
				ps.setString(3, oContent);
				ps.setInt(4, Integer.parseInt(oId));

				
			}
		});

	}
	
} // end ODao
