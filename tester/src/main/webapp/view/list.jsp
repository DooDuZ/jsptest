<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="write.jsp"> 글쓰기 </a>


	<table class="btable table"> <!--  1. 게시물 표시 구역 -->
			<tr>
				<th> 번호 </th> <th> 제목 </th> <!--  2. 제목클릭시 상세페이지 -->
				<th>작성자</th><th> 작성일 </th> 
			</tr>
		</table>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="../js/list.js" type="text/javascript"></script>
</body>
</html>