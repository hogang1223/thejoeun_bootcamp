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
		
		// Empty Check
		var form = document.loginForm
		if(form.id.value == ""){
			alert("please input your id");
			form.id.focus();
			return false;
		}else if(form.pw.value == ""){
			alert("please input your password");
			form.pw.focus();
			return false;
		}
		
		// Length Check
		if(form.id.value.length < 4 || form.id.value.length > 12){
			alert("아이디는 4~12자 이내로 입력 가능합니다.");
			form.id.select();
			return false;
		}
		if(form.pw.value.length < 4){
			alert("비밀번호는 4자 이상으로 입력 해야 합니다.");
			form.pw.select();
			return false;
		}
		
		form.submit();
	}

</script>
<body>

	<form name="loginForm" action="Validation03_process.jsp" method="post">
		<p>아이디 : <input type="text" name="id"></p>
		<p>비밀번호 : <input type="password" name="pw"></p>
		<p><input type="button" value="전송" onclick="checkLogin()"></p>
	</form>

</body>
</html>