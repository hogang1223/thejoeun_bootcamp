<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function modifyInfo() {
		alert("Modify Completed");
	}
	function deleteInfo() {
		alert("Delete Completed");
	}
</script>
<body>
	
	<table border="1">
		<form action="modify" method="post">
			<tr>
				<td>번호</td>
				<td><input type="text" name="bId" size="20" value="${content_view.bId }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="bName" size="20" value="${content_view.bName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" size="50" value="${content_view.bTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="bContent" rows="10">${content_view.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정" onclick="modifyInfo()">&nbsp;&nbsp;&nbsp;
				<a href="list">목록보기</a>&nbsp;&nbsp;&nbsp;
				<a href="delete?bId=${content_view.bId }" onclick="deleteInfo()">삭제</a></td>
			</tr>
		</form>
	</table>
</body>
</html>