<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Board</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
		</tr>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.no }</td>
				<td>${dto.writer }</td>
				<td><a href="content.do?no=${dto.no }">${dto.title }</a></td>
				<td>${dto.date }</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="4" align="center">
					<!-- 페이징 부분 -->
					<c:forEach items="${pageList }" var="page">
						<a href="list.do?page=${page }">${page}</a>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right"><a href="write_enter.do">글작성</a></td>
			</tr>
	</table>
</body>
</html>