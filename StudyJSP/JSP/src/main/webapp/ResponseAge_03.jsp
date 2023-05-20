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
		String age = request.getParameter("age");
	%>
	<h1>성인</h1>
	<p>당신의 나이는 <%=age %>살 이므로 주류 구매가 가능 합니다.</p>
	<a href="ResponseAge_01.jsp">처음으로 이동</a>

</body>
</html>