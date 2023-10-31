<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<!-- 검색 라인 -->

	<div class="listBox">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">EMAIL</th>
						<th scope="col">NICK_NAME</th>
						<th scope="col">REG_AT</th>
						<th scope="col">LAST_LOGIN</th>
						<th scope="col">AUTH</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="mvo">
						<tr>

							<td>${mvo.email}</td>
							<td>${mvo.nickName}</td>
							<td>${mvo.regAt}</td>
							<td>${mvo.lastLogin}</td>
							<td>
							<c:forEach items="${mvo.authList}" var="auth">	
							${auth.auth}
							</c:forEach>
							</td>
							<td><a href="/member/adminModify?email=${mvo.email}"><button type="button" class="btn btn-primary">수정</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>