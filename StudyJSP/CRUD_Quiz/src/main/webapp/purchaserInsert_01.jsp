<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Page</title>
</head>

<body>
	<h3>아래의 항목을 입력후 확인 버튼을 누르세요!</h3>
	<form name="info" action="purchaserInsert_02.jsp">
	<table>
	<tr>
	<td align="right">사용자 ID : </td>
	<td><input type="text" name="userid"></td>
	</tr>
	<tr>
	<td align="right">성명 : </td>
	<td><input type="text" name="username"></td>
	</tr>
	<tr>
	<td align="right">전화번호 : </td>
	<td><input type="text" name="usertel"></td>
	</tr>
	<tr>
	<td align="right">주소 : </td>
	<td><input type="text" name="useraddress"></td>
	</tr>
	<tr>
	<td><br><br></td>
	<td><br><br><input type="submit" value="확인"></td>
	</tr>
	</table>
	</form>
</body>
</html>