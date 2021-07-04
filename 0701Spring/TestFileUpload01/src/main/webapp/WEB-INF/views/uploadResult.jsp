<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<p> 이름 : ${name }</p>
		<p> 파일 : ${file } </p>
		<p>
		<img src="${pageContext.request.contextPath }/resources/test/${file}">
		</p>
</body>
</html>