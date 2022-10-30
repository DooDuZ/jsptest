<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
	<link href="../css/login.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="main_signin">
		
		<!-- 본문 -->
		<section>
			<div class="wrap">
				<div class="container">
					<div class="content_box">
						<!-- id,pw -->
						<div class="input_box">
							<!-- id -->
							<div id="signin_input1">
								<span class="input_field">
									<input id="signin_id" type="text" placeholder="ID">
								</span>
							</div>
							<!-- pw -->
							<div id="signin_input2">
								<span class="input_field">
									<input id="signin_pw" type="password" placeholder="Password">
								</span>
							</div>
						</div>
						
						<!-- remember me, forger pw -->
						<div class="option_box">
							<!-- forget id -->
							<span>
								<h3> <a href="#">Forget Id?</a> </h3>
							</span>
							<!-- forget pw -->
							<span>
								<h3> <a href="#">Forget Password?</a> </h3>
							</span>
						</div>
						
						<!-- login btn -->
						<div class="btn_box">
							<button onclick="login()" class="btn">LOGIN</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		
	</div>


	<script type="text/javascript" src="../js/login.js"></script>

</body>
</html>