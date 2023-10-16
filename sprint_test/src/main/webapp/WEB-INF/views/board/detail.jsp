<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<!-- BoardDTO로 바꿨는데 이미 detail.jsp에는 bvp로 다 입력해놔서 이렇게 해줌 -->
	<c:set value="${boardDTO.bvo }" var="bvo"></c:set>

	<table border="1">
		<tr>
			<th>No</th>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${bvo.content}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${bvo.registerDate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bvo.read_count }</td>
		</tr>
	</table>

	<!-- file 표시 영역 -->
	<c:set value="${boardDTO.flist }" var="flist"></c:set>
	<div>
		<ul>
			<!-- 파일 개수만큼 li를 추가하여 파일을 표시(타입이 1일경우만(그림만) 표시) -->
			<!-- li > div > img 그림 표시 > div > div > 파일 이름, 작성일자, span > 파일 크기-->

			<!-- 하나의 파일만 따와서 fvo로 저장 -->
			<c:forEach items="${flist }" var="fvo">
				<li><c:choose>
						<c:when test="${fvo.file_type > 0 }">
							<div>
								<!-- /upload/ => servlet-context에 설정한 경로 -->
								<!-- /upload/year/month/day/uuid_file_name -->

								<!-- FleHandler에 UP_DIR를 D:\\_myweb\\_java\\fileupload로 해놨어서-->
								<!-- '\\'를 '/'로(웹에서는 '/'를 사용 함) fn태그를 사용하여 변환  -->
								<img alt="없음"
									src="/upload/${fn:replace(fvo.save_dir,'\\','/')}/
							${fvo.uuid}_th_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- 이미지 없는 애들은 파일 아이콘 넣을 수 있음 -->
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<div>${fvo.file_name }</div>
						${fvo.reg_date }
					</div> <span>${fvo.file_size }Byte</span></li>
			</c:forEach>
		</ul>
	</div>


	<c:if test="${ses.id eq bvo.writer }">
		<a href="/board/modify?bno=${bvo.bno }">
			<button type="button">수정</button>
		</a>
		<a href="/board/remove?bno=${bvo.bno }">
			<button type="button">삭제</button>
		</a>
	</c:if>
	<a href="/board/list">
		<button type="button">리스트</button>
	</a>

	<hr>
	<!-- comment line -->
	<div>
		<!-- 댓글 작성 라인 -->
		<div>
			<span id="cmtWriter">${ses.id }</span> <input type="text"
				id="cmtText" placeholder="Add Comment...">
			<button type="button" id="cmtPostBtn">댓글등록</button>
		</div>
		<!-- 댓글 표시 라인 -->
		<div>
			<ul id="cmtListArea">
				<li>
					<div>
						<div>Writer</div>
						Content
					</div> <span>reg_date</span>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
		console.log(bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>

	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>