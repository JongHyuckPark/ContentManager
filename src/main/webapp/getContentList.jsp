<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty name }">
	<c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container" align="center">
		<h1>글 목록</h1>
		<h3>
			${name } 님 환영합니다... <a href="LogoutCtrl" class="btn btn-primary">로그아웃</a>
		</h3>

		<!-- 검색코드 시작부분 -->
<!-- 		<form action="GetSearchCtrl"> -->
<!-- 			<table class="table" style="width: 800px;"> -->
<!-- 				<tr> -->
<!-- 					<td align="right"><select name="searchCondition"> -->
<!-- 							<option value="title">제목</option> -->
<!-- 							<option value="content">내용</option> -->
<!-- 							<option value="nickname">작성자</option> -->
<!-- 					</select> <input type="text" name="searchKeyword"> <input -->
<!-- 						type="submit" value="검색" class="btn btn-primary"></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</form> -->
		<!-- 검색코드 끝 부분 -->

		<hr>

		<table class="table" style="width: 1100px;">
			<tr>
				<th style="width: 200px;">제품번호</th>
				<th style="width: 100px;">제품종류</th>
				<th style="width: 150px;">이름</th>
				<th style="width: 150px;">출판사</th>
				<th style="width: 100px;">발행일</th>
				<th style="width: 100px;">대여일</th>
				<th style="width: 100px;">반납일</th>
				<th style="width: 100px;">가격</th>
				<th style="width: 100px;">조회수</th>
			</tr>
			<c:choose>
				<c:when test="${empty contentList}">
					<tr>
						<td align="center" colspan="5">등록된 글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${contentList }" var="content" varStatus="status">
						<tr>
							<td>${content.contentcode }</td>
							<td>${content.content }</td>
							<td><a href="GetContentCtrl?contentcode=${content.contentcode }">${content.contentname }</a>
							</td>
							<td>${content.publisher }</td>
							<td>${content.publicationdate }</td>
							<td>${content.rentaldate }</td>
							<td>${content.returndate }</td>
							<td>${content.price }</td>
							<td>${content.cnt }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
		<br> <a href="addContent.jsp">새글 등록</a>

	</div>
</body>
</html>