<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>곱셈</title>
</head>
<body>
<%
	String str1 = request.getParameter("num1");
	String str2 = request.getParameter("num2");
	
	int num1 = Integer.parseInt(str1);
	int num2 = Integer.parseInt(str2);

	out.print("첫번째 수 : "+ str1 + "<br>");
	out.print("두번째 수 : "+ str2 + "<br>");
	
%>
	<form action="ExForward_05.jsp">
		<%=num1 %> x <%=num2 %> = <%=num1 * num2 %>
		<input type="hidden" name="num1" value="<%=num1%>">
		<input type="hidden" name="num2" value="<%=num2%>">
		<input type="submit" value="나눗셈">
	</form>
</body>
</html>