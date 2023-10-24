<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
<style type="text/css">
.bigBox {
	width: 1000px;
	margin-left: 250px;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<div class="bigBox">
		<table class="table">

			<tr>
				<th scope="col">BNO</th>
				<td>${bdto.bvo.bno }</td>
			</tr>
			<tr>
				<th scope="col">WRITER</th>
				<td>${bdto.bvo.writer }</td>
			</tr>
			<tr>
				<th scope="col">TITLE</th>
				<td>${bdto.bvo.title }</td>
			</tr>
			<tr>
				<th scope="col">CONTENT</th>
				<td>${bdto.bvo.content }</td>
			</tr>
			<tr>
				<th scope="col">REG_DATE</th>
				<td>${bdto.bvo.regAt }</td>
			</tr>
		</table>
		<c:forEach items="${bdto.flist}" var="fvo">
			<c:choose>
				<c:when test="${fvo.fileType > 0}">
					<div>
						<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}${fvo.fileName}">
											
					</div>
				</c:when>
				<c:otherwise>
					<!-- 이미지 없은 경우 아이콘 표시 -->
				</c:otherwise>
			</c:choose>
			<dir>
				file_name : ${fvo.fileName} <br>
				reg_date : ${fvo.regAt}<br>
				file_size : ${fvo.fileSize} 
			</dir>			
		</c:forEach>

		<a href="/board/modify?bno=${bvo.bno}"><button
				class="btn btn-primary" type="button">수정</button></a> <a
			href="/board/remove?bno=${bvo.bno}"><button
				class="btn btn-primary" type="button">삭제</button></a> <a
			href="/board/list"><button class="btn btn-primary" type="button">리스트로</button></a>

		<hr>
		<!-- 댓글 라인 -->
		<div>
			<!-- 댓글 등록 라인 -->
			<div class="input-group mb-3">
				<span class="input-group-text" id="cmtWriter">${bvo.writer}</span> <input
					type="text" class="form-control" id="cmtText"
					placeholder="Test Comment">
				<button class="btn btn-primary" id="cmtPostBtn" type="button">댓글
					등록</button>
			</div>


			<!-- 댓글 표시 라인 -->
			<ul class="list-group list-group-flush" id="cmtListArea">
			</ul>

			<!-- 댓글 더보기 버튼 -->
			<div>
				<div>
					<!-- style="visibility: hidden" <= 숨김 -->
					<button type="button" id="moreBtn" data-page="1"
						class="btn btn-outline-dark" style="visibility: hidden">MORE+</button>
				</div>
			</div>

			<!-- 모달창 -->
			<div class="modal" id="myModal" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Writer</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body">
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="cmtTextModal"
									placeholder="Test Comment">
								<button class="btn btn-primary" id="cmtModBtn" type="button">수정</button>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<script type="text/javascript">
			let bnoVal = `<c:out value="${bvo.bno}"/>`;
			console.log("bnoVal>>> " + bnoVal);
		</script>
		<script type="text/javascript" src="/resources/js/boardComment.js"></script>
		<script type="text/javascript">
			printCommentList(bnoVal);
		</script>
		<jsp:include page="../common/footer.jsp" />
</body>
</html>