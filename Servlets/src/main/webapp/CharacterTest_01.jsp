<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성격 테스트</title>
</head>
<body>
	<form action="CharacterTest_01S">
	
	<h2>성격 테스트</h2>
	<p>당신의 성격을 테스트 합니다. 데이터를 입력한 후 확인 버튼을 눌러주세요.</p>
	<p>이름은? <input type="text" name="name"> </p>
	<p>좋아하는 색은? 노랑<input type="radio" name="color" value="노랑색" checked="checked">
				빨강<input type="radio" name="color" value="빨강색">
				파랑<input type="radio" name="color" value="파랑색"></p>
	<p>좋아하는 동물은? <select name="animal">
			<option value="거북이">거북이
			<option value="토끼">토끼
			<option value="호랑이">호랑이
	</select></p>
	<p>좋아하는 음식은? (모두 고르세요)
	짜장면<input type="checkbox" name="food" value="짜장면" checked="checked">
	짬뽕<input type="checkbox" name="food" value="짬뽕">
	탕수육<input type="checkbox" name="food" value="탕수육"></p>
	<p><input type="submit" value="확인">
	</p>		
	
	
	</form>
</body>
</html>