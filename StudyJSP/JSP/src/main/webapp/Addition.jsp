<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Addition</title>
</head>
<body>

	<%
		int a = 30;
		int b = 20;
		int addition = a + b;
		int sub = a - b;
		int mul = a * b;
		double div = (double)a / b;
		int di_n = a % b;
		
		out.println(a + "+" + b + "=" + (a+b));
	%>
	<br>
	<%=a %> + <%=b %> = <%=addition %> <br>
	<%=a %> - <%=b %> = <%=sub %> <br>
	<%=a %> * <%=b %> = <%=mul %> <br>
	<%=a %> / <%=b %> = <%=div %> <br>
	<%=a %> % <%=b %> = <%=di_n %> <br>

</body>
</html>