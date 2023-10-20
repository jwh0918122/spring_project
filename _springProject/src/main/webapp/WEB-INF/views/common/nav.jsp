<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navBar</title>
<style type="text/css">
.boxZZZ{
display: flex;
justify-content: center;
width: 100%;
}
</style>
</head>
<body>

<nav>
<div class="boxZZZ" >
<div class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">	
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/list">Board List</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/register">Board Reg</a> <!-- href="/board/register"는 BoardController로 들어가는 매핑 -->
        </li>
      </ul>
    </div>
  </div>
  </div>
  </div>
</nav>

</body>
</html>