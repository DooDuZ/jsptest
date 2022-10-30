<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<form id="write">
		제목: <input type="text" name="b_title" class="b_title"><br>
		<textarea id="summernote" name="b_content" class="b_content"></textarea><br>
		첨부파일 : <input type="file" name ="b_file" class="b_file"> <br>
		<button type="button" onclick="update()">수정</button>
	</form>
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="../js/boardupdate.js" type="text/javascript"></script>
</body>
</html>