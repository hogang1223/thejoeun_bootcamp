<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력결과</title>
</head>
<body>

	<h3>입력결과</h3>	
	<%
	String chk = request.getParameter("check");
	
	
	if(chk.equals("0")){
		chk = "Error";	
	}else{
		chk = "Completed";
	}
	%>
	<%= chk %>
</body>
</html>