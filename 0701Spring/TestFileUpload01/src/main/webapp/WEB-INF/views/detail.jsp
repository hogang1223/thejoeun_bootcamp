<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>

	<table border="1">
		<form action="modify" method="post" enctype="multipart/form-data">
			<tr>
				<td>id</td>
				<td><input type="text" name="id" size = "20" value="${detail.id }"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="name" size = "20" value="${detail.name}"></td>
			</tr>
			<tr>
				<td>image</td>
				<td>
					<input type="hidden" name="oldFilepath" value="${detail.filepath }">
					<input type="file" name="imgfile" id="imgfile" accept="image/*">
					<div id="preview">
					<c:if test="${!empty detail.filepath}">
				    	<img src="${pageContext.request.contextPath }/resources/test/${detail.filepath}" width="auto" height="100">
				    </c:if>
				    </div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> &nbsp;&nbsp; <a href="list">목록보기</a></td>
			</tr>
		</form>
	
	</table>

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