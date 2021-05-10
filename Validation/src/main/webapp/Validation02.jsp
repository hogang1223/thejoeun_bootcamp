<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function checkLogin() {
		var form = document.loginForm
		if(form.id.value == ""){
			alert("please input your id")
			form.id.focus();
			return false;
		}else if(form.pw.value == ""){
			alert("please input your password")
			form.passwd.focus();
			return false;
		}
		form.submit();
	}

</script>
<body>

	<form name="loginForm" action="Validation02_process.jsp" method="post">
		<p>아이디 : <input type="text" name="id"></p>
		<p>비밀번호 : <input type="password" name="pw"></p>
		<p><input type="button" value="전송" onclick="checkLogin()"></p>
	</form>

</body>
</html>