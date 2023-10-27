<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Register Page</title>
<style type="text/css">
form {
	display: flex;
	justify-content: center;
}
.box2{
display: flex;
	justify-content: center;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<!-- email, pwd, nick_name -->
	<form action="/member/register" method="post">
		<div class="boxs">
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">EMAIL</label>
				<input type="text" class="form-control" name="email"
					id="exampleFormControlInput1">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">PW</label>
				<input type="text" class="form-control" name="pwd"
					id="exampleFormControlInput1">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlTextarea1" class="form-label">NICK_NAME</label>
				<input type="text" class="form-control" name="nickName"
					id="exampleFormControlInput1">
			</div>
			<div class="box2">
				<button type="submit" class="btn btn-primary">join</button>
			</div>
		</div>
	</form>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>