<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int age ;
		age = Integer.parseInt(request.getParameter("age"));
		
		
		if(age>19){
			response.sendRedirect("ResponseAge_03.jsp?age="+age);
		}else{
			response.sendRedirect("ResponseAge_04.jsp?age="+age);
		}
		
	%>


</body>
</html>