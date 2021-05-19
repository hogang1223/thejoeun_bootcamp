<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="0">
		<form action="modify.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
			<!-- <input type="hidden" name="bId" value="${content_view.bId }"> -->
			<tr>
				<td>번호</td>
				<td><input type="text" name="no" readonly="readonly" value="${content.no }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="writer" szie="20" value="${content.writer }"></td>
			</tr> 
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" szie="50" value="${content.title }"></td>
			</tr> 
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content">${content.content }</textarea></td>
			</tr> 
			<tr>
				<td>첨부파일</td>
				<td>
					<c:set var="requestFilePath" value="${content.filePath }" />
					<c:choose>
					    <c:when test="${!empty requestFilePath}">
					        <a href="${content.filePath }" download>${fileName }</a>
					    </c:when>
					    <c:otherwise>
					        <a>첨부파일 없음</a>
					    </c:otherwise>
					</c:choose>
					<!-- 이미지 수정을 위해서 기존 파일 경로도 전송해야하므로 히든아이템으로 유지한다. -->
					<input type="hidden" name="oldFilePath" value="${content.filePath }">
					<input type="file" name="uploadFile" >
				</td>
			</tr>
			<tr>
				<td>첨부파일 미리보기</td>
				<td><img width="300" src="${content.filePath }"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정"> &nbsp; &nbsp; &nbsp;<a href="list.do?page=${courrentPage}">목록보기</a>
					&nbsp;&nbsp;&nbsp;<a href="delete.do?no=${content.no }">삭제</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>