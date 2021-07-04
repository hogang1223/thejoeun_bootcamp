<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--Encytpe을 반드시 multipart/form-data로 작성  -->
	<form method="post" action="upload" enctype="multipart/form-data">
		<p> 이름 : <input type="text" name="name"> </p>
		<p> 파일 : <input type="file" name="file"> </p>
		<p> <input type="submit" value="upload"> </p>
	</form>
</body>
</html>