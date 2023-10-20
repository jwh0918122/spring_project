<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form action="/board/modify" method="post">
		<table class="table">
			<tr>
				<th scope="col">BNO</th>
				<td><input type="number" name="bno" value="${bvo.bno}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">WRITER</th>
				<td><input type="text" name="writer" value="${bvo.writer}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">TITLE</th>
				<td><input type="text" name="title" value="${bvo.title}"></td>
			</tr>
			<tr>
				<th scope="col">CONTENT</th>
				<td><textarea rows="3" cols="30" name="content">${bvo.content }</textarea>
				</td>
		</table>
		<button class="btn btn-primary" type="submit">수정 완료</button>
		<button class="btn btn-primary" type="button">리스트로</button>
	</form>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>