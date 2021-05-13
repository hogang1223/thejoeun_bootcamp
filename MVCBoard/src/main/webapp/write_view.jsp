<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성</title>
</head>
<script type="text/javascript">
	function insertInfo() {
		alert("Insert Completed");
	}
</script>
<body>
	<table border="0">
		<form action="write.do" method="post">
			<tr>
				<td>이름</td>
				<td><input type="text" name="bName" size="20"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" size="50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="bContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력" onclick="insertInfo()"> &nbsp;&nbsp;&nbsp;
				<a href="list.do">목록보기</a></td>
			</tr>
		</form>
	</table>
</body>
</html>