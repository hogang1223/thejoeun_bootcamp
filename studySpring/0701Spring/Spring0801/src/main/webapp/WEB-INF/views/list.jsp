<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="500" cellpadding="0" cellspacing="0" border="1">
	<tr>
		<td>번호</td>
		<td>작성자</td>
		<td>내용</td>
		<td>삭제</td>
	<tr>
	<c:forEach items="${list}" var="contentDto">
	<tr>
		<td>${contentDto.mId}</td>
		<td>${contentDto.mWriter}</td>
		<td>${contentDto.mContent}</td>
		<td><a href="delete?mId=${contentDto.mId}">X</a></td>
	<tr>
	</c:forEach>
</table>
<p><a href="writeForm">글작성</a></p>
</body>
</html>