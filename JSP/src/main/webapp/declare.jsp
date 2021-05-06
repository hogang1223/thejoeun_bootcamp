<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언부</title>
</head>
<body>
<%
	int i = 10;
	String str = "I have a dream.";
	
	out.println("i = " + i + "<br>");	
	out.println("str = " + str + "<br>");
	out.println("sum = " + sum(12,15) + "<br>");
	
%>

</body>
</html>
<%! 
	// 선언자는 스크립트 내에서 사용 불가 (메소드는 따로 작성)
	// 보통 메소드는 html 아래에 작성
	public int sum(int a, int b){
		return a + b;
	}
%>