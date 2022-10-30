<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/mypage.css">
	
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<div class="webbox">
			<form class="mypageform" action="/mamin/member/mypage" method="post">
				<table class="mypagetable">
					<tr>
						<td class="col1"> 비밀번호 </td>
						<td class="col2"> <input type="password" name="m_password" id="m_password" onkeyup="mevent1()"> </td>
						<td rowspan="2" class="col3">  </td>
					</tr>
					<tr>
						<td class="col1"> 비밀번호 확인 </td>
						<td class="col2"> <input type="password" id="m_password_confirm" onkeyup="mevent2()"> </td>
					</tr>
					<tr>
						<td class="col1"> 닉네임 </td>
						<td class="col2"> <input type="text" name="m_nick" class="m_nick"> </td>
					</tr>
					<tr>
						<td class="col1"> 캐릭터 </td>
						<td class="col2 characters">
							<img src="../img/member/1.png" class="cimg"><br>
							<input class="characterbtn" type="radio" name="character" value="1">곰
							<input class="characterbtn" type="radio" name="character" value="2">돼지
							<input class="characterbtn" type="radio" name="character" value="3">쥐
							<input class="characterbtn" type="radio" name="character" value="4">햄스터
							<input class="characterbtn" type="radio" name="character" value="5">토끼
							<input class="characterbtn" type="radio" name="character" value="6">다람쥐
							<input class="characterbtn" type="radio" name="character" value="아가양">아가양
						</td>
					</tr>
					<tr>
						<td class="col1"> 자기소개 </td>
						<td class="col2"> <input type="text" name="m_profile" class="m_profile"> </td>
					</tr>
				</table>
				<div class="mypagebtnbox">
					<button type="reset">취소하기</button>
					<button type="button" onclick="formsubmit()">회원정보수정</button>
				</div>
			</form>
		</div>
		
		
		<!-- JQuery -->
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
		<script type="text/javascript" src="../js/mypage.js"></script>
		
	</body>
</html>