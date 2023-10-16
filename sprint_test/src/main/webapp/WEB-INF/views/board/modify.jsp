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
	<c:set value="${boardDTO.bvo }" var="bvo"></c:set>

	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>No</th>
				<td><input type="hidden" name="bno" value="${bvo.bno }">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${bvo.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${bvo.writer }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="5" cols="50" name="content">${bvo.content}</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${bvo.registerDate }</td>
			</tr>
			<tr>
			<td>
				<!-- 수정 파일 등록 라인 -->
		file:<input type="file" id="file" name="files" multiple="multiple" style="display: none">
		<button type="button" id="trigger">FileUpload</button><br>
		<div id="fileZone">
		
		</div>
			</td>
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
						<div>${fvo.file_name }</div>
						<button type="button" class="file-x" data-uuid="${fvo.uuid }" >X</button>
						</li>
				</c:forEach>
			</ul>
		</div>
		
		
		
		<button type="submit" id="regBtn">수정</button>
	</form>
	
	<script type="text/javascript">
	const sesId = `<c:out value="${ses.id}"/>`
	const writer = `<c:out value="${bvo.writer }"/>`
	</script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>