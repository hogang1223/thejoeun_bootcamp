<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis 주소록 리스트 : 조건</title>
</head>
<body>
	<h3>MyBatis 주소록 리스트</h3>
	
	<form action="listQuery" method="post">
		<p>검색선택 : 
		<select name="query">
			<option value="ADDRESS">주소</option>
			<option value="RELATION">관계</option>
		</select> &nbsp;&nbsp;&nbsp;
		<input type="text" name="content" size="30">
		<input type="submit" value="검색">
		</p>
	</form>
	
	<table border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
			<td>전자우편</td>
			<td>관계</td>
		</tr>
		<c:set var="cnt" value="0"/>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td><a href="content?seqno=${dto.seqno }">${dto.seqno}</a></td>
			<td>${dto.name}</td>
			<td>${dto.telno}</td>
			<td>${dto.address}</td>
			<td>${dto.email}</td>
			<td>${dto.relation}</td>
		<tr>
		<c:set var="cnt" value="${cnt=cnt+1 }" />
		</c:forEach>
	</table>
	<p>검색 결과는 <b>${cnt }</b>명 입니다.</p>
	
	<p><a href="writeForm">주소록 등록</a></p>

</body>
</html>