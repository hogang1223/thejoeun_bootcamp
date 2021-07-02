<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis 주소록</title>
</head>
<body>
	<table border="1">
		<form action="modify" method="post">
			<tr>
				<td>번호</td>
				<td><input type="text" name="seqno" size = "20" value="${content.seqno }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size = "20" value="${content.name}"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td> <input type="text" name="telno" size = "70" value="${content.telno}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td> <input type="text" name="address" size = "70" value="${content.address}"> </td>
			</tr>
			<tr>
				<td>전자우편</td>
				<td> <input type="text" name="email" size = "70" value="${content.email}"> </td>
			</tr>
			<tr>
				<td>관계</td>
				<td> <input type="text" name="relation" size = "70" value="${content.relation}"> </td>
			</tr>
			<tr >
				<td colspan="2"><input type="submit" value="수정"> &nbsp;&nbsp; <a href="list">목록보기</a>&nbsp;&nbsp; <a href="delete?seqno=${content.seqno }">삭제</a></td>
			</tr>
		</form>
	</table>

</body>
</html>