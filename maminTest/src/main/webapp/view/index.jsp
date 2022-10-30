<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마블의 민족</title>
	<link href="../css/index.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %> <!-- file="jsp파일 주소" -->
	
	<div class="wrapper">
		<div class="m_main_img">
			<div class="m_logobox">
				<h3 class="m_main_text">마블의 민족</h3>
				<img class="character" src="/mamin/img/main/지구졸라맨.png">
				<div class="btn_box">
					<div class="startbtn" onclick="checkRoom()">Start Game</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 캐러셀
	
	<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="../img/지웅/main/도시_밤.png" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="../img/지웅/main/기린_아프리카.png" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="../img/지웅/main/낙타_사막.png" class="d-block w-100" alt="...">
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>	
	-->
	
	<script src="../js/index.js"></script>
</body>
</html>