<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="../css/room.css" rel = "stylesheet">

</head>
<body>
	<%@ include file="header.jsp" %> <!-- file="jsp파일 주소" -->	
	<div class="r_sno" style="display : none;"></div>
	
	<div class="wrap">
		<div class="room_wrapper">
			<div class="room_info">
				마블의 민족!
			</div>
			<table class="slot_box">
				<tr class="player_slot r_slot1">
					<td class="r_p_img"></td>
					<td class="r_name_box"></td>
					<td class="r_winrate"></td>
					<td class="r_ready_box1"></td>
				</tr>
				<tr class="player_slot r_slot2">
					<td class="r_p_img"></td>
					<td class="r_name_box"></td>
					<td class="r_winrate"></td>
					<td class="r_ready_box2"></td>
				</tr>
				<tr class="player_slot r_slot3">
					<td class="r_p_img"></td>
					<td class="r_name_box"></td>
					<td class="r_winrate"></td>
					<td class="r_ready_box3"></td>
				</tr>
				<tr class="player_slot r_slot4">
					<td class="r_p_img"></td>
					<td class="r_name_box"></td>
					<td class="r_winrate"></td>
					<td class="r_ready_box4"></td>
				</tr>
			</table>
			<div class="r_btnbox">
			 	<button onclick="open_game()">게임 시작</button>
				<button onclick="exit()" class="exitbtn">나가기</button>
			</div>
			<div class="chatbox">
				<div class="chatDisplay"></div>
				<div class="chatInput">
					<span class="input_wrap"><textarea rows="" cols="" class="c_inputbox" onkeyUp="enterKey()"></textarea></span>
					<button onclick="sendMessage()">입력</button>
				</div>
			</div>
		</div>
	</div>	
	<script src="../js/room.js"></script>
</body>
</html>