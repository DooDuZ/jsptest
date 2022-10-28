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
	
	<div class="container">
		<div class="room_wrapper">
			<div class="room_info">
				<div> 부동산은 승리한다 </div>
			 </div>
			<div class="slot_box">
				<div class="player_slot">
					<div class="r_p_img"></div>
					<div class="r_name_box">두두지</div>
					<div class="r_winrate">승률 97%</div>
					<div class="r_ready_box">Ready</div>
				</div>
				<div class="player_slot">
					<div class="r_p_img"></div>
					<div class="r_name_box">곰탕배달부</div>
					<div class="r_winrate">승률 82%</div>
					<div class="r_ready_box">...준비중...</div>
				</div>
				<div class="player_slot">
					<div class="r_p_img"></div>
					<div class="r_name_box">풀매수가주아아</div>
					<div class="r_winrate">승률 12%</div>
					<div class="r_ready_box">...준비중...</div>
				</div>
				<div class="player_slot">
					<div class="r_p_img"></div>
					<div class="r_name_box">껍데기</div>
					<div class="r_winrate">승률 51%</div>
					<div class="r_ready_box">Ready</div>
				</div>
			</div>
		</div>	
	</div>	
	
</body>
</html>