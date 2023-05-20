<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
	<!--Encytpe을 반드시 multipart/form-data로 작성  -->
	<form method="post" action="upload" enctype="multipart/form-data">
		<p> 이름 : <input type="text" name="name"> </p>
		<p> 파일 : <input type="file" name="imgfile" id="imgfile" accept="image/*"></p>
		<div id="preview"></div>
		<p> <input type="submit" value="upload"> </p>
	</form>
</body>
<script type="text/javascript">

	function readInputFile(input) {
	    if(input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $('#preview').html("<img src="+ e.target.result +" width='auto' height='100'>");
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	 
	$('#imgfile').on('change', function(){
	    readInputFile(this);
	});
	function resetInputFile($input, $preview) {
	    var agent = navigator.userAgent.toLowerCase();
	     if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
	        // ie 일때
	        $input.replaceWith($input.clone(true));
	        $preview.empty();
	    } else { 
	        //other
	        $input.val("");
	        $preview.empty();
	    }       
	}
	 
	$(".btn-delete").click(function(event) {
	    var $input = $('#imgfile');
	    var $preview = $('#preview');
	    resetInputFile($input, $preview);
});
</script>
</html>