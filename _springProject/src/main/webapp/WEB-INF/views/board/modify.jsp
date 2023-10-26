<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th scope="col">BNO</th>
				<td><input type="number" name="bno" value="${bdto.bvo.bno}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">WRITER</th>
				<td><input type="text" name="writer" value="${bdto.bvo.writer}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">TITLE</th>
				<td><input type="text" name="title" value="${bdto.bvo.title}"></td>
			</tr>
			<tr>
				<th scope="col">CONTENT</th>
				<td><textarea rows="3" cols="30" name="content">${bdto.bvo.content }</textarea>
				</td>
			</tr>
		</table>
		<!-- 새 파일 등록 -->
		<input type="file" class="form-control" name="files" id="files"
			style="display: none;" multiple="multiple">
		<!-- input button trigger  용도의 button -->
		<button type="button" id="trigger" class="btn btn-outline-primary">File
			Upload</button>
		<div class="mb-3" id="fileZone">
			<!-- 새 파일 표시될 영역 -->
		</div>
		<hr>

		<!-- 파일 삭제 -->
		<c:set value="${bdto.flist}" var="flist"></c:set>
		<c:forEach items="${flist}" var="fvo">
			<c:choose>
				<c:when test="${fvo.fileType > 0}">
					<div>
						<img
							src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}">
						<button type="button" class="btn btn-primary fileDel"
							data-uuid="${fvo.uuid}">X</button>
					</div>
				</c:when>
				<c:otherwise>
					<!-- 이미지 없은 경우 아이콘 표시 -->
				</c:otherwise>
			</c:choose>
				file_name : ${fvo.fileName} <br>
				reg_date : ${fvo.regAt}<br>
				file_size : ${fvo.fileSize} 			
		</c:forEach>

<hr>
		<button class="btn btn-primary" id="regBtn" type="submit">수정
			완료</button>
		<a href="/board/list"><button class="btn btn-primary" type="button">리스트로</button></a>
	</form>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>