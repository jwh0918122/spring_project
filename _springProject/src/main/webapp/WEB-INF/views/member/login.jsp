<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style type="text/css">
form {
	display: flex;
	justify-content: center;
}

.box2 {
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />


	<h2 style="text-align: center;">LogIn Page</h2>
	<form action="/member/login" method="post">
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
			<c:if test="${not empty param.errMsg}">
				<div class="text-danger mb-3">
					<c:choose>
						<c:when test="${param.errMsg eq 'Bad credentials'}">
							<c:set var="errText" value="비밀번호가 일치하지 않습니다." />
						</c:when>
						<c:otherwise>
							<c:set var="errText" value="이메일을 확인해주세요." />
						</c:otherwise>
					</c:choose>
					${errText}
					${param.errMsg }
				</div>
			</c:if>


			<div class="box2">
				<button type="submit" class="btn btn-primary">LogIn</button>
			</div>
		</div>
	</form>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>