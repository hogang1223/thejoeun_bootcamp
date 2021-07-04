<%@page import="com.test.fileupload01.common.FilePath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file upload</title>
</head>
<body>

 	<table border="1">
		<tr>
			<td>id</td>
			<td>name</td>
			<td>image</td>
		</tr>
		<c:forEach items="${list}" var="fileUploadDto">
		<tr>
			<td><a href="detail?id=${fileUploadDto.id}">${fileUploadDto.id}</a></td>
			<td>${fileUploadDto.name}</td>
			<td>
				<img src="${pageContext.request.contextPath }/resources/test/${fileUploadDto.filepath}">
			</td>
		<tr>
		</c:forEach>
	</table>


</body>
</html>