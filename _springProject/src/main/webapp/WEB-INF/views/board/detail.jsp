<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<table class="table">

		<tr>
			<th scope="col">WRITER</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th scope="col">TITLE</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th scope="col">CONTENT</th>
			<td>${bvo.content }</td>
	</table>

	<a href="/board/modify?bno=${bvo.bno}">
		<button class="btn btn-primary" type="button">수정</button>
	</a>
	<a href="/board/remove?bno=${bvo.bno}">
	<button class="btn btn-primary" type="button">삭제</button>
	</a>
		<button class="btn btn-primary" type="button">리스트로</button>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>