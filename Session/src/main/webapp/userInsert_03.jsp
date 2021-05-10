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
	String admit = request.getParameter("admit");
	
	if(admit.equals("true")){
		response.sendRedirect("userInsert_04.jsp");
	}else{
%>
	<script type="text/javascript">
		alert('동의함을 클릭해주세요.');
		document.location.href="userInsert_02.jsp" ;
	</script>
	
	<%
	}
	%>
</body>
</html>