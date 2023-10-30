<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<sec:authentication property="principal.mvo.email" var="authEmail" />
<form action="/board/register" method="post" enctype="multipart/form-data">
<div class="mb-3">

  <label for="t" class="form-label">TITLE</label>
  <input type="text" class="form-control" name="title" id="t">
</div>
<div class="mb-3">
  <label for="w" class="form-label">WRITER</label>
  <input type="text" class="form-control"  name="writer" id="w"  value="${authEmail}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="c" class="form-label">CONTENT</label>
  <textarea class="form-control" name="content" id="c" rows="3"></textarea>
 </div>
<div class="mb-3">
	<!-- multiple="multiple" => 파일을 여러 개 등록하기 위해 -->
  <input type="file" class="form-control"  name="files" id="files" style="display: none;" multiple="multiple">
	<!-- input button trigger  용도의 button -->
<button type="button" id="trigger" class="btn btn-outline-primary">File Upload</button>
</div>
<div class="mb-3" id="fileZone">
	<!-- 첨부파일 표시될 영역 -->
</div>  
	<hr>
  <button type="submit" class="btn btn-primary" id="regBtn">등록</button>

</form>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>