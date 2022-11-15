<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 등록</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	
	<c:if test="${empty name}">
		<c:redirect url="login.jsp"/>
	</c:if>
		
	<div class="container" align="center">
		<h3>새 글 등록하기...
		<a href="UserInfo" class="btn btn-primary">내 정보 보기</a> &nbsp;&nbsp;&nbsp;
		<a href="LogoutCtrl" class="btn btn-primary">로그아웃</a> </h3>
		<hr>
		<form name="addContent">
			<table class="table" style="width: 1000px;">
				<tr>
					<td style="width: 200px;">제품코드</td>
					<td style="width: 200px;"><input type="text" name="contentcode" value="${contentcode }"></td>
				</tr>
				<tr>
					<td style="width: 200px;">제품종류</td>
					<td style="width: 200px;"><input type="text" name="genre"></td>

				</tr>
				<tr>
					<td style="width: 200px;">제목</td>
					<td style="width: 200px;"><input type="text" name="contentname"></td>
				</tr>
				<tr>
					<td style="width: 200px;">저자</td>
					<td style="width: 200px;"><input type="text" name="author"></td>
				</tr>
				<tr>
					<td style="width: 200px;">출판사</td>
					<td style="width: 200px;"><input type="text" name="publisher"></td>
				</tr>
				<tr>
					<td style="width: 200px;">발행일</td>
					<td style="width: 200px;"><input type="date" name="publicationdate" style="width: 180px;"></td>
				</tr>
				<tr>
					<td style="width: 200px;">가격</td>
					<td style="width: 200px;"><input type="text" name="price"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="button" class="btn btn-primary" onclick="insertContent();return false;" value="등록"></td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			function insertContent(){
				var contentcode=document.addContent.contentcode.value;
				var genre=document.addContent.genre.value;
				var contentname=document.addContent.contentname.value;
				var author=document.addContent.author.value;
				var publisher=document.addContent.publisher.value;
				var publicationdate=document.addContent.publicationdate.value;
				var price=document.addContent.price.value;
				
				if(contentcode=="" || contentcode.length==0){
					alert('제품코드를 입력하세요.');
					document.addContent.contentcode.focus();
				}else if(genre=="" || genre.length==0){
					alert('제품종류를 입력하세요.');
					document.addContent.genre.focus();
				}else if(contentname=="" || contentname.length==0){
					alert('제목을 입력하세요.');
					document.addContent.contentname.focus();
				}else if(author=="" || author.length==0){
					alert('저자를 입력하세요.');
					document.addContent.author.focus();
				}else if(publisher=="" || publisher.length==0){
					alert('출판사를 입력하세요.');
					document.addContent.publisher.focus();
				}else if(publicationdate=="" || publicationdate.length==0){
					alert('출판일을 입력하세요.');
					document.addContent.publicationdate.focus();
				}else if(price=="" || price.length==0){
					alert('가격을 입력하세요.');
					document.addContent.price.focus();
				}else{
					document.addContent.method="post";
					document.addContent.action="AddContentCtrl";
					document.addContent.submit();
				}
			}
		</script>
	</div>
</body>
</html>