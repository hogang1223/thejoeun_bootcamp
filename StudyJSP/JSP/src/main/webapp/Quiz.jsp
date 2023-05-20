<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단(2단)</title>
</head>
<body>
	<%
		for(int i=1; i<=9; i++){
			out.println("2 x " + i + " = " + (2*i));
	%>	
		<br>
	<%
			out.println("==================");
	%>
		<br>
	<%
		}
	%>
	
	
	
</body>
</html>