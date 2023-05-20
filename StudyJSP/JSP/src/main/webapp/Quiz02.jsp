<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 2단</title>
</head>
<body>

	<%
		int i = 1;
		while(i<=9){
			out.println("2 x " + i + " = " + (2*i) + "<br> ============== <br>");
			i++;
		}
	%>
</body>
</html>