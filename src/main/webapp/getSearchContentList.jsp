<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 글 목록</title>
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
			<c:choose>
				<c:when test="${empty name}">
					Guest님 환영합니다. <a href="index.jsp" class="btn btn-primary">이전 페이지</a>
				</c:when>
				<c:otherwise>
					${name } 님 환영합니다... <a href="LogoutCtrl" class="btn btn-primary">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</h3>

<!-- 		검색코드 시작부분 -->
		<form action="GetSearchCtrl" method="post">
			<table class="table" style="width: 800px;">
				<tr>
					<td align="right"><select name="searchCondition">
							<option value="contentname">제목</option>
							<option value="genre">장르</option>
							<option value="author">저자</option>
					</select> <input type="text" name="searchKeyword"> <input
						type="submit" value="검색" class="btn btn-primary"></td>
				</tr>
			</table>
		</form>
<!-- 		검색코드 끝 부분 -->

		<hr>

		<table class="table" style="width: 1100px;">
			<tr>
				<c:choose>
					<c:when test="${id eq 'admin' }">
					<th style="width: 200px;">제품번호</th>
					</c:when>
				</c:choose>
				<th style="width: 100px;">장르</th>
				<th style="width: 150px;">제목</th>
				<th style="width: 100px;">저자</th>
				<th style="width: 150px;">출판사</th>
				<th style="width: 100px;">발행일</th>
				<th style="width: 100px;">대출여부</th>
				<th style="width: 100px;">가격</th>
				<th style="width: 100px;">조회수</th>
			</tr>
			<c:choose>
				<c:when test="${empty contentList}">
					<tr>
						<td align="center" colspan="8">등록된 글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${contentList }" var="contentList" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${id eq 'admin' }">
									<td style="width: 200px;">${contentList.contentcode }</td>
								</c:when>
							</c:choose>
							<td style="width: 100px;">${contentList.genre }</td>
							<td style="width: 150px;"><a href="GetContentCtrl?contentcode=${contentList.contentcode }">${contentList.contentname }</a>
							</td>
							<td style="width: 100px;">${contentList.author }</td>
							<td style="width: 150px;">${contentList.publisher }</td>
							<td style="width: 100px;">${contentList.publicationdate }</td>
							<td style="width: 100px;">${contentList.reservation }</td>
							<td style="width: 100px;">${contentList.price }</td>
							<td style="width: 100px;">${contentList.cnt }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		

		<c:choose>
			<c:when test="${id eq 'admin' }">
				<br> <a href="JoinContentCtrl">새글 등록</a>
			</c:when>
		</c:choose>

		
	</div>
</body>
</html>