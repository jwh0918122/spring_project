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
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.writer }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.registerDate }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.read_count }</a></td>
				</tr>


			</c:forEach>
		</tbody>

	</table>
	<script type="text/javascript">
	const isOk=`<c:out value="${isOk}"/>`;
	consol.log(isOk);
	if(isOk==1){
		alert('삭제 완료')
	}
	</script>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>