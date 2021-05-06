<%@page import="java.net.URLEncoder"%>
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
		request.setCharacterEncoding("utf-8");
	
		int age ;
		String adult , possible;
		age = Integer.parseInt(request.getParameter("age"));

		if(age>19){
			adult = URLEncoder.encode("성인");
			possible = URLEncoder.encode("가능");
		}else{
			adult = URLEncoder.encode("미성년자");
			possible = URLEncoder.encode("불가능");
			
		}
/* 			out.print(adult+possible); */
 			response.sendRedirect("ResponseAge_07.jsp?age="+age+"&adult="+adult+"&possible="+possible);
		
	%>


</body>
</html>