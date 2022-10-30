<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
<title>마블의 민족</title>
	<link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
	<link href="../css/header.css" rel="stylesheet">
</head>
<body>

	<% String loginID = (String) session.getAttribute("m_id"); %>
	<div class="H_idbox" style="display : none;"><%=loginID %></div>

	<header>
		<div class="hd_box">
			<div class="h_logo">
				<a href=""><img src="/mamin/img/main/비행기.png"></a>
				<h3><a href="">마블의 민족</a></h3>
			</div>
			<div class="h_menu">
			<% if(loginID==null){ %>
				<ul class="h_menu_top">
					<li><a href="/mamin/view/login.jsp">로그인</a></li>
					<li><a href="/mamin/view/signup.jsp">회원가입</a></li>
				</ul>
			<% }else{ %>
				<ul class="h_menu_top">
					<li><%= loginID %></li>
					<li><a href="/mamin/view/logout.jsp">로그아웃</a></li>
					<li><a href="/mamin/view/mypage.jsp">마이페이지</a></li>
				</ul>
			<% } %>
				<ul class="h_menu_btm">
					<li><a href="/mamin/view/index.jsp">홈</a></li>
					<li><a href="">게임설명</a></li>
					<li><a href="">랭킹</a></li>
					<li><a href="/mamin/view/list.jsp">커뮤니티</a></li>
				</ul>
			</div>
		</div>
	</header>

	<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<!-- JQUERY 라이브러리 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</body>
</html>