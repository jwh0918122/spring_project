<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<form action="/board/register" method="post" enctype="multipart/form-data">
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">TITLE</label>
  <input type="text" class="form-control" name="title" id="exampleFormControlInput1">
</div>
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">WRITER</label>
  <input type="text" class="form-control"  name="writer" id="exampleFormControlInput1">
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">CONTENT</label>
  <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
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
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
</body>
</html>