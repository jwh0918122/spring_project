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
	<h2>게시글 등록</h2>
	<!-- mapping 상태는 get/post가 별도의 mapping을 가짐 -->
	<!-- 그래서 이름이 같아도 형태가 다르기 때문에 구분이 되서 괜찮음 -->
	<!-- enctype="multipart/form-data" : 첨부파일 보낼 때 -->
	<form action="/board/register" method="post" enctype="multipart/form-data"> 
		title : <input type="text" name="title"><br>
		writer : <input type="text" name="writer" value="${ses.id }" readonly="readonly"><br>
		content : <textarea rows="5" cols="50" name="content"></textarea><br>
		<!-- multiple="multiple" : 한번에 여러개의 파일을 등록할 때 사용 -->
		<!-- style="display: none" : 기본으로 생성되는 "파일 선택" 버튼이 사라짐 -->
		file:<input type="file" id="file" name="files" multiple="multiple" style="display: none">
		<button type="button" id="trigger">FileUpload</button><br>
		<div id="fileZone">
		
		</div>
		<button type="submit" id="regBtn">등록</button>
	</form>
	
	<hr>
	<a href="/">
		<button type="button">홈</button>
	</a>
	<a href="/board/list">
		<button type="button">리스트</button>
	</a>
	
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>
