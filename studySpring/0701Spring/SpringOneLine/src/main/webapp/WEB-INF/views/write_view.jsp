<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>One Line board</title>
</head>
<body>
	
	<table border="1">
		<form action="write" method="post">
			<tr>
				<td>이름</td>
				<td><input type="text" name="oName" size="20"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="oTitle" size="50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="oContent" rows="10"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="전송">&nbsp;&nbsp;<a href="list">목록</a></td>
			</tr>
		</form>
	</table>
</body>
</html>