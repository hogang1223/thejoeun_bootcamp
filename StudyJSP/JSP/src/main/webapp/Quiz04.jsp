<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1부터 200까지의 합</title>
</head>
<body>
	<%
		int i = 1;
		int sum = 0;
		
		while(i<=100){
			sum += i;
			i++;			
		}
			out.println("1부터 100까지의 합 = " + sum);
 		while(i<=200){
			sum +=i;
			i++;
		}
		
		out.println("<br>1부터 200까지의 합 = " + sum); 
		
		
	%>
	
</body>
</html>