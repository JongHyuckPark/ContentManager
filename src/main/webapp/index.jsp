<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	
<title>Index Page</title>
</head>
<body>
<div class="container" align="center">
		<h1>게시판 제작 예제</h1>
		<br> <br>
		
		<c:choose>
			<c:when test="${empty name }">
				<div style="width: 800px;">
					<a href="join.jsp" class="btn btn-primary">회원가입</a>
					<a href="login.jsp" class="btn btn-primary">로그인</a> 
					<a href="GetContentListCtrl" class="btn btn-primary">제품 리스트</a>
				</div>
			</c:when>
			<c:when test="${id eq 'admin'}">
				<div style="width: 800px;">
					<a href="LogoutCtrl" class="btn btn-primary">로그아웃</a> 
					<a href="GetContentListCtrl" class="btn btn-primary">제품 리스트</a> 
					<a href="JoinContentCtrl" class="btn btn-primary">상품 정보 입력</a>
				</div>
			</c:when>
			<c:otherwise>
				<div style="width: 800px;">
					<a href="LogoutCtrl" class="btn btn-primary">로그아웃</a> 
					<a href="GetContentListCtrl" class="btn btn-primary">제품 리스트</a> 
				</div>
			</c:otherwise>
		</c:choose>


	</div>
</body>
</html>