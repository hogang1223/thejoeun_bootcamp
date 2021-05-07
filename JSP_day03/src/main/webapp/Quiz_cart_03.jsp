<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 선택 저장 결과</title>
</head>
<body>
	<h2>상품 선택 저장 결과</h2>
	
	<%
		String str = request.getParameter("result");
		if(str.equals("success")){
			out.print("저장되었습니다.");
		}else{
			out.print("파일에 문제가 발생했습니다.");
		}
	%>
	<form action="Quiz_cart_04.jsp">
	
	<input type="submit" value="저장 결과 불러오기">
	</form>
	
</body>
</html>