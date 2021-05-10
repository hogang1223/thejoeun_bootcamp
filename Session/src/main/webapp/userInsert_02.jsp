<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 관리에 대한 약관</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String userId = request.getParameter("userId");
	String userPw = request.getParameter("userPw");
	String userName = request.getParameter("userName");
	
	session.setAttribute("ID", userId);
	session.setAttribute("PW", userPw);
	session.setAttribute("NAME", userName);
	
%>

	<h2>약관</h2>
	<p>
	-----------------------------------------------------<br>
	1. 회원 정보는 웹사이트의 운영을 위해서만 사용 됩니다.<br>
	2.웹사이트의 정상 운영을 방해하는 회원은 탈퇴 처리 합니다.<br>
	-----------------------------------------------------<br>
	위의 약관에 동의 하십니까?</p><br>
	
	<form action="userInsert_03.jsp">
	<input type="radio" name="admit" value="true" checked="checked"> 동의 함
	<input type="radio" name="admit" value="false"> 동의 하지 않음
	<br><br>
	<input type="submit" value="확인">
	
	</form>
</body>
</html>