<%@page import="java.util.ArrayList"%>
<%@page import="com.company.biz.vo.ContentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 등록</title>
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

	//2. Servlet이 전달한 데이터를 받는다.
	ContentVO vo = (ContentVO) request.getAttribute("vo");

	// 답글 데이터 받기
	// 	ArrayList<ReplyBoardVO> replyList=(ArrayList<ReplyBoardVO>)request.getAttribute("replyList");
	%>
	<div class="container" align="center">
		<h3>제품 상세</h3>
		<h3>
			${name }님 로그인 환영합니다..... <a href="LogoutCtrl" class="btn btn-primary">로그아웃</a>
		</h3>
		<hr>
		<%
		if (id.equals("admin")) {
		%>

		<form action="UpdateContentCtrl" method="post" name="book">
			<table class="table" style="width: 800px;">
				<tr>
					<td style="width: 200px;">제품코드</td>
					<td style="width: 200px;">${vo.contentcode }
						<input type="hidden" value="${vo.contentcode }" name="contentcode">
						<input type="hidden" value="${id }" name="id">
						<input type="hidden" value="${name }" name="name">
						
					</td>
				</tr>
				<tr>
					<td style="width: 200px;">제품종류</td>
					<td style="width: 200px;">
					<input type="text" value="${vo.content }" name="content"></td>

				</tr>
				<tr>
					<td style="width: 200px;">제목</td>
					<td style="width: 200px;"><input type="text"
						value="${vo.contentname }" name="contentname"></td>
				</tr>
				<tr>
					<td style="width: 200px;">저자</td>
					<td style="width: 200px;"><input type="text"
						value="${vo.author }" name="author"></td>
				</tr>
				<tr>
					<td style="width: 200px;">출판사</td>
					<td style="width: 200px;"><input type="text"
						value="${vo.publisher }" name="publisher"></td>
				</tr>
				<tr>
					<td style="width: 200px;">발행일</td>
					<td style="width: 200px;"><input type="text"
						value="${vo.publicationdate }" name="publicationdate"></td>
				</tr>

				<tr>
					<td style="width: 200px;">대여일</td>
					<td style="width: 200px;">${vo.rentaldate }</td>
				</tr>
				<tr>
					<td style="width: 200px;">반납일</td>
					<c:choose>
						<c:when test="${empty vo.rentaldate}">
							<td></td>
						</c:when>
						<c:otherwise>
							<td>${vo.returndate }</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td style="width: 200px;">가격</td>
					<td style="width: 200px;"><input type="text"
						value="${vo.price }" name="price"></td>
				</tr>
				<tr>
					<td style="width: 200px;">조회수</td>
					<td style="width: 200px;">${vo.cnt }</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="제품 수정" class="btn btn-primary">
						<c:choose>
							<c:when test="${empty vo.rentaldate}">
									<input type="button" value="예약하기" onclick="reservation(); return false;" class="btn btn-primary">
							</c:when>
							<c:when test="${vo.rentaldate eq '예약 중'}">
									<input type="button" value="예약취소" onclick="removeBook(); return false;" class="btn btn-primary">
							</c:when>
						</c:choose>
					</td>
				</tr>
			</table>
			<%
			} else {
			%>
			
			<table class="table" style="width: 800px;">
				<tr>
					<td style="width: 200px;">제품코드</td>
					<td style="width: 200px;">${vo.contentcode }
						<input type="hidden" value="${vo.contentcode }" name="contentcode">
						<input type="hidden" value="${id }" name="id">
						<input type="hidden" value="${name }" name="name">
						<input type="hidden" value="${vo.content }" name="content">
						<input type="hidden" value="${vo.contentname }" name="contentname">
						<input type="hidden" value="${vo.rentaldate }" name="rentaldate">
						<input type="hidden" value="${vo.price  }" name="price">
					</td>
				</tr>
				<tr>
					<td style="width: 200px;">제품종류</td>
					<td style="width: 200px;">${vo.content }</td>

				</tr>
				<tr>
					<td style="width: 200px;">제목</td>
					<td style="width: 200px;">${vo.contentname }</td>
				</tr>
				<tr>
					<td style="width: 200px;">저자</td>
					<td style="width: 200px;">${vo.author }</td>
				</tr>				
				<tr>
					<td style="width: 200px;">출판사</td>
					<td style="width: 200px;" >${vo.publisher }</td>
				</tr>
				<tr>
					<td style="width: 200px;">발행일</td>
					<td style="width: 200px;">${vo.publicationdate }</td>
				</tr>

				<tr>
					<td style="width: 200px;">대여일</td>
					<td style="width: 200px;">${vo.rentaldate }</td>
				</tr>
				<tr>
					<td style="width: 200px;">반납일</td>
					<c:choose>
						<c:when test="${empty vo.rentaldate}">
							<td></td>
						</c:when>
						<c:otherwise>
							<td>${vo.returndate }</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td style="width: 200px;">가격</td>
					<td style="width: 200px;">${vo.price }</td>
				</tr>
				<tr>
					<td style="width: 200px;">조회수</td>
					<td style="width: 200px;">${vo.cnt }</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<c:choose>
						<c:when test="${empty vo.rentaldate}">
								<input type="button" value="예약하기" onclick="reservation(); return false;" class="btn btn-primary">
						</c:when>
						<c:when test="${vo.rentaldate eq '예약 중'}">
								<input type="button" value="예약취소" onclick="removeBook(); return false;" class="btn btn-primary">
						</c:when>
					</c:choose>
						
					</td>
				</tr>
				
			</table>
			<%
			}
			%>

		</form>
		<hr>
		<%
		if (id.equals("admin")) {
		%>
		<a href="JoinContentCtrl">제품등록</a>&nbsp;&nbsp;&nbsp; <a
			href="DeleteContentCtrl?contentcode=${vo.contentcode }">제품삭제</a>&nbsp;&nbsp;&nbsp;
		<%
		}
		%>
		<a href="GetContentListCtrl">글목록</a>
		<br> <br> <br> <br>
		<br>

	</div>

</body>

<script type="text/javascript">
	function reservation(){
		var id=document.book.id.value
		var name=document.book.name.value
		var contentcode=document.book.contentcode.value
		var content=document.book.content.value
		var contentname=document.book.contentname.value
		var author=document.book.author.value
		var price=document.book.price.value
		
		document.book.method="post";
		document.book.action="ReservationCtrl";
		document.book.submit();
	}
// 	예약 취소가 안 되고 있음.
	function removeBook(){
		if(!confirm("예약을 취소하시겠습니까?")){
			
		}else{
			alert("예약이 취소되었습니다.")
			var rentaldate=document.book.rentaldate.value
			var contentcode=document.book.contentcode.value
			var name=document.book.name.value
			
			document.book.method="post";
			document.book.action="RemoveCtrl";
			document.book.submit();
		}
	}
</script>
</html>