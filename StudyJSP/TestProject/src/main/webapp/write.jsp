<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="0">
		<form action="write.do" method="post" enctype="multipart/form-data">
			<tr>
				<td>이름</td>
				<td><input type="text" name="writer" size="20"></td>
			</tr> 
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="50"></td>
			</tr> 
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr> 
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="uploadFile" ></td>
			</tr> 
			<tr><td><input type="submit" value="OK"></td></tr>
		</form>
	</table>
</body>
</html>