<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./layout/header.jsp"></jsp:include>

<h1>
	Hello world!  
</h1>

<P>  My Spring Project </P>

<script>
const msg_login =`<c:out value="${msg_login}"/>`;
const msg_logout =`<c:out value="${msg_logout}"/>`;
const msg_modify =`<c:out value="${msg_modify}"/>`;
if(msg_login==1){
	alert("로그인 실패")
}
if(msg_logout==1){
	alert("로그아웃 되었습니다.")
}
if(msg_modify==1){
	alert("정보가 수정되었습니다.")
}

/*  const sesId =`<c:out value="${ses.id}"/>`;
 console.log(sesId);
if(sesId != null)){
	alert(`${sesId }님 환영합니다.`);	
} */  

</script>
<c:if test="${ses.id ne null }">
  <h3>${ses.id }님 환영합니다.</h3>
</c:if> 

<jsp:include page="./layout/footer.jsp"></jsp:include>

