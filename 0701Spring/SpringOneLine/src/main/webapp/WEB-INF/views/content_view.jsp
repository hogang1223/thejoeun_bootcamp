<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>One Line board</title>
</head>
<script type="text/javascript">
	function modifyInfo() {
		alert("Modify Completed");
	}
</script>
<body>
	
	<table border="1">
		<form action="modify" method="post">
			<tr>
				<td>번호</td>
				<td><input type="text" name="oId" size="20" value="${content_view.oId }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="oName" size="20" value="${content_view.oName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="oTitle" size="50" value="${content_view.oTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="oContent" rows="10">${content_view.oContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정" onclick="modifyInfo()">&nbsp;&nbsp;&nbsp;
				<a href="list">목록보기</a></td>
			</tr>
		</form>
	</table>
</body>
</html>