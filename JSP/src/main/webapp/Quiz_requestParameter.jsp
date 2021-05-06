<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산 결과</title>
</head>
<body>
	
	<%

		int addResult, mulResult;
		request.setCharacterEncoding("utf-8");
		
		addResult =Integer.parseInt(request.getParameter("addNum1")) 
				+ Integer.parseInt(request.getParameter("addNum2"));
		
		mulResult =Integer.parseInt(request.getParameter("mulNum1")) 
				* Integer.parseInt(request.getParameter("mulNum2"));
		
		
	%>	
	add Result : <%=addResult %><br>
	mul Result : <%=mulResult %><br>
	
</body>
</html>