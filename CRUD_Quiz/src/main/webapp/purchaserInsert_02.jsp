<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = request.getParameter("userid");
	String username = request.getParameter("username");
	String usertel = request.getParameter("usertel");
	String useraddress = request.getParameter("useraddress");
	
	session.setAttribute("ID", userid);
	session.setAttribute("NAME", username);
	session.setAttribute("TEL", usertel);
	session.setAttribute("ADD", useraddress);
	

	//-----
	
	String url_mysql = "jdbc:mysql://localhost/customer?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";
	
	PreparedStatement ps = null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		Statement stmt_mysql = conn_mysql.createStatement();
		
		String A = "insert into purchaserinfo (userid, name, tel, address, insertdate";
		String B = ") values (?, ?, ?, ?, now())";
		
		ps = conn_mysql.prepareStatement(A+B);
		ps.setString(1, userid);
		ps.setString(2, username);
		ps.setString(3, usertel);
		ps.setString(4, useraddress);
		ps.executeUpdate();
		
		conn_mysql.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	//-----

	response.sendRedirect("purchaserInsert_03.jsp");
	
	

%>