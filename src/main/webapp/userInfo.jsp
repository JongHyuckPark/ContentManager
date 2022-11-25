<%@page import="com.company.biz.vo.UserVO"%>
<%@page import="com.company.biz.vo.ContentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserInfo</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${empty name }">
		<c:redirect url="login.jsp" />
	</c:if>
	<%
	String id = (String) session.getAttribute("id");
	String name = (String) session.getAttribute("name");

	%>
	<div class="container" align="center">
		<h3>내 정보</h3>
		<h3>
			${name }님 로그인 환영합니다..... <a href="LogoutCtrl" class="btn btn-primary">로그아웃</a>
		</h3>
		<hr>
		
		<form action="UpdateUserInfo" method="post" name="userInfo">
			<table class="table" style="width: 800px;">
				<tr>
					<td style="width: 200px;">ID</td>
					<td style="width: 200px;">
						<input type="text" value="${uvo.id }" name="id">
						
					</td>
				</tr>
				<tr>
					<td style="width: 200px;">비밀번호</td>
					<td style="width: 200px;">
					<input type="text" value="${uvo.password }" name="password"></td>

				</tr>
				<tr>
					<td style="width: 200px;">이름</td>
					<td style="width: 200px;">
					<input type="text" value="${uvo.name }" name="name"></td>
				</tr>
				<tr>
					<td style="width: 200px;">전화번호</td>
					<td style="width: 200px;"><input type="text"
						value="${uvo.phone }" name="phone"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="정보 수정" class="btn btn-primary">
					<c:choose>
						<c:when test="${empty usersRVList}">
							<button onclick="deleteUser();return false;" class="btn btn-primary">회원 탈퇴</button>
<!-- 							<button type="button" onclick="deleteUser();return false;" value="회원 탈퇴" class="btn btn-primary"> -->
						</c:when>
					</c:choose>
					</td>
				</tr>
			</table>
		</form>
		
		<form name="reservationList">
			<table class="table" style="width: 900px;">
			<tr>
				<th style="width: 200px;">제품번호</th>
				<th style="width: 100px;">제품종류</th>
				<th style="width: 150px;">제목</th>
				<th style="width: 100px;">대여일</th>
				<th style="width: 150px;">반납일</th>
				<th style="width: 100px;">가격</th>
				<th style="width: 100px;">반납</th>
			</tr>
			<c:choose>
				<c:when test="${empty usersRVList}">
					<tr>
						<td align="center" colspan="8">대출내역이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${usersRVList }" var="usersRVList" varStatus="status">
						<tr>
							<td style="width: 200px;">${usersRVList.contentcode }
							</td>
							<td style="width: 100px;">${usersRVList.genre }
								<input type="hidden" value="${usersRVList.id }" name="id">
								<input type="hidden" value="${usersRVList.contentcode }" name="contentcode">
							</td>
							<td style="width: 150px;">${usersRVList.contentname}</td>
							<td style="width: 100px;">${usersRVList.rentaldate }</td>
							<td style="width: 150px;">${usersRVList.returndate }</td>
							<td style="width: 100px;">${usersRVList.price }</td>
							<td style="width: 100px;"> <input type="button" value="반납" class="btn btn-primary" 
								onclick="returnContent();return false;"> 
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		</form>
		<a href="index.jsp" class="btn btn-primary">초기화면</a>
	</div>
	<script type="text/javascript">
		function returnContent(){
		    if (!confirm("반납하시겠습니까?")) {
		    	alert('취소되었습니다.');
		    } else {
				alert("반납되었습니다.");
				var id=document.reservationList.id.value;
				var contentcode=document.reservationList.contentcode.value;
				
				document.reservationList.method="post";
				document.reservationList.action="ReturnContentCtrl";
				document.reservationList.submit();
			}
		 }
		
		function deleteUser(){
		    if (!confirm("회원탈퇴 하시겠습니까?")) {
		    	alert('취소되었습니다.');
		    } else {
				alert('회원탈퇴에 성공하였습니다.');
				var id=document.userInfo.id.value;
				
				document.userInfo.method="post";
				document.userInfo.action="DeleteUserCtrl";
				document.userInfo.submit();
			}
		}
		

	</script>
</body>
</html>