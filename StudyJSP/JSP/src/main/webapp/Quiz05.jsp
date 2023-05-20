<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2의 거듭제곱</title>
</head>
<body>

<%-- 	2 ^ 1 = <%=pow(2,1) %><br>
	2 ^ 2 = <%=pow(2,2) %><br>
	2 ^ 3 = <%=pow(2,3) %><br>
	2 ^ 4 = <%=pow(2,4) %><br>
	2 ^ 5 = <%=pow(2,5) %><br> --%>

<%
	for(int i = 1; i<=5; i++){
		out.println("2 ^ " + i + " = " + pow(2,i) + "<br>");
	}
	out.print("-------------<br>");
%>

<%
	// 지시자
	int [] iArr = {10, 20, 30};
	out.print(Arrays.toString(iArr));
%>


</body>
</html>

<%!
	private int pow(double a, double b){
		int result = (int)Math.pow(a,b);
		return result;
	}
%>