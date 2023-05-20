<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미성년자 확인</title>
</head>
<body>
	<%
		String age = request.getParameter("age");
	%>
	<h1>미성년자</h1>
	<p>당신의 나이는 <%=age %>살 이므로 주류 구매가 불가능 합니다.</p>
	<a href="ResponseAge_01.jsp">처음으로 이동</a>
</body>
</html>