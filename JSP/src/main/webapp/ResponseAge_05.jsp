<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나이 입력</title>
</head>
<body>

	당신의 나이는 ? 
	<br>
	<form action="ResponseAge_06.jsp"> <!--method를 작성하지 않을 경우 default는 get방식 -->
		<input type="text" name="age" size="5">
		<input type="submit" value="확인">		
	</form>
</body>
</html>