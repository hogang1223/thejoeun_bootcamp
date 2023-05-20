<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 저장 내용</title>
</head>
<body>
<%
	BufferedReader reader = null;
	try{
		String filePath = application.getRealPath("quiz.txt");
		reader = new BufferedReader(new FileReader(filePath));
		while(true){
			String str = reader.readLine();
			if(str == null) break;
			out.print(str + "<br>");
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
</body>
</html>