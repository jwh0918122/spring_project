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
	<!-- 검색 구역 -->
	
		<form action="/board/list" method="get">
			<c:set value="${ph.pgvo.type }" var="typed"></c:set>
			<select name="type">
				<option ${typed==null? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't'? 'selected':'' }>title</option>
				<option value="c" ${typed eq 'c'? 'selected':'' }>content</option>
				<option value="w" ${typed eq 'w'? 'selected':'' }>writer</option>
				<option value="tc" ${typed eq 'tc'? 'selected':'' }>title+content</option>
				<option value="cw" ${typed eq 'cw'? 'selected':'' }>content+writer</option>
				<option value="tw" ${typed eq 'tw'? 'selected':'' }>title+writer</option>
				<option value="tcw" ${typed eq 'tcw'? 'selected':'' }>all</option>
			</select> 

			<!-- qty 바꾸기 -->
			<c:set value="${ph.pgvo.qty }" var="qtyVal"></c:set>
			<select name="qty">
				<option value=5 ${qtyVal eq 5? 'selected':''}>5개</option>
				<option value=10 ${qtyVal==null || qtyVal eq 10? 'selected':''}>10개</option>
				<option value=15 ${qtyVal eq 15? 'selected':''}>15개</option>
			<!-- qty 바꾸기 끝 -->
			
			</select> 
			<input type="hidden" name="pageNo" value="1"> 
			<input type="text" name="keyword" value="${ph.pgvo.keyword }" placeholder="search">
			<button type="submit">검색</button>
		</form>
		totalCount : ${ph.totalCount }
		<!-- 검색 구역 끝 -->
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>파일수</th>
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
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.commentCount }</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.fileCount }</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<!-- 페이지네이션 구역 -->
		<div>
		<c:if test="${ph.prev}">
			<a
				href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
				◁ | </a>
		</c:if>
		<c:forEach begin="${ph.startPage}" end="${ph.endPage}"
			var="i">
			<a
				href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }
			</a>
		</c:forEach>
		<c:if test="${ph.next}">
			<a
				href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
				| ▷ </a>
		</c:if>
	</div>
	
		
	<script type="text/javascript">
	const isOk=`<c:out value="${isOk}"/>`;
	console.log(isOk);
	if(isOk==1){
		alert('삭제 완료')
	}
	</script>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>