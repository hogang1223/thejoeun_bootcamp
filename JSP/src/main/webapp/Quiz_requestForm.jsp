<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자 입력</title>
</head>
<body>
	<form action="Quiz_requestParameter.jsp" method="get">

	<table>
	<tr>
		<td><input type="text" name="addNum1" size="10"></td>
		<td> + </td> 
		<td><input type="text" name="addNum2" size="10"></td>
	</tr>
	<tr>
		<td>
		<select name="mulNum1">
			<%
				for(int i = 1; i<1000; i++){
				out.print("<option value = " + i + "> "+ i + "</option>");
				}
			%>
		</select>
		</td>
		<td> x </td> 
		<td>
		<select name="mulNum2">
			<%
				for(int i = 1; i<1000; i++){
				out.print("<option value = " + i + "> "+ i + "</option>");
				}
			%>
		</select>
		</td>
	</tr>
	
	
	<tr>
	<td col="3"><input type="submit" value="OK"></td>
	</tr>
	
	</table>
	
	</form>
</body>
</html>