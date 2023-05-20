<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<script type="text/javascript">
	function checkAll() {
		
		var form = document.loginForm
		
/* 		if(!checkUserId(form.id.value)){
			return false;
		} */
		//	ID, PW 유효성 검사
		var checkIdPw = /^[a-zA-Z0-9]{4,12}$/;
		// Name 한글만 입력
		var checkName = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
		// 연락처 유효성 검사
		var checkTel = /^[0-9]{3,4}$/;
		// 이메일 유효성 검사
		var checkEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		
 		// id Check		
		if(form.id.value == ""){
			alert("Please input your id");
			form.id.focus();
			return false;
		}
		if(!checkIdPw.test(form.id.value)){
			alert("Please input ID between 4~12 word by using Alphabet and Number");
			form.id.select();
			return false;
		}
		
		// pw Check		
		if(form.pw.value == ""){
			alert("Please input your Password");
			form.pw.focus();
			return false;
		}
		if(!checkIdPw.test(form.pw.value)){
			alert("Please input Password between 4~12 word by using Alphabet and Number");
			form.pw.select();
			return false;
		}
		
		// name check
		if(form.name.value == ""){
			alert("Please input your Name");
			form.name.focus();
			return false;
		}
		if(!checkName.test(form.name.value)){
			alert("Please input your name by only Korean");
			form.name.select();
			return false;
		}
		
 		// tel check
		if(form.tel2.value == "" || form.tel3.value == ""){
			alert("Plase input your Phone Number");
			form.tel2.focus();
			return false;
		}
		if(!checkTel.test(form.tel2.value) || !checkTel.test(form.tel3.value)){
			alert("Please input your Phone Number by only Number");
			form.tel2.select();
			return false;
		}
		
		// email check
		if(form.email.value == ""){
			alert("Please input your email");
			form.email.focus();
			return false;
		}
		if(!checkEmail.test(form.email.value)){
			alert("올바른 형식으로 입력해주세요.");
			form.email.select();
			return false;
		}
		
		
		return true;
	}
/* 	// 공백 확인 함수
	function checkExistData(value, dataName) {
		if(value == ""){
			alert(dataName + "입력해 주세요!");
			return false;
		}
		return true;
	} */
/* 	
	function checkUserId(id) {
		
		// Id, PW 검사
		var chk = /^[a-zA-Z0-9]{4,12}$/;
		
		if(!checkExistData(id, "아이디를"))
			return false;
		if(!chk.test(id)){
			alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해주세요.")
			form.user.id.select();
			return false;
		}
		return true;
	} */

</script>
<body>
	<h2>회원 가입</h2>
	<form name="loginForm" action="Validation05_process.jsp" method="post" onsubmit="return checkAll()">
		<p>아이디 : <input type="text" name="id"></p>
		<p>비밀번호 : <input type="password" name="pw"></p>
		<p>이름 : <input type="text" name="name"></p>
		<p>연락처 : <select name="tel1">
			<option value="010">010</option>
			<option value="017">017</option>
			<option value="019">019</option>
		</select> - 
				<input type="text" name="tel2" size="5" maxlength="4"> - 
				<input type="text" name="tel3" size="5" maxlength="4"></p>
		<p>이메일 : <input type="text" name="email"></p>
				


		<p><input type="submit" value="가입하기"></p>
	</form>

</body>
</html>