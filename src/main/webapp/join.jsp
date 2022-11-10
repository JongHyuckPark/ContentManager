<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container" align="center">
		<h1>회원가입</h1>
		<hr>
		<form action="JoinCtrl" method="post" name="join">
			<table class="table" style="width:400px;">
				<tr>
					<td>아이디</td>
					<td> <input type="text" name="id" value="${param.id }"> <button onclick="idCheck();return false;" class="btn btn-primary" value="${param.id }">ID중복확인</button> </td>
				</tr>
				
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>
				</tr>
				
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone"></td>
				</tr>
				
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" value="가입" class="btn btn-primary">
						<input type="reset" value="다시입력" class="btn btn-primary">
					</td>
				</tr>
			</table>
		</form>
		<hr>
		
	</div>
		<script type="text/javascript">
		function idCheck(){
			var id=document.join.id.value;
			
			if(id=="" || id.length==0){
				alert('id를 입력하세요.');
				document.join.id.focus();
			}else{
				document.join.method="post";
				document.join.action="IdCheckCtrl";
				document.join.submit();
			}
		}
		</script>
</body>
</html>