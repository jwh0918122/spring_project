<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navBar</title>
<style type="text/css">
.boxZZZ {
	display: flex;
	justify-content: center;
	width: 100%;
}
</style>
</head>
<body>

	<nav>
		<div class="boxZZZ">
			<div class="navbar navbar-expand-lg bg-body-tertiary">
				<div class="container-fluid">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a>
							<li class="nav-item"><a class="nav-link" href="/board/list">Board List</a></li> 
							
							${principal }
							
							
								<!-- 사용자 정보는 principal -->
								<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
									<!-- 관리자 권한만 open되는 것 -->
									<!-- 로그인 해야 open 되는 메뉴들(일반 유저) -->
									<!-- 아직 로그인 전 상태에서 open 되어야 할 메뉴 -->
							<!-- 로그인 한 상태에서 open 되어야 할 메뉴 -->
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal.mvo.email" var="authEmail" />
								<sec:authentication property="principal.mvo.nickName" var="authNick" />
								<sec:authentication property="principal.mvo.authList" var="auths" />
									
									<c:choose>
									<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
										<li class="nav-item">
											<a class="nav-link" href="/member/list">ADMIN > ${authNick}(${authEmail})</a> 
										</li>							
									</c:when>
									<c:otherwise>	
										<li class="nav-item">
											<a class="nav-link" href="/member/detail?email=${authEmail}">${authNick}(${authEmail})</a> 
										</li>																		
									</c:otherwise>
									</c:choose>	
									<li class="nav-item">
											<a class="nav-link" href="/board/register">Board Reg</a>
										</li>
																	
									<li class="nav-item">
										<a class="nav-link" href="/member/logout" id="logoutLink">LogOut</a>
									</li>																									
									<form action="/member/logout" method="post" id="logoutForm">
									<input type="hidden" name="email" value="${authEmail}">
									</form>
							</sec:authorize>								
									
									<sec:authorize access="isAnonymous()">
									<li class="nav-item"><a class="nav-link" href="/member/register">SignUp</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="/member/login">LogIn</a>
									</li>																		
									</sec:authorize>	
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click',(e)=>{
	e.preventDefault();
	document.getElementById('logoutForm').submit();
})
</script>
</body>
</html>