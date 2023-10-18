<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List Page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<table class="table">
		<thead>
			<tr>
				<th scope="col">BNO</th>
				<th scope="col">WRITER</th>
				<th scope="col">TITLE</th>
				<th scope="col">READ_COUNT</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
				
					<th><a href="/board/detail?bno=${bvo.bno}">${bvo.bno }</a></th>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.writer }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.readCount }</a></td>
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
	const isOk=`<c:out value="${isOk}"/>`;
	if(isOk>0){
		alert("수정 완료");
	}
	
	const reisOk=`<c:out value="${reisOk}"/>`;
	if(reisOk>0){
		alert("삭제 완료");
	}
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>