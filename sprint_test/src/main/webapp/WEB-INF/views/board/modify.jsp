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
	
	<form action="/board/modify" method="post">
	<table border="1">
	<tr>
	<th>No</th>
	<th>제목</th>
	<th>작성자</th>
	<th>내용</th>
	<th>작성일</th>
	</tr>
	
	<tr>
	<td><input type="hidden" name="bno" value="${bvo.bno }"> </td>
	<td><input type="text" name="title" value="${bvo.title } }"></td>
	<td>${bvo.writer }</td>
	<td><textarea rows="5" cols="50" name="content">${bvo.content}</textarea> </td>
	<td>${bvo.registerDate }</td>
	</tr>	
	</table>
	
	<button type="submit">수정</button>
	</form>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>