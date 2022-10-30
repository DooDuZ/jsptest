
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>

	<table>
		<tr> <td> 번호 <td> 	<td class="b_no"> 		</td> </tr>
		<tr> <td> 제목 <td> 	<td class="b_title">  	</td> </tr>
		<tr> <td> 작성자 <td> <td class="m_id"> 		</td> </tr>
		<tr> <td> 내용 <td> 	<td class="b_content"> 	</td> </tr>
		<tr> <td> 첨부파일 <td> <td class="b_file"> 	</td> </tr>
	</table>

	<span class="btnbox"></span>

	<script src="../js/view.js "type="text/javascript"></script>
</body>
</html>