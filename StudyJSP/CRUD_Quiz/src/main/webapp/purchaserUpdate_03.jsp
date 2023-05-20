<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String username = request.getParameter("username");
	String usertel = request.getParameter("usertel");
	String useraddress = request.getParameter("useraddress");

	//-----
	
	String url_mysql = "jdbc:mysql://localhost/customer?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";
	
	PreparedStatement ps = null;
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		Statement stmt_mysql = conn_mysql.createStatement();
		
		String A = "update purchaserinfo set name = ?, tel = ?, address = ? where userid = ? ";
		
		ps = conn_mysql.prepareStatement(A);
		ps.setString(1, username);
		ps.setString(2, usertel);
		ps.setString(3, useraddress);
		ps.setString(4, (String)session.getAttribute("ID"));
		ps.executeUpdate();
		
		conn_mysql.close();
		
		session.setAttribute("NAME", username);
		session.setAttribute("TEL", usertel);
		session.setAttribute("ADD", useraddress);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	//-----

	response.sendRedirect("purchaserUpdate_04.jsp");
	
	

%>

