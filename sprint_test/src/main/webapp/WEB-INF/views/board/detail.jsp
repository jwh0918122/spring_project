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

	<table border="1">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>

		<tr>
			<td>${bvo.bno }</td>
			<td>${bvo.title }</td>
			<td>${bvo.writer }</td>
			<td>${bvo.content }</td>
			<td>${bvo.registerDate }</td>
			<td>${bvo.read_count }</td>
		</tr>
	</table>
	<a href="/board/modify?bno=${bvo.bno }">
		<button type="button">수정</button>
	</a>
	<a href="/board/remove?bno=${bvo.bno }">
		<button type="button">삭제</button>
	</a>
	<a href="/board/list">
		<button type="button">리스트</button>
	</a>
	
	<hr>
	<!-- comment line -->
	<div>
		<!-- 댓글 작성 라인 -->
		<div>
			<span id="cmtWriter">${ses.id }</span> 
			<input type="text" id="cmtText" placeholder="Add Comment...">
			<button type="button" id="cmtPostBtn">댓글등록</button>
		</div>
		<!-- 댓글 표시 라인 -->
		<div>
			<ul id="cmtListArea">
				<li>
					<div>
						<div>Writer</div>
						Content
					</div> 
					<span>reg_date</span>
				</li>
			</ul>
		</div>
	</div>
<script type="text/javascript">
const bnoVal =`<c:out value="${bvo.bno}"/>`;
console.log(bnoVal);
</script>
<script type="text/javascript" src="/resources/js/boardComment.js"></script>

	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>