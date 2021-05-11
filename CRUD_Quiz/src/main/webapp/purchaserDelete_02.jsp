<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 삭제</title>
</head>
<body>
	<h4>삭제할 고객정보를 확인 후 버튼을 누르세요</h4>
	
<%
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String username = null;
	String usertel = null;
	String useraddress = null;

	//-----
	
	String url_mysql = "jdbc:mysql://localhost/customer?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";
	
	String whereStatement = "select name, tel, address from purchaserinfo where userid = '" + userid + "'";
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		Statement stmt_mysql = conn_mysql.createStatement();
		
		ResultSet rs = stmt_mysql.executeQuery(whereStatement);
		while(rs.next()){
			username = rs.getString(1);
			usertel = rs.getString(2);
			useraddress = rs.getString(3);
		}
		conn_mysql.close();
		
		session.setAttribute("ID", userid);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
%>
	
	<form action="purchaserDelete_03.jsp" method="post">
	<table>
		<tr>
		<td align="right">사용자 ID : </td>
		<td><input type="text" name="userid" value="<%=userid%>"></td>
		</tr>
		<tr>
		<td align="right">성명 : </td>
		<td><input type="text" name="username" value="<%=username%>"></td>
		</tr>
		<tr>
		<td align="right">전화번호 : </td>
		<td><input type="text" name="usertel" value="<%=usertel%>"></td>
		</tr>
		<tr>
		<td align="right">주소 : </td>
		<td><input type="text" name="useraddress" value="<%=useraddress%>"></td>
		</tr>
		<tr>
		<td><br><br></td>
		<td><br><br><input type="submit" value="확인"></td>
		</tr>
	</table>
	</form>
</body>
</html>