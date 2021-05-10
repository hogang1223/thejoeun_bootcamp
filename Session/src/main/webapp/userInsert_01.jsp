<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 입력</title>
</head>
<body>
	<h2>개인 정보를 입력하세요.</h2>
	
	<form action="userInsert_02.jsp">
	
	<table>
	<tr>
		<td>아이디 :</td>
		<td><input type="text" name="userId"></td>
	</tr>
	<tr>
		<td>패스워드 : </td>
		<td><input type="password" name="userPw"></td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td><input type="text" name="userName"></td>
	</tr>
	</table>
	<br><br>
	<input type="submit" value="확인">	
	</form>
	
	
</body>
</html>