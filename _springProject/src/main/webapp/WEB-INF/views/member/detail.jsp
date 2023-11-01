<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<form action="/member/modify" method="post">
	<table class="table">

		<tr>
			<th scope="col">EMAIL</th>
			<td><input type="text" name="email" value="${mvo.email}" readonly="readonly"></td>
		</tr>
		<tr>
			<th scope="col">NICK_NAME</th>
			<td><input type="text" name="nickName" value="${mvo.nickName}"></td>
		</tr>
		<tr>
			<th scope="col">REG_AT</th>
			<td>${mvo.regAt}</td>
		</tr>
		<tr>
			<th scope="col">RAST_LOGIN</th>
			<td>${mvo.lastLogin}</td>
		</tr>
	</table>
	<button class="btn btn-primary" type="submit">수정</button>
	</form>
	<a href="/member/delMvo?email=${mvo.email}"><button class="btn btn-primary" type="button">회원 탈퇴</button></a>
	<a href="/"><button class="btn btn-primary" type="button">홈으로</button></a>
	<hr>
	<jsp:include page="../common/footer.jsp" />

</body>
</html>