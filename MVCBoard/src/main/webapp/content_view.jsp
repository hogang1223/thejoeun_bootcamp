<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내용 확인</title>
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
		<table border="0">
		<form action="modify.do" method="post">
			<tr>
				<td>번호</td>
				<td><input type="text" name="bId" value="${content_view.bId }" readonly="readonly"></td>
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
				<td><textarea rows="10" cols="50" name="bContent">${content_view.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정" onclick="modifyInfo()">&nbsp;&nbsp;&nbsp;
				<a href="list.do">목록보기</a>&nbsp;&nbsp;&nbsp;
				<a href="delete.do?bId=${content_view.bId }" onclick="deleteInfo()">삭제</a></td>
			</tr>
		</form>
	</table>

</body>
</html>