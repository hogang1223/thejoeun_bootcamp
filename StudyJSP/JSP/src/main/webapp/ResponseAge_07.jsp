<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성인 확인</title>
</head>
<body>
	<%
		String age = URLDecoder.decode(request.getParameter("age"));
		String adult = URLDecoder.decode(request.getParameter("adult"));
		String possible = URLDecoder.decode(request.getParameter("possible"));
	%>
	<h1><%=adult %></h1>
	<p>당신의 나이는 <%=age %>살 이므로 주류 구매가 <%=possible %>합니다.</p>
	<a href="ResponseAge_05.jsp">처음으로 이동</a>

</body>
</html>