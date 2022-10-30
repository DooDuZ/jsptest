<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/mamin/css/gameBoard.css">
	<link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
	<!-- 헤더 연결없어서 폰트어썸 따로 추가해줌 - 수현 -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>

	<div class="gameroom"><!-- 게임방 전체 -->
		<div class="gameboard"><!-- 게임판 -->
			<div class="g_profile"><!-- 플레이어 정보 -->
				<div class="player1_info">
					
				</div>
				<div class="player2_info">
					
				</div>
				<div class="player3_info">
					
				</div>  
				<div class="player4_info">
					
				</div>
			
			</div>
			<div class="boardbox"><!-- 게임판 박스 -->
			
				<div class="b_center"> <!-- 게임판 가운데 부분 -->
					<!-- 주사위 굴러갈 부분, 게임로고 표시 -->
					<div class="b_diceContent"><!-- 주사위 -->
						<img class="b_dice1" alt="" src="/mamin/img/game/주사위1.png">
						<img class="b_dice2" alt="" src="/mamin/img/game/주사위1.png">
					</div>
					<div onclick="diceStart()" class="diceBtn"> <!-- 주사위 굴릴 버튼 -->
						<span>주사위 굴리기</span>
					</div>
					<div class="b_logo"><!-- 로고 넣을 부분 -->
					
					</div>
				</div>
				<div class="b_start"> <!-- 출발점 -->
					<!-- 게임칸 박스 생성해야함 -->
					<!--  플레이어 말, 색상, 나라명 , 통행료 표시해야함 -->
				</div>
				
				<div class="row b_row h_row bottom_row"> <!-- 아래 줄 -->
				
				
				</div>
				
				<div class="b_island"> <!-- 무인도칸 -->
				
				
				</div>
				
				<div class="row b_row v_row left_row"> <!-- 세로줄-->
				
				
				</div>
				
				<div class="b_olympic"> <!-- 올림픽칸 -->
				
				</div>
				
				<div class="row b_row h_row top_row"> <!--윗줄 -->
				
				
				</div>
				
				<div class="b_travle"> <!-- 세계여행 -->
				
				</div>
				<div class="row b_row v_row right_row"> <!--  오른쪽 줄 -->
				
				
				</div>
				
				
			</div>
		
		</div>
	
	
	
	
	
	</div>


	<script type="text/javascript" src="/mamin/js/gameBoard.js"></script>
</body>
</html>