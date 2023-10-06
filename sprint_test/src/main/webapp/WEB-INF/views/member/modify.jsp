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
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<form action="/member/modify" method="post">
		<input type="hidden" name="id" value="${ses.id }">
		<table border="1">
			<tr>
				<th>ID</th>
				<td>${ses.id }</td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="text" name="pw" placeholder="변경할 비밀번호">
				</td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="email" name="email" value="${ses.email }"></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><input type="name" name="name" value="${ses.name }"></td>
			</tr>
			<tr>
				<th>Age</th>
				<td><input type="age" name="age" value="${ses.age }"></td>
			</tr>
			<tr>
				<th>Home</th>
				<td><input type="home" name="home" value="${ses.home }"></td>
			</tr>
		</table>

		<button type="submit">수정</button>
	</form>

	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<a href="index">Home</a>

</body>
</html>