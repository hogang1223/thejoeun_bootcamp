<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 정보 확인</title>
</head>
<body>
	<table>
		<tr>
			<td align="right">사용자 ID : </td>
			<td>${ID }</td>
		</tr>		
	</table>
	<%
		session.invalidate();
	%>
	<br>
	<p>상기의 정보가 삭제되었습니다. 감사합니다.</p>
	<a href="purchaserSelect_01.jsp">처음으로</a>
</body>
</html>