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

<form action="/board/register" method="post">
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
  <button type="submit" class="btn btn-primary">등록</button>
</div>
</form>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>