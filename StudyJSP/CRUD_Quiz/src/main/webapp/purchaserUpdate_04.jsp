<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 정보 확인</title>
</head>
<body>
	<table>
		<tr>
			<td align="right">사용자 ID : </td>
			<td>${ID }</td>
		</tr>
			<tr>
			<td align="right">성명 : </td>
			<td>${NAME }</td>
		</tr>
		<tr>
			<td align="right">전화번호 : </td>
			<td>${TEL }</td>
		</tr>
		<tr>
			<td align="right">주소 : </td>
			<td>${ADD }</td>
		</tr>		
	</table>
	<%
		session.invalidate();
	%>
	<br>
	<p>상기의 정보로 수정되었습니다. 감사합니다.</p>
	<a href="purchaserSelect_01.jsp">처음으로</a>
</body>
</html>